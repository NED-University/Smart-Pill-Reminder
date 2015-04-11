package com.taradov.alarmme;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Inshal on 11-Mar-15.
 */
public class DBAdapter {
    private static final String DATABASE_NAME = "AlarmMeDB.db";
    private static final int DATABASE_VERSION = 1;
    private SQLiteDatabase db;
    private final Context mContext;
    private DBOpenHelper mdbHelper;

    // Alarm Table Constants
    private static final String ALARM_TABLE = "Alarm";

    public static final String KEY_ALARM_ID         = "Id";
    public static final String KEY_ALARM_TITLE      = "Title";
    public static final String KEY_ALARM_FROMDATE   = "FromDate";
    public static final String KEY_ALARM_TODATE     = "ToDate";
    public static final String KEY_ALARM_TIME       = "Time";
    public static final String KEY_ALARM_DAYS       = "Days";
    public static final String KEY_ALARM_MEDICINEID = "MedicineId";
    public static final String KEY_ALARM_INTERVAL   = "Interval";
    public static final String KEY_ALARM_ICON       = "Icon";
    public static final String KEY_ALARM_ENABLED    = "Enabled";

    public static final int ALARM_ID_COLUMN         = 0;
    public static final int ALARM_TITLE_COLUMN      = 1;
    public static final int ALARM_FROMDATE_COLUMN   = 2;
    public static final int ALARM_TODATE_COLUMN     = 3;
    public static final int ALARM_TIME_COLUMN       = 4;
    public static final int ALARM_DAYS_COLUMN       = 5;
    public static final int ALARM_MEDICINEID_COLUMN = 6;
    public static final int ALARM_INTERVAL_COLUMN   = 7;
    public static final int ALARM_ICON_COLUMN       = 8;
    public static final int ALARM_ENABLED_COLUMN    = 9;

    // Medicine Table Constants
    private static final String MEDICINE_TABLE = "Medicine";

    public static final String KEY_MEDICINE_ID      = "Id";
    public static final String KEY_MEDICINE_NAME    = "Name";
    public static final String KEY_MEDICINE_COLOR   = "Color";
    public static final String KEY_MEDICINE_AUDIO   = "Audio";
    public static final String KEY_MEDICINE_QRCODE  = "QRcode";

    public static final int MEDICINE_ID_COLUMN      = 0;
    public static final int MEDICINE_NAME_COLUMN    = 1;
    public static final int MEDICINE_COLOR_COLUMN   = 2;
    public static final int MEDICINE_AUDIO_COLUMN   = 3;
    public static final int MEDICINE_QRCODE_COLUMN   = 4;

    // Validation Table Constants
    private static final String HISTORY_TABLE = "MedicationHistory";

    public static final String KEY_HISTORY_ID           = "Id";
    public static final String KEY_HISTORY_ALARMID      = "AlarmId";
    public static final String KEY_HISTORY_TIMEDUE      = "TimeDue";
    public static final String KEY_HISTORY_TIMETAKEN    = "TimeTaken";
    public static final String KEY_HISTORY_QRCODE       = "QRCode";
    public static final String KEY_HISTORY_VALIDATION   = "Validation";

    public static final int HISTORY_ID_COLUMN           = 0;
    public static final int HISTORY_ALARMID_COLUMN      = 1;
    public static final int HISTORY_TIMEDUE_COLUMN      = 2;
    public static final int HISTORY_TIMETAKEN_COLUMN    = 3;
    public static final int HISTORY_QRCODE_COLUMN       = 4;
    public static final int HISTORY_VALIDATION_COLUMN   = 5;

    // Validation Table Constants
    private static final String PATIENT_TABLE = "Patient";

