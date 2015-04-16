package com.taradov.alarmme;

import android.content.Context;

/**
 * Created by Inshal on 14-Mar-15.
 */
public class HistoryItem {
    Context mContext;
    private int mId;
    private int mAlarmId;
    private long mTimeDue;
    private long mTimeTaken;

    public HistoryItem(Context _context, int _id, int _alarmId, long _timeDue, long _timeTaken) {
        mContext = _context;
        mId = _id;
        mAlarmId = _alarmId;
        mTimeDue = _timeDue;
        mTimeTaken = _timeTaken;
    }

    public int getId() {
        return mId;
    }

    public void setId(int _id) {
        mId = _id;
    }

    public long getTimeDue() {
        return mTimeDue;
    }

    public long getTimeTaken() {
        return mTimeTaken;
    }

    public int getAlarmId() {
        return mAlarmId;
    }

    public void setTimeDue(long _timeDue) {
        mTimeDue = _timeDue;
    }

    public void setTimeTaken(long _timeTaken) {
        mTimeTaken = _timeTaken;
    }

    public void setAlarmId(int _medicineId) {
        mAlarmId = _medicineId;
    }
}
