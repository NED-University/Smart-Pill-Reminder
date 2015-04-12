package com.taradov.alarmme;

import android.content.Context;

import java.lang.String;

/**
 * Created by Inshal on 21-Mar-15.
 */

public class Medicine {
    private Context mContext;
    private int mId;
    private String mName;
    private int mColor;
    private int mAudio;
    private String QRcode;

    public Medicine(Context _context, int _id, String _name, int _color, int _mAudio, String _QRcode)
    {
        mContext = _context;
        mId = _id;
        mName = _name;
        mColor = _color;
        mAudio = _mAudio;
        QRcode =_QRcode;
    }

    public void setId(int _id)
    {
        mId = _id;
    }

    public void setName(String _name)
    {
        mName = _name;
    }

    public void setColor(int _color)
    {
        mColor = _color;
    }

    public void setAudio(int _audio)
    {
        mAudio = _audio;
    }
    public void setQRcode(String _QRcode)
    {
        QRcode = _QRcode;
    }


    public int getId()
    {
        return mId;
    }

    public String getName()
    {
        return mName;
    }

    public int getColor()
    {
        return mColor;
    }

    public int getAudio()
    {
        return mAudio;
    }

    public String getQRcode()
    {
        return QRcode;
    }
}