    public static final String KEY_PATIENT_ID               = "Id";
    public static final String KEY_PATIENT_NAME             = "Name";
    public static final String KEY_PATIENT_FATHERNAME       = "FatherName";
    public static final String KEY_PATIENT_AGE              = "Age";
    public static final String KEY_PATIENT_GENDER           = "Gender";
    public static final String KEY_PATIENT_ETHNICITY        = "Ethnicity";
    public static final String KEY_PATIENT_ADDRESS          = "Address";
    public static final String KEY_PATIENT_MARITALSTATUS    = "MaritalStatus";
    public static final String KEY_PATIENT_PROFESSION       = "Profession";
    public static final String KEY_PATIENT_INFECTIONS       = "Infections";
    public static final String KEY_PATIENT_CHILDRENNO       = "NumberOfChildren";
    public static final String KEY_PATIENT_CHILDRENSUFFER   = "ChildrenSuffer";
    public static final String KEY_PATIENT_SPOUSESUFFER     = "SpouseSuffer";
    public static final String KEY_PATIENT_RISK             = "RiskBehaviour";
    public static final String KEY_PATIENT_TRAVELHISTORY    = "TravelHistory";
    public static final String KEY_PATIENT_FRIENDHISTORY    = "FriendHistory";
    public static final String KEY_PATIENT_CLINICALFEATURES = "ClinicalFeatures";
    public static final String KEY_PATIENT_DIAGNOSISDATE    = "DiagnosisDate";

    public static final int PATIENT_ID_COLUMN               = 0;
    public static final int PATIENT_NAME_COLUMN             = 1;
    public static final int PATIENT_FATHERNAME_COLUMN       = 2;
    public static final int PATIENT_AGE_COLUMN              = 3;
    public static final int PATIENT_GENDER_COLUMN           = 4;
    public static final int PATIENT_ETHNICITY_COLUMN        = 5;
    public static final int PATIENT_ADDRESS_COLUMN          = 6;
    public static final int PATIENT_MARITALSTATUS_COLUMN    = 7;
    public static final int PATIENT_INFECTIONS_COLUMN       = 8;
    public static final int PATIENT_PROFESSION_COLUMN       = 9;
    public static final int PATIENT_CHILDRENNO_COLUMN       = 10;
    public static final int PATIENT_CHILDRENSUFFER_COLUMN   = 11;
    public static final int PATIENT_SPOUSESUFFER_COLUMN     = 12;
    public static final int PATIENT_RISK_COLUMN             = 13;
    public static final int PATIENT_TRAVELHISTORY_COLUMN    = 14;
    public static final int PATIENT_FRIENDHISTORY_COLUMN    = 15;
    public static final int PATIENT_CLINICALFEATUR_COLUMN   = 16;
    public static final int PATIENT_DIAGNOSISDATE_COLUMN    = 17;

    public DBAdapter(Context _context) {
        this.mContext = _context;
        mdbHelper = new DBOpenHelper(mContext, DATABASE_NAME,
                null, DATABASE_VERSION);
    }

    private static class DBOpenHelper extends SQLiteOpenHelper {
        public DBOpenHelper(Context context, String name,
                            SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }
        // SQL Statement to create a new database.
        private static final String CREATE_TABLE_ALARM =
                "create table " + ALARM_TABLE + " (" +
                KEY_ALARM_ID          + " integer primary key autoincrement, " +
                KEY_ALARM_TITLE       + " text, " +
                KEY_ALARM_FROMDATE    + " long not null, " +
                KEY_ALARM_TODATE      + " long, " +
                KEY_ALARM_TIME        + " long not null, " +
                KEY_ALARM_DAYS        + " integer not null, " +
                KEY_ALARM_MEDICINEID  + " integer not null, " +
                KEY_ALARM_INTERVAL    + " integer not null, " +
                KEY_ALARM_ICON        + " integer not null, " +
                KEY_ALARM_ENABLED     + " integer not null " +
                ");";

        private static final String CREATE_TABLE_MEDICINE =
                "create table " + MEDICINE_TABLE + " (" +
                KEY_MEDICINE_ID     + " integer primary key autoincrement, " +
                KEY_MEDICINE_NAME   + " text not null," +
                KEY_MEDICINE_COLOR  + " integer," +
                KEY_MEDICINE_AUDIO  + " integer, " +
                KEY_MEDICINE_QRCODE + " text not null " +
                       ");";

