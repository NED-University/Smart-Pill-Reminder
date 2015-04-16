package com.taradov.alarmme;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;


public class DBViewer extends Activity {

    private ListView listView;
    private ArrayList<Alarm> alarmList;
    private AlarmAdapter alarmAdapter;
    private ArrayList<Medicine> medicineList;
    private MedicineAdapter medicineAdapter;
    private ArrayList<HistoryItem> historyList;
    private HistoryAdapter historyAdapter;
    DBAdapter dbAdapter;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dbviewer);

        listView = (ListView) findViewById(R.id.db_list_view);

        // initialize array list of alarms
        alarmList = new ArrayList<Alarm>();
        // initialize alarm array adapter
        alarmAdapter = new AlarmAdapter(this, R.layout.db_alarm_view, alarmList);
        // bind the adapter to the list view
        listView.setAdapter(alarmAdapter);

        medicineList = new ArrayList<Medicine>();
        // initialize alarm array adapter
        medicineAdapter = new MedicineAdapter(this, R.layout.db_alarm_view, medicineList);

        historyList = new ArrayList<HistoryItem>();
        // initialize alarm array adapter
        historyAdapter = new HistoryAdapter(this, R.layout.db_alarm_view, historyList);

        dbAdapter = new DBAdapter(this);
        dbAdapter.open();

//        Alarm testItem = new Alarm(this);
//
//        testItem.setTitle("Test");
//        testItem.setDate(System.currentTimeMillis());
//        testItem.setToDate(System.currentTimeMillis());
//        testItem.setTime(System.currentTimeMillis());
//        testItem.setDays(Alarm.EVERY_DAY);
//        testItem.setInterval(5);
//        testItem.setpId(R.drawable.asthma_inhaler_icon);
//        testItem.setEnabled(true);
//
//        dbAdapter.addAlarm(testItem, 4);

        // TODO: Test functions
        populateAlarmList();
        populateMedicineList();
        populateHistoryList();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // Close the database
        dbAdapter.close();
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

    private void populateAlarmList() {
        // Get all the alarms from the database.
        cursor = dbAdapter.getAllAlarmCursor();
        startManagingCursor(cursor);
        // Update the array.
        updateAlarmArray();
    }

    private void updateAlarmArray() {
        cursor.requery();
        alarmList.clear();
        if (cursor.moveToFirst())
            do {
                alarmList.add(0, dbAdapter.createAlarm(cursor));
            } while (cursor.moveToNext());
        alarmAdapter.notifyDataSetChanged();
    }

    private void populateMedicineList() {
        // Get all the alarms from the database.
        cursor = dbAdapter.getAllMedicineCursor();
        startManagingCursor(cursor);
        // Update the array.
        updateMedicineArray();
    }

    private void updateMedicineArray() {
        cursor.requery();
        medicineList.clear();
        if (cursor.moveToFirst())
            do {
                medicineList.add(0, dbAdapter.createMedicineItem(cursor));
            } while (cursor.moveToNext());
        medicineAdapter.notifyDataSetChanged();
    }

    private void populateHistoryList() {
        // Get all the alarms from the database.
        cursor = dbAdapter.getAllHistoryCursor();
        startManagingCursor(cursor);
        // Update the array.
        updateHistoryArray();
    }

    private void updateHistoryArray() {
        cursor.requery();
        historyList.clear();
        if (cursor.moveToFirst())
            do {
                historyList.add(0, dbAdapter.createHistoryItem(cursor));
            } while (cursor.moveToNext());
        historyAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_dbviewer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //TODO: Create option to switch adapters through settings menu
}
