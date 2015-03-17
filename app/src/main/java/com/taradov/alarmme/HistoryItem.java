package com.taradov.alarmme;

/**
 * Created by Inshal on 14-Mar-15.
 */
public class HistoryItem {
    private int mAlarmId;
    private long mTimeDue;
    private long mTimeTaken;

    public HistoryItem( int _alarmId, long _timeDue, long _timeTaken)
    {
        mAlarmId = _alarmId;
        mTimeDue = _timeDue;
        mTimeTaken = _timeTaken;
    }

    public long getTimeDue()
    {
        return mTimeDue;
    }

    public long getTimeTaken()
    {
        return mTimeTaken;
    }

    public int getAlarmId()
    {
        return mAlarmId;
    }


    public void setTimeDue(long _timeDue)
    {
        mTimeDue = _timeDue;
    }

    public void setTimeTaken(long _timeTaken)
    {
        mTimeTaken = _timeTaken;
    }

    public void setAlarmId(int _medicineId)
    {
        mAlarmId = _medicineId;
    }
}