        private static final String CREATE_TABLE_VALIDATION =
                "create table " + HISTORY_TABLE + " (" +
                KEY_HISTORY_ID         + " integer primary key autoincrement, " +
                KEY_HISTORY_ALARMID    + " integer not null,"  +
                KEY_HISTORY_TIMEDUE    + " long not null, " +
                KEY_HISTORY_TIMETAKEN  + " long, " +
                KEY_HISTORY_QRCODE     + " text, " +
                KEY_HISTORY_VALIDATION + " text " +
                ");";

        private static final String CREATE_TABLE_PATIENT =
                "create table " + PATIENT_TABLE + " (" +
                KEY_PATIENT_ID              + " integer primary key autoincrement, " +
                KEY_PATIENT_NAME            + " text not null, " +
                KEY_PATIENT_FATHERNAME      + " text, " +
                KEY_PATIENT_AGE             + " integer, " +
                KEY_PATIENT_GENDER          + " integer, " +
                KEY_PATIENT_ETHNICITY       + " integer, " +
                KEY_PATIENT_ADDRESS         + " text, " +
                KEY_PATIENT_MARITALSTATUS   + " integer, " +
                KEY_PATIENT_PROFESSION      + " text, " +
                KEY_PATIENT_INFECTIONS      + " integer, " +
                KEY_PATIENT_CHILDRENNO      + " text, " +
                KEY_PATIENT_CHILDRENSUFFER  + " integer, " +
                KEY_PATIENT_SPOUSESUFFER    + " integer, " +
                KEY_PATIENT_RISK            + " integer, " +
                KEY_PATIENT_TRAVELHISTORY   + " integer, " +
                KEY_PATIENT_FRIENDHISTORY   + " integer, " +
                KEY_PATIENT_CLINICALFEATURES+ " integer, " +
                KEY_PATIENT_DIAGNOSISDATE   + " long " +
                ");";

        @Override
        public void onCreate(SQLiteDatabase _db) {
            _db.execSQL(CREATE_TABLE_ALARM);
            _db.execSQL(CREATE_TABLE_MEDICINE);
            _db.execSQL(CREATE_TABLE_VALIDATION);
            _db.execSQL(CREATE_TABLE_PATIENT);
        }

        @Override
        public void onUpgrade(SQLiteDatabase _db, int _oldVersion, int _newVersion) {
            Log.w("TaskDBAdapter", "Upgrading from version " +
                    _oldVersion + " to " +
                    _newVersion + ", which will destroy all old data");

            // Drop the old table.
            _db.execSQL("DROP TABLE IF EXISTS " + ALARM_TABLE);
            _db.execSQL("DROP TABLE IF EXISTS " + MEDICINE_TABLE);
            _db.execSQL("DROP TABLE IF EXISTS " + HISTORY_TABLE);
            _db.execSQL("DROP TABLE IF EXISTS " + PATIENT_TABLE);

            // Create a new one.
            onCreate(_db);
        }
    }

    public void open() throws SQLiteException {
        try {
            db = mdbHelper.getWritableDatabase();
        } catch (SQLiteException ex) {
            db = mdbHelper.getReadableDatabase();
        }
    }

    public void close() {
        db.close();
    }

    // Insert a new medicine
    public long addMedicine(String _name, int _color, int _audio,String _QRcode) {
        // Create a new row of values to insert.
        ContentValues values = new ContentValues();
        // Assign values for each column.
        values.put(KEY_MEDICINE_NAME, _name);
        values.put(KEY_MEDICINE_COLOR, _color);
        values.put(KEY_MEDICINE_COLOR, _audio);
        values.put(KEY_MEDICINE_QRCODE, _QRcode);

        // Insert the row.
        return db.insert(MEDICINE_TABLE, null, values);
    }

    // Update a medicine entry
    public boolean updateMedicine(long _id, String _name, int _color, int _audio,String _QRcode) {
        // Create a new row of values to insert.
        ContentValues newValues = new ContentValues();
        // Assign values for each column.
        newValues.put(KEY_MEDICINE_NAME, _name);
        newValues.put(KEY_MEDICINE_COLOR, _color);
        newValues.put(KEY_MEDICINE_AUDIO, _audio);
        newValues.put(KEY_MEDICINE_QRCODE, _QRcode);

        // Insert the row.
        return db.update(MEDICINE_TABLE, newValues, KEY_MEDICINE_ID + "=" + _id, null) > 0;
    }

