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
    public static final String KEY_ALARM_ATTIME     = "AtTime";
    public static final String KEY_ALARM_DAYS       = "Days";
    public static final String KEY_ALARM_MEDICINEID = "MedicineId";
    public static final String KEY_ALARM_INTERVAL   = "Interval";
    public static final String KEY_ALARM_ICON       = "Icon";
    public static final String KEY_ALARM_ENABLED    = "Enabled";

    // TODO: Check consistency with attribute names
    public static final int ALARM_TITLE_COLUMN      = 1;
    public static final int ALARM_FROMDATE_COLUMN   = 2;
    public static final int ALARM_TODATE_COLUMN     = 3;
    public static final int ALARM_ATTIME_COLUMN     = 4;
    public static final int ALARM_DAYS_COLUMN       = 5;
    public static final int ALARM_MEDICINEID_COLUMN = 6;
    public static final int ALARM_INTERVAL_COLUMN   = 7;
    public static final int ALARM_ICON_COLUMN       = 8;
    public static final int ALARM_ENABLED_COLUMN    = 9;

    private static final String[] AlarmTableColumns= new String[] { KEY_ALARM_ID, KEY_ALARM_TITLE, KEY_ALARM_FROMDATE,
            KEY_ALARM_TODATE, KEY_ALARM_ATTIME, KEY_ALARM_DAYS, KEY_ALARM_INTERVAL, KEY_ALARM_ICON, KEY_ALARM_ENABLED};

    // Medicine Table Constants
    private static final String MEDICINE_TABLE = "Medicine";

    public static final String KEY_MEDICINE_ID      = "Id";
    public static final String KEY_MEDICINE_NAME    = "Name";
    public static final String KEY_MEDICINE_COLOR   = "Color";
    public static final String KEY_MEDICINE_AUDIO   = "Audio";

    public static final int MEDICINE_NAME_COLUMN    = 1;
    public static final int MEDICINE_COLOR_COLUMN   = 2;
    public static final int MEDICINE_AUDIO_COLUMN   = 3;

    // Validation Table Constants
    private static final String HISTORY_TABLE = "MedicationHistory";

    public static final String KEY_HISTORY_ID           = "Id";
    public static final String KEY_HISTORY_ALARMID      = "AlarmId";
    public static final String KEY_HISTORY_TIMEDUE      = "TimeDue";
    public static final String KEY_HISTORY_TIMETAKEN    = "TimeTaken";

    public static final int HISTORY_ALARMID_COLUMN      = 1;
    public static final int HISTORY_TIMEDUE_COLUMN      = 2;
    public static final int HISTORY_TIMETAKEN_COLUMN    = 3;

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
                KEY_ALARM_TODATE      + " long not null, " +
                KEY_ALARM_ATTIME      + " long not null, " +
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
                KEY_MEDICINE_AUDIO  + " integer " +
                ");";

        private static final String CREATE_TABLE_VALIDATION =
                "create table " + HISTORY_TABLE + " (" +
                KEY_HISTORY_ID         + " integer primary key autoincrement, " +
                KEY_HISTORY_ALARMID    + " integer not null,"  +
                KEY_HISTORY_TIMEDUE    + " long not null, " +
                KEY_HISTORY_TIMETAKEN  + " text " +
                ");";

        @Override
        public void onCreate(SQLiteDatabase _db) {
            _db.execSQL(CREATE_TABLE_ALARM);
            _db.execSQL(CREATE_TABLE_MEDICINE);
            _db.execSQL(CREATE_TABLE_VALIDATION);
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
    public long addMedicine(String _name) {
        // Create a new row of values to insert.
        ContentValues values = new ContentValues();
        // Assign values for each column.
        values.put(KEY_MEDICINE_NAME, _name);

        // Insert the row.
        return db.insert(MEDICINE_TABLE, null, values);
    }

    // Update a medicine entry
    public boolean updateMedicine(long _id, String _name) {
        // Create a new row of values to insert.
        ContentValues newValues = new ContentValues();
        // Assign values for each column.
        newValues.put(KEY_MEDICINE_NAME, _name);

        // Insert the row.
        return db.update(MEDICINE_TABLE, newValues, KEY_MEDICINE_ID + "=" + _id, null) > 0;
    }

    // Remove a medicine
    public boolean removeMedicine(String _name) {
        return db.delete(MEDICINE_TABLE, KEY_MEDICINE_NAME + "=" + _name, null) > 0;
    }

    // Insert a new alarm
    public long addAlarm(Alarm _alarm)
    {
        // Create a new row of values to insert.
        ContentValues values = new ContentValues();
        DateTime dT = new DateTime(mContext);

        // TODO: Adopt this to changes to the new Alarm class

        // Assign values for each column.
        values.put(KEY_ALARM_ID, _alarm.getId());
        values.put(KEY_ALARM_TITLE, _alarm.getTitle());
        values.put(KEY_ALARM_FROMDATE, dT.formatDate(_alarm));
        values.put(KEY_ALARM_DAYS, dT.formatDays(_alarm));
        values.put(KEY_ALARM_ATTIME, dT.formatTime(_alarm));
        values.put(KEY_ALARM_INTERVAL, _alarm.getDays());
        values.put(KEY_ALARM_ICON, _alarm.getpId());

        // Insert the row.
        return db.insert(ALARM_TABLE, null, values);
    }

    // Update an alarm entry
    public boolean updateAlarm(long _id, Alarm _alarm)
    {
        // Create a new row of values to insert.
        ContentValues newValues = new ContentValues();
        DateTime dT = new DateTime(mContext);

        // TODO: Adopt this to changes to the new Alarm class

        // Assign values for each column.
        newValues.put(KEY_ALARM_ID, _alarm.getId());
        newValues.put(KEY_ALARM_TITLE, _alarm.getTitle());
        newValues.put(KEY_ALARM_FROMDATE, dT.formatDate(_alarm));
        newValues.put(KEY_ALARM_DAYS, dT.formatDays(_alarm));
        newValues.put(KEY_ALARM_ATTIME, dT.formatTime(_alarm));
        newValues.put(KEY_ALARM_INTERVAL, _alarm.getDays());
        newValues.put(KEY_ALARM_ICON, _alarm.getpId());

        // Update the row.
        return db.update(ALARM_TABLE, newValues, KEY_ALARM_ID + "=" + _id, null) > 0;
    }

    // Remove an alarm from the database using its name
    public boolean removeAlarm(String _name)
    {
        return db.delete(ALARM_TABLE, KEY_ALARM_TITLE + "=" + _name, null) > 0;
    }

    // Remove an alarm from the database using its id
    public boolean removeAlarm(long _id)
    {
        return db.delete(ALARM_TABLE, KEY_ALARM_ID + "=" + _id, null) > 0;
    }

    // Insert a new alarm
    public long addHistory(HistoryItem _historyItem)
    {
        // Create a new row of values to insert.
        ContentValues values = new ContentValues();

        // Assign values for each column.
        values.put(KEY_HISTORY_TIMEDUE   , _historyItem.getTimeDue());
        values.put(KEY_HISTORY_TIMETAKEN , _historyItem.getTimeTaken());
        values.put(KEY_HISTORY_ALARMID, _historyItem.getAlarmId());

        // Insert the row.
        return db.insert(ALARM_TABLE, null, values);
    }

    // Update an alarm entry
    public boolean updateHistory(long _id, HistoryItem _historyItem)
    {
        // Create a new row of values to insert.
        ContentValues newValues = new ContentValues();

        // Assign values for each column.
        newValues.put(KEY_HISTORY_TIMEDUE   , _historyItem.getTimeDue());
        newValues.put(KEY_HISTORY_TIMETAKEN , _historyItem.getTimeTaken());
        newValues.put(KEY_HISTORY_ALARMID, _historyItem.getAlarmId());

        // Update the row.
        return db.update(HISTORY_TABLE, newValues, KEY_HISTORY_ID + "=" + _id, null) > 0;
    }

    // Remove an alarm from the database using its name
    public boolean removeHistory(String _name)
    {
        return db.delete(ALARM_TABLE, KEY_ALARM_TITLE + "=" + _name, null) > 0;
    }

    public Cursor getAllAlarmCursor() {
        return db.query(ALARM_TABLE, AlarmTableColumns,
        null, null, null, null, null);
    }

    public Cursor setCursorAlarm(long _rowIndex) throws SQLException {
        Cursor result = db.query(true, ALARM_TABLE, AlarmTableColumns,
                KEY_ALARM_ID + "=" + _rowIndex, null, null, null,
                null, null);

        if ((result.getCount() == 0) || !result.moveToFirst()) {
            throw new SQLException("No to do items found for row: " + _rowIndex);
        }

        return result;
    }

    public Alarm getAlarm(long _rowIndex) throws SQLException {
        Cursor cursor = db.query(true, ALARM_TABLE,
                AlarmTableColumns,
                KEY_ALARM_ID + "=" + _rowIndex, null, null, null,
                null, null);

        if ((cursor.getCount() == 0) || !cursor.moveToFirst()) {
            throw new SQLException("No to do item found for row: " + _rowIndex);
        }

        Alarm result = new Alarm(mContext);

        result.setTitle(cursor.getString(ALARM_TITLE_COLUMN));
        result.setId(_rowIndex);
//        result.setDate();
//        result.setDays();
//        result.setInterval();
//        result.setpId();

        return result;
    }
}
