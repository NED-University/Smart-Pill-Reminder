/**************************************************************************
 *
 * Copyright (C) 2012 Alex Taradov <taradov@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 *************************************************************************/

package com.taradov.alarmme;

import java.util.Timer;
import java.util.TimerTask;
import android.os.Bundle;
import android.os.Vibrator;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.net.Uri;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.util.Log;
import android.view.WindowManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.TextView;
import android.content.Intent;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.media.Ringtone;
import android.media.RingtoneManager;

public class AlarmNotification extends Activity
{
    private final String TAG = "AlarmMe";

    private Ringtone mRingtone;
    private Vibrator mVibrator;
    private final long[] mVibratePattern = { 0, 500, 500 };
    private boolean mVibrate;
    private Uri mAlarmSound;
    private long mPlayTime;
    private Timer mTimer = null;
    private Alarm mAlarm;
    private DateTime mDateTime;
    private TextView mTextView;
    private PlayTimerTask mTimerTask;
    private ImageView mImageView;

    private HistoryItem mHistoryItem;
    private DBAdapter dbAdapter;

    @Override
    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);

        dbAdapter = new DBAdapter(this);
        dbAdapter.open();

        getWindow().addFlags(
                WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON |
                        WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED |
                        WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);

        setContentView(R.layout.notification);

        mDateTime = new DateTime(this);
        mTextView = (TextView)findViewById(R.id.alarm_title_text);
        mImageView =(ImageView)findViewById(R.id.imageView1);

        readPreferences();

        mRingtone = RingtoneManager.getRingtone(getApplicationContext(), mAlarmSound);
        if (mVibrate)
            mVibrator = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);

        start(getIntent());
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        Log.i(TAG, "AlarmNotification.onDestroy()");
        dbAdapter.close();
        stop();
    }

    @Override
    protected void onNewIntent(Intent intent)
    {
        super.onNewIntent(intent);
        Log.i(TAG, "AlarmNotification.onNewIntent()");

        addNotification(mAlarm);

        stop();
        start(intent);
    }

    private void start(Intent intent)
    {
        mAlarm = new Alarm(this);
        mHistoryItem = new HistoryItem(this);

        mAlarm.fromIntent(intent);
        mHistoryItem.setAlarmId((int)mAlarm.getId());
        mHistoryItem.setTimeDue(mAlarm.getNextOccurence());

        dbAdapter.addHistory(mHistoryItem);
        mHistoryItem = dbAdapter.getLastHistoryItem();

        Log.i(TAG,"AlarmNotification.start('" + mAlarm.getTitle() + "')");

        mTextView.setText(mAlarm.getTitle());
        mImageView.setImageResource((int) mAlarm.getpId());

        mTimerTask = new PlayTimerTask();
        mTimer = new Timer();
        mTimer.schedule(mTimerTask, mPlayTime);
        mRingtone.play();
        if (mVibrate)
            mVibrator.vibrate(mVibratePattern, 0);
    }

    private void stop()
    {
        Log.i(TAG, "AlarmNotification.stop()");

        mTimer.cancel();
        mRingtone.stop();
        if (mVibrate)
            mVibrator.cancel();
    }

    public void onDismissClick(View view)
    {
//	  finish();
//	  Intent intent= new Intent(AlarmNotification.this,QR.class);
//	  startActivity(intent);
////	  finish();

        try {

            Intent intent = new Intent(
                    "com.google.zxing.client.android.SCAN");
            intent.putExtra("SCAN_MODE", "QR_CODE_MODE,PRODUCT_MODE");
            mHistoryItem.toIntent(intent);
            startActivityForResult(intent, 0);
            finish();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "ERROR:" + e, Toast.LENGTH_LONG).show();
        }
    }

    //In the same activity you�ll need the following to retrieve the results:
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == 0) {

            if (resultCode == RESULT_OK) {
                mHistoryItem.setTimeTaken(System.currentTimeMillis());
                mHistoryItem.setQRCode(intent.getStringExtra("SCAN_RESULT"));

//                String requiredQRCode = dbAdapter.getMedicineFromAlarmID(mHistoryItem.getAlarmId()).getQRCode();
                String requiredQRCode = "Brufen";

                if (requiredQRCode.equals(mHistoryItem.getQRCode()))
                {
                    mHistoryItem.setValidation(HistoryItem.TAKEN);
                }
                else
                {
                    mHistoryItem.setValidation(HistoryItem.INCORRECT);
                }

                dbAdapter.updateHistory(mHistoryItem.getId(), mHistoryItem);
            }
        }
    }

    private void readPreferences()
    {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        mAlarmSound = Uri.parse(prefs.getString("alarm_sound_pref", "DEFAULT_RINGTONE_URI"));
        mVibrate = prefs.getBoolean("vibrate_pref", true);
        mPlayTime = (long)Integer.parseInt(prefs.getString("alarm_play_time_pref", "60")) * 1000;
    }

    private void addNotification(Alarm alarm)
    {
        NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification;
        PendingIntent activity;
        Intent intent;

        Log.i(TAG, "AlarmNotification.addNotification(" + alarm.getId() + ", '" + alarm.getTitle() + "', '" + mDateTime.formatDetails(alarm) + "')");

        notification = new Notification(R.drawable.ic_notification, "Missed alarm", System.currentTimeMillis());
        notification.flags |= Notification.FLAG_AUTO_CANCEL;

        intent = new Intent(this.getApplicationContext(), AlarmMe.class);
        intent.setAction(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);

        activity = PendingIntent.getActivity(this, (int)alarm.getId(), intent, PendingIntent.FLAG_UPDATE_CURRENT);
        notification.setLatestEventInfo(this, "Missed alarm: " + alarm.getTitle(), mDateTime.formatDetails(alarm), activity);

        notificationManager.notify((int)alarm.getId(), notification);
    }

    @Override
    public void onBackPressed()
    {
        finish();
    }

    private class PlayTimerTask extends TimerTask
    {
        @Override
        public void run()
        {
            Log.i(TAG, "AlarmNotification.PalyTimerTask.run()");
            addNotification(mAlarm);
            finish();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        // Close the database
        dbAdapter.close();
    }

    @Override
    public void onStop() {
        super.onStop();
        // Close the database
        dbAdapter.close();
    }

    @Override
    public void onResume() {
        super.onResume();
        // Close the database
        dbAdapter.open();
    }

    @Override
    public void onStart()
    {
        super.onStart();
        dbAdapter.open();
    }
}