    // Remove a medicine
    public boolean removeMedicine(String _name) {
        return db.delete(MEDICINE_TABLE, KEY_MEDICINE_NAME + "=" + addQuotes(_name), null) > 0;
    }

    // get a cursor to all medicine items
    public Cursor getAllMedicineCursor() {
        return db.query(MEDICINE_TABLE, null,
                null, null, null, null, null);
    }

    public Medicine createMedicineItem(Cursor _cursor)
    {
        return new Medicine(mContext,
                _cursor.getInt(MEDICINE_ID_COLUMN),
                _cursor.getString(MEDICINE_NAME_COLUMN),
                _cursor.getInt(MEDICINE_COLOR_COLUMN),
                _cursor.getInt(MEDICINE_AUDIO_COLUMN)),
                _cursor.getString(MEDICINE_QRCODE_COLUMN);
    }

    public Medicine getMedicineFromAlarmID(int _alarmID)
    {
        return createMedicineItem(db.rawQuery("SELECT * FROM " + MEDICINE_TABLE + " M, " +
                ALARM_TABLE + " A WHERE A." + KEY_ALARM_MEDICINEID + "=M." + KEY_MEDICINE_ID +
                " AND A." + KEY_ALARM_ID + "=" + _alarmID, null, null));
    }

    // Insert a new alarm
    public long addAlarm(Alarm _alarm, int _medicineID)
    {
        // Create a new row of values to insert.
        ContentValues values = new ContentValues();

        // Assign values for each column.
        //values.put(KEY_ALARM_ID         , _alarm.getId());
        values.put(KEY_ALARM_TITLE      , _alarm.getTitle());
        values.put(KEY_ALARM_FROMDATE   , _alarm.getDate());
        values.put(KEY_ALARM_TODATE     , _alarm.getToDate());
        values.put(KEY_ALARM_TIME       , _alarm.getTime());
        values.put(KEY_ALARM_DAYS       , _alarm.getDays());
        values.put(KEY_ALARM_MEDICINEID , _medicineID);
        values.put(KEY_ALARM_INTERVAL   , _alarm.getInterval());
        values.put(KEY_ALARM_ICON       , _alarm.getpId());
        values.put(KEY_ALARM_ENABLED    , _alarm.getEnabled());

        // Insert the row.
        return db.insert(ALARM_TABLE, null, values);
    }

    // Update an alarm entry
    public boolean updateAlarm(long _id, Alarm _alarm)
    {
        // Create a new row of values to insert.
        ContentValues newValues = new ContentValues();
        DateTime dT = new DateTime(mContext);

        // Assign values for each column.
        newValues.put(KEY_ALARM_TITLE      , _alarm.getTitle());
        newValues.put(KEY_ALARM_FROMDATE   , _alarm.getDate());
        newValues.put(KEY_ALARM_TODATE     , _alarm.getToDate());
        newValues.put(KEY_ALARM_TIME       , _alarm.getTime());
        newValues.put(KEY_ALARM_DAYS       , _alarm.getDays());
        newValues.put(KEY_ALARM_INTERVAL   , _alarm.getInterval());
        newValues.put(KEY_ALARM_ICON       , _alarm.getpId());
        newValues.put(KEY_ALARM_ENABLED    , _alarm.getEnabled());

        // Update the row.
        return db.update(ALARM_TABLE, newValues, KEY_ALARM_ID + "=" + _id, null) > 0;
    }

