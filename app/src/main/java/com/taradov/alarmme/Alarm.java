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

import java.lang.System;
import java.lang.Comparable;
import java.io.IOException;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.Calendar;

import android.content.Intent;
import android.content.Context;

public class Alarm implements Comparable<Alarm> {
    private Context mContext;
    private long mId;
    private String mTitle;
    private long mFromDate;
    private long mToDate;
    private long mTime;
    private int mDays;
    private int mInterval;
    private long pId;
    private boolean mEnabled;
    private int mAudioPos;
    private int Red;
    private int Green;
    private int Blue;
    private int alpha ;

    private int mOccurence;
    private long mNextOccurence;

    public static final int ONCE = 0;
    public static final int WEEKLY = 1;

    public static final int NEVER = 0;
    public static final int EVERY_DAY = 0x7f;

    public static int[] AUDIO_IDS = {R.raw.def, R.raw.danganronpa};

    public Alarm(Context context) {
        mContext = context;
        mId = 0;
        mTitle = "";
        mFromDate = System.currentTimeMillis();
        mToDate = System.currentTimeMillis();
        mEnabled = true;
        mOccurence = ONCE;
        mDays = EVERY_DAY;
        Red=255;
        Green=0;
        Blue=0;
        alpha=255;
        mAudioPos=0;
        update();
    }

	public int getAudioPos()
    {
        return mAudioPos;
    }

    public int getAudioId()
    {
        return AUDIO_IDS[mAudioPos];
    }

    public long getId() {
        return mId;
    }

    public void setId(long id) {
        mId = id;
    }

    public long getpId() {
        return pId;
    }

    public void setpId(long picid) {
        pId = picid;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public long getTime() {
        return mTime;
    }

    public void setTime(long time) {
        mTime = time;
    }

    public int getOccurence() {
        return mOccurence;
    }

    public int getInterval() {
        return mInterval;
    }

    public void setAudioPos(int _audio)
    {
        mAudioPos = _audio;
    }

    public void setOccurence(int occurence) {
        mOccurence = occurence;
        update();
    }

    public void setInterval(int interval) {
        mInterval = interval;
        update();
    }

    public long getFromDate() {

        return mFromDate;
    }

    public void setFromDate(long date) {
        mFromDate = date;
        update();
    }

    public long getToDate() {

        return mToDate;
    }

    public void setToDate(long date) {
        mToDate = date;
        update();
    }

    public boolean getEnabled() {
        return mEnabled;
    }

    public void setEnabled(boolean enabled) {
        mEnabled = enabled;
    }

    public int getDays() {
        return mDays;
    }

    public void setDays(int days) {
        mDays = days;
        update();
    }

    public void setRed(int r){
        Red=r;
    }
    public int getRed(){
        return Red;
    }
    public void setGreen(int g){
        Green=g;
    }
    public int getGreen(){
        return Green;
    }
    public void setBlue(int b){
        Blue=b;
    }
    public int getBlue(){
        return Blue;
    }

    public void setAlpha(int a){
        alpha=a;
    }
    public int getAlpha(){
        return alpha;
    }



    public long getNextOccurence() {
        return mNextOccurence;
    }

    public boolean getOutdated() {
        return mNextOccurence < System.currentTimeMillis();
    }

    public int compareTo(Alarm aThat) {
        final long thisNext = getNextOccurence();
        final long thatNext = aThat.getNextOccurence();
        final int BEFORE = -1;
        final int EQUAL = 0;
        final int AFTER = 1;

        if (this == aThat)
            return EQUAL;

        if (thisNext > thatNext)
            return AFTER;
        else if (thisNext < thatNext)
            return BEFORE;
        else
            return EQUAL;
    }

    public void update() {
        Calendar now = Calendar.getInstance();

        // if (mOccurence == WEEKLY)
        {
            Calendar alarm = Calendar.getInstance();

            alarm.setTimeInMillis(mFromDate);
            alarm.set(now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH));

            if (mDays != NEVER) {
                while (true) {
                    int day = (alarm.get(Calendar.DAY_OF_WEEK) + 5) % 7;

                    if (alarm.getTimeInMillis() >= now.getTimeInMillis() && (mDays & (1 << day)) > 0)
                        break;

                    alarm.add(Calendar.DAY_OF_MONTH, 1);
                }
            } else {
                alarm.add(Calendar.YEAR, 10);
            }

            mNextOccurence = alarm.getTimeInMillis();
        }
//        else
//        {
//            mNextOccurence = mFromDate;
//        }

        mFromDate = mNextOccurence;
    }

