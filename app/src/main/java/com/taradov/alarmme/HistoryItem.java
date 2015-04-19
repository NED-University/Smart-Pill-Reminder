package com.taradov.alarmme;

import android.content.Context;
import android.content.Intent;

/**
 * Created by Inshal on 14-Mar-15.
 */
public class HistoryItem {
    Context mContext;
    private int mId;
    private int mAlarmId;
    private long mTimeDue;
    private long mTimeTaken;
    private String mQRCode;
    private String mValidation;

    public static final String MISSED = "Missed";
    public static final String TAKEN = "Taken";
    public static final String INCORRECT = "Incorrect";

    public HistoryItem( Context _context)
    {
        mContext = _context;
        mTimeDue = System.currentTimeMillis();
        mQRCode = "";
        mValidation = MISSED;
    }

    public HistoryItem( Context _context, int _id, int _alarmId, long _timeDue, long _timeTaken,
                        String _QRcode, String _validation)
    {
        mContext = _context;
        mId = _id;
        mAlarmId = _alarmId;
        mTimeDue = _timeDue;
        mTimeTaken = _timeTaken;
        mQRCode = _QRcode;
        mValidation = _validation;
    }

    public int getId()
    {
        return mId;
    }

    public void setId(int _id)
    {
        mId = _id;
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

    public void setQRCode(String _code){ mQRCode = _code; }

    public void setValidation(String _validation){ mValidation = _validation; }

    public String getQRCode () { return mQRCode; }

    public String getValidation () { return mValidation; }

    public void toIntent(Intent intent)
    {
        intent.putExtra("com.taradov.historyitem.id", mId);
        intent.putExtra("com.taradov.historyitem.alarmid", mAlarmId);
        intent.putExtra("com.taradov.historyitem.timedue", mTimeDue);
        intent.putExtra("com.taradov.historyitem.timetaken", mTimeTaken);
        intent.putExtra("com.taradov.historyitem.QRcode", mQRCode);
        intent.putExtra("com.taradov.historyitem.validation", mValidation);
    }

    public void fromIntent(Intent intent)
    {
        mId = intent.getIntExtra("com.taradov.historyitem.id", 0);
        mAlarmId = intent.getIntExtra("com.taradov.historyitem.alarmid", 0);
        mTimeDue = intent.getLongExtra("com.taradov.historyitem.timedue", System.currentTimeMillis());
        mTimeTaken = intent.getLongExtra("com.taradov.historyitem.timetaken", 0);
        mQRCode = intent.getStringExtra("com.taradov.historyitem.QRcode");
        mValidation = intent.getStringExtra("com.taradov.historyitem.validation");
    }
}