    public boolean updateAlarm(String _name, Alarm _alarm)
    {
        // Create a new row of values to insert.
        ContentValues newValues = new ContentValues();
        DateTime dT = new DateTime(mContext);

        // Assign values for each column.
        newValues.put(KEY_ALARM_TITLE      , _alarm.getTitle());
        newValues.put(KEY_ALARM_FROMDATE   , _alarm.getDate());
        newValues.put(KEY_ALARM_TODATE     , _alarm.getToDate());
        newValues.put(KEY_ALARM_TIME       , _alarm.getTime());
        newValues.put(KEY_ALARM_DAYS       , _alarm.getDays());
        newValues.put(KEY_ALARM_INTERVAL   , _alarm.getInterval());
        newValues.put(KEY_ALARM_ICON       , _alarm.getpId());
        newValues.put(KEY_ALARM_ENABLED    , _alarm.getEnabled());

        // Update the row.
        return db.update(ALARM_TABLE, newValues, KEY_ALARM_TITLE + "=" + addQuotes(_name), null) > 0;
    }

    // Remove an alarm from the database using its name
    public boolean removeAlarm(String _name)
    {
        return db.delete(ALARM_TABLE, KEY_ALARM_TITLE + "=" + addQuotes(_name), null) > 0;
    }

    // Remove an alarm from the database using its id
    public boolean removeAlarm(long _id)
    {
        return db.delete(ALARM_TABLE, KEY_ALARM_ID + "=" + _id, null) > 0;
    }

    // get a cursor to all alarms
    public Cursor getAllAlarmCursor() {
        return db.query(ALARM_TABLE, null,
                null, null, null, null, null);
    }

    // get cursor to a particular alarm with id
    public Cursor setCursorAlarm(long _rowIndex) throws SQLException {
        Cursor result = db.query(true, ALARM_TABLE, null,
                KEY_ALARM_ID + "=" + _rowIndex, null, null, null,
                null, null);

        if ((result.getCount() == 0) || !result.moveToFirst()) {
            throw new SQLException("No to do items found for row: " + _rowIndex);
        }

        return result;
    }

    // get alarm object using id
    public Alarm getAlarm(long _rowIndex) throws SQLException {
        Cursor cursor = db.query(true, ALARM_TABLE,
                null, KEY_ALARM_ID + "=" + _rowIndex, null, null, null,
                null, null);

        if ((cursor.getCount() == 0) || !cursor.moveToFirst()) {
            throw new SQLException("No to do item found for row: " + _rowIndex);
        }

        return createAlarm(cursor);
    }

    public Alarm createAlarm(Cursor _cursor)
    {
        Alarm result = new Alarm(mContext);

        result.setId(_cursor.getInt(ALARM_ID_COLUMN));
        result.setTitle(_cursor.getString(ALARM_TITLE_COLUMN));
        result.setDate(_cursor.getLong(ALARM_FROMDATE_COLUMN));
        result.setToDate(_cursor.getLong(ALARM_TODATE_COLUMN));
        result.setTime(_cursor.getLong(ALARM_TIME_COLUMN));
        result.setDays(_cursor.getInt(ALARM_DAYS_COLUMN));

        // TODO: Assign MedicineID to each Alarm
        //result.set(_cursor.get(ALARM_MEDICINEID_COLUMN));
        result.setInterval(_cursor.getInt(ALARM_INTERVAL_COLUMN));
        result.setpId(_cursor.getInt(ALARM_ICON_COLUMN));
        result.setEnabled((_cursor.getInt(ALARM_ENABLED_COLUMN) == 1));

        return result;
    }

    // Insert a new history item
    public long addHistory(HistoryItem _historyItem)
    {
        // Create a new row of values to insert.
        ContentValues values = new ContentValues();

        // Assign values for each column.
        values.put(KEY_HISTORY_ALARMID, _historyItem.getAlarmId());
        values.put(KEY_HISTORY_TIMEDUE   , _historyItem.getTimeDue());
        values.put(KEY_HISTORY_TIMETAKEN , _historyItem.getTimeTaken());
        values.put(KEY_HISTORY_QRCODE, _historyItem.getQRCode());
        values.put(KEY_HISTORY_VALIDATION, _historyItem.getValidation());

        // Insert the row.
        return db.insert(HISTORY_TABLE, null, values);
    }