    public void toIntent(Intent intent) {
        intent.putExtra("com.taradov.alarmme.id", mId);
        intent.putExtra("com.taradov.alarmme.title", mTitle);
        intent.putExtra("com.taradov.alarmme.date", mFromDate);
        intent.putExtra("com.taradov.alarmme.todate", mToDate);
        intent.putExtra("com.taradov.alarmme.time", mTime);
        intent.putExtra("com.taradov.alarmme.alarm", mEnabled);
        intent.putExtra("com.taradov.alarmme.occurence", mOccurence);
        intent.putExtra("com.taradov.alarmme.interval", mInterval);
        intent.putExtra("com.taradov.alarmme.days", mDays);
        intent.putExtra("com.taradov.alarmme.audiopos", mAudioPos);
        intent.putExtra("com.taradov.alarmme.pId", pId);
        intent.putExtra("com.taradov.alarmme.red", Red);
        intent.putExtra("com.taradov.alarmme.green", Green);
        intent.putExtra("com.taradov.alarmme.blue", Blue);
        intent.putExtra("com.taradov.alarmme.alpha", alpha);
    }

    public void fromIntent(Intent intent) {
        mId = intent.getLongExtra("com.taradov.alarmme.id", 0);
        mTitle = intent.getStringExtra("com.taradov.alarmme.title");
        mTitle = intent.getStringExtra("com.taradov.alarmme.title");
        mTitle = intent.getStringExtra("com.taradov.alarmme.title");
        mFromDate = intent.getLongExtra("com.taradov.alarmme.date", 0);
        mToDate = intent.getLongExtra("com.taradov.alarmme.todate", 0);
        mEnabled = intent.getBooleanExtra("com.taradov.alarmme.alarm", true);
        mOccurence = intent.getIntExtra("com.taradov.alarmme.occurence", 0);
        mInterval = intent.getIntExtra("com.taradov.alarmme.interval", 0);
        mDays = intent.getIntExtra("com.taradov.alarmme.days", 0);
        mAudioPos = intent.getIntExtra("com.taradov.alarmme.audiopos", 0);
        pId = intent.getLongExtra("com.taradov.alarmme.pId", -1);
        Red = intent.getIntExtra("com.taradov.alarmme.red", 0);
        Green = intent.getIntExtra("com.taradov.alarmme.green", 0);
        Blue = intent.getIntExtra("com.taradov.alarmme.blue", 0);
        alpha = intent.getIntExtra("com.taradov.alarmme.alpha", 100);
        update();
    }

    public void serialize(DataOutputStream dos) throws IOException {
        dos.writeLong(mId);
        dos.writeUTF(mTitle);
        dos.writeLong(mFromDate);
        dos.writeLong(mToDate);
        dos.writeBoolean(mEnabled);
        dos.writeInt(mOccurence);
        dos.writeInt(mInterval);
        dos.writeInt(mDays);
        dos.writeLong(pId);
    }

    public void deserialize(DataInputStream dis) throws IOException {
        mId = dis.readLong();
        mTitle = dis.readUTF();
        mFromDate = dis.readLong();
        mToDate = dis.readLong();
        mEnabled = dis.readBoolean();
        mOccurence = dis.readInt();
        mInterval = dis.readInt();
        mDays = dis.readInt();
        pId = dis.readLong();
        update();
    }
}
