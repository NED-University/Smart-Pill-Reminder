package com.taradov.alarmme;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class DBViewer extends Activity {

    private ListView listView;
    private ArrayList<Alarm> alarmList;
    private AlarmAdapter alarmAdapter;
    private ArrayList<Medicine> medicineList;
    private MedicineAdapter medicineAdapter;
    private ArrayList<HistoryItem> historyList;
    private HistoryAdapter historyAdapter;
    private ArrayList<Patient> patientList;
    private PatientAdapter patientAdapter;
    private TextView titleView;
    DBAdapter dbAdapter;
    Cursor cursor;

    public static final String TITLE_HISTORY = "History";
    public static final String TITLE_MEDICINES = "Medicines";
    public static final String TITLE_ALARMS = "Alarms";
    public static final String TITLE_PATIENT = "Patient";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dbviewer);

        listView = (ListView)findViewById(R.id.db_list_view);
        titleView = (TextView) findViewById(R.id.database_title);

        // initialize array list of alarms
        alarmList = new ArrayList<Alarm>();
        // initialize alarm array adapter
        alarmAdapter = new AlarmAdapter(this, R.layout.db_alarm_view, alarmList);

        medicineList = new ArrayList<Medicine>();
        // initialize alarm array adapter
        medicineAdapter = new MedicineAdapter(this, R.layout.db_medicine_view, medicineList);

        historyList = new ArrayList<HistoryItem>();
        // initialize alarm array adapter
        historyAdapter = new HistoryAdapter(this, R.layout.db_history_item_view, historyList);

        patientList = new ArrayList<Patient>();
        // initialize alarm array adapter
        patientAdapter = new PatientAdapter(this, R.layout.db_patient_view, patientList);

        // bind an adapter to the list view
        listView.setAdapter(historyAdapter);
        titleView.setText(TITLE_HISTORY);

        dbAdapter = new DBAdapter(this);
        dbAdapter.open();

        populateAlarmList();
        populateMedicineList();
        populateHistoryList();
        populatePatientList();
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
            } while(cursor.moveToNext());
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
            } while(cursor.moveToNext());
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
            } while(cursor.moveToNext());
        historyAdapter.notifyDataSetChanged();
    }

    private void populatePatientList() {
        // Get all the alarms from the database.
        cursor = dbAdapter.getAllPatientCursor();
        startManagingCursor(cursor);
        // Update the array.
        updatePatientArray();
    }

    private void updatePatientArray() {
        cursor.requery();
        patientList.clear();
        if (cursor.moveToFirst())
            do {
                patientList.add(0, dbAdapter.createPatient(cursor));
            } while(cursor.moveToNext());
        patientAdapter.notifyDataSetChanged();
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
        if (id == R.id.switch_alarms)
        {
            listView.setAdapter(alarmAdapter);
            titleView.setText(TITLE_ALARMS);
            return true;
        }
        else if (id == R.id.switch_medicine)
        {
            listView.setAdapter(medicineAdapter);
            titleView.setText(TITLE_MEDICINES);
            return true;
        }
        else if (id == R.id.switch_history)
        {
            listView.setAdapter(historyAdapter);
            titleView.setText(TITLE_HISTORY);
            return true;
        }
        else if (id == R.id.switch_patient)
        {
            listView.setAdapter(patientAdapter);
            titleView.setText(TITLE_PATIENT);
            return true;
        }
        else if (id == R.id.export_button)
        {
            DatabaseExportHelper exporter = new DatabaseExportHelper(this, dbAdapter);
            exporter.execute();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