    // Update a history item
    public boolean updateHistory(long _id, HistoryItem _historyItem)
    {
        // Create a new row of values to insert.
        ContentValues newValues = new ContentValues();

        // Assign values for each column.
        newValues.put(KEY_HISTORY_TIMEDUE   , _historyItem.getTimeDue());
        newValues.put(KEY_HISTORY_TIMETAKEN , _historyItem.getTimeTaken());
        newValues.put(KEY_HISTORY_ALARMID, _historyItem.getAlarmId());
        newValues.put(KEY_HISTORY_QRCODE, _historyItem.getQRCode());
        newValues.put(KEY_HISTORY_VALIDATION, _historyItem.getValidation());

        // Update the row.
        return db.update(HISTORY_TABLE, newValues, KEY_HISTORY_ID + "=" + _id, null) > 0;
    }

    // Remove a history item from the database using its name
    public boolean removeHistory(String _name)
    {
        return db.delete(HISTORY_TABLE, KEY_ALARM_TITLE + "=" + addQuotes(_name), null) > 0;
    }

    // get a cursor to all history items
    public Cursor getAllHistoryCursor() {
        return db.query(HISTORY_TABLE, null,
                null, null, null, null, null);
    }

    public HistoryItem createHistoryItem(Cursor _cursor)
    {
        return new HistoryItem(mContext,
                _cursor.getInt(HISTORY_ID_COLUMN),
                _cursor.getInt(HISTORY_ALARMID_COLUMN),
                _cursor.getLong(HISTORY_TIMEDUE_COLUMN),
                _cursor.getLong(HISTORY_TIMETAKEN_COLUMN),
                _cursor.getString(HISTORY_QRCODE_COLUMN),
                _cursor.getString(HISTORY_VALIDATION_COLUMN)
                );
    }

    private String addQuotes(String msg)
    {
        return "\"" + msg + "\"";
    }

    public HistoryItem getLastHistoryItem()
    {
        Cursor cursor = db.query(HISTORY_TABLE, null, null, null, null, null, KEY_HISTORY_ID + " DESC", "1");

        if ((cursor.getCount() == 0) || !cursor.moveToFirst()) {
            throw new SQLException("No history item found");
        }

        return createHistoryItem(cursor);
    }

    // Insert a new patient
    public long addPatient(Patient _patient) {
        // Create a new row of values to insert.
        ContentValues values = new ContentValues();

        // Assign values for each column.
        values.put(KEY_PATIENT_NAME             , _patient.getPatientName());
        values.put(KEY_PATIENT_FATHERNAME       , _patient.getFatherName());
        values.put(KEY_PATIENT_AGE              , _patient.getAge());
        values.put(KEY_PATIENT_GENDER           , _patient.getGender());
        values.put(KEY_PATIENT_ETHNICITY        , _patient.getEthinicity());
        values.put(KEY_PATIENT_ADDRESS          , _patient.getAddress());
        values.put(KEY_PATIENT_MARITALSTATUS    , _patient.getMaritalStatus());
        values.put(KEY_PATIENT_PROFESSION       , _patient.getProfession());
        values.put(KEY_PATIENT_INFECTIONS       , _patient.getInfection());
        values.put(KEY_PATIENT_CHILDRENNO       , _patient.getChildrenno());
        values.put(KEY_PATIENT_CHILDRENSUFFER   , _patient.getChildrenSuffer());
        values.put(KEY_PATIENT_SPOUSESUFFER     , _patient.getSpouseSuffer());
        values.put(KEY_PATIENT_RISK             , _patient.getRiskBehaviour());
        values.put(KEY_PATIENT_TRAVELHISTORY    , _patient.getTravelHistory());
        values.put(KEY_PATIENT_FRIENDHISTORY    , _patient.getFriendHistory());
        values.put(KEY_PATIENT_CLINICALFEATURES , _patient.getClinicalFeatures());
        values.put(KEY_PATIENT_DIAGNOSISDATE    , _patient.getDiagnosisDate());

        // Insert the row.
        return db.insert(PATIENT_TABLE, null, values);
    }

    // Update a patient entry
    public boolean updatePatient(long _id, Patient _patient) {
        // Create a new row of values to insert.
        ContentValues newValues = new ContentValues();

        // Assign values for each column.
        newValues.put(KEY_PATIENT_NAME             , _patient.getPatientName());
        newValues.put(KEY_PATIENT_FATHERNAME       , _patient.getFatherName());
        newValues.put(KEY_PATIENT_AGE              , _patient.getAge());
        newValues.put(KEY_PATIENT_GENDER           , _patient.getGender());
        newValues.put(KEY_PATIENT_ETHNICITY        , _patient.getEthinicity());
        newValues.put(KEY_PATIENT_ADDRESS          , _patient.getAddress());
        newValues.put(KEY_PATIENT_MARITALSTATUS    , _patient.getMaritalStatus());
        newValues.put(KEY_PATIENT_PROFESSION       , _patient.getProfession());
        newValues.put(KEY_PATIENT_INFECTIONS       , _patient.getInfection());
        newValues.put(KEY_PATIENT_CHILDRENNO       , _patient.getChildrenno());
        newValues.put(KEY_PATIENT_CHILDRENSUFFER   , _patient.getChildrenSuffer());
        newValues.put(KEY_PATIENT_SPOUSESUFFER     , _patient.getSpouseSuffer());
        newValues.put(KEY_PATIENT_RISK             , _patient.getRiskBehaviour());
        newValues.put(KEY_PATIENT_TRAVELHISTORY    , _patient.getTravelHistory());
        newValues.put(KEY_PATIENT_FRIENDHISTORY    , _patient.getFriendHistory());
        newValues.put(KEY_PATIENT_CLINICALFEATURES , _patient.getClinicalFeatures());
        newValues.put(KEY_PATIENT_DIAGNOSISDATE    , _patient.getDiagnosisDate());

        // Insert the row.
        return db.update(PATIENT_TABLE, newValues, KEY_PATIENT_ID + "=" + _id, null) > 0;
    }

    // Remove a all patients
    public boolean removePatient() {
        return db.delete(PATIENT_TABLE, null, null) > 0;
    }

    // get a cursor to all patient profiles
    public Cursor getAllPatientCursor() {
        return db.query(PATIENT_TABLE, null,
                null, null, null, null, null);
    }

    // create and return a patient item from a cursor
    public Patient createPatient(Cursor _cursor)
    {
        Patient p = new Patient(mContext);

        p.setPatientName(_cursor.getString(PATIENT_NAME_COLUMN));
        p.setFatherName(_cursor.getString(PATIENT_FATHERNAME_COLUMN));
        p.setAge(_cursor.getInt(PATIENT_AGE_COLUMN));
        p.setGender(_cursor.getInt(PATIENT_GENDER_COLUMN));
        p.setEthinicity(_cursor.getInt(PATIENT_ETHNICITY_COLUMN));
        p.setAddress(_cursor.getString(PATIENT_ADDRESS_COLUMN));
        p.setMaritalStatus(_cursor.getInt(PATIENT_MARITALSTATUS_COLUMN));
        p.setInfections(_cursor.getInt(PATIENT_INFECTIONS_COLUMN));
        p.setProfession(_cursor.getString(PATIENT_PROFESSION_COLUMN));
        p.setChildrenno(_cursor.getString(PATIENT_CHILDRENNO_COLUMN));
        p.setChildrenSuffer(_cursor.getInt(PATIENT_CHILDRENSUFFER_COLUMN));
        p.setSpouseSuffer(_cursor.getInt(PATIENT_SPOUSESUFFER_COLUMN));
        p.setRiskBehaviour(_cursor.getInt(PATIENT_RISK_COLUMN));
        p.settravelHistory(_cursor.getInt(PATIENT_TRAVELHISTORY_COLUMN));
        p.setfriendHistory(_cursor.getInt(PATIENT_FRIENDHISTORY_COLUMN));
        p.setclinicalFeatures(_cursor.getInt(PATIENT_CLINICALFEATUR_COLUMN));
        p.setDiagnosisDate(_cursor.getLong(PATIENT_DIAGNOSISDATE_COLUMN));

        return p;
    }

}
