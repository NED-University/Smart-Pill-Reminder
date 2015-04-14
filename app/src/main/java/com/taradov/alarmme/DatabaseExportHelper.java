package com.taradov.alarmme;

import android.app.ProgressDialog;
import android.content.Context;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.Boolean;
import java.sql.Date;
import java.text.SimpleDateFormat;

import jxl.*;
import jxl.write.*;
import jxl.write.biff.RowsExceededException;

/**
 * Created by Inshal on 14-Mar-15.
 */
//new async task for file export to csv
public class DatabaseExportHelper extends AsyncTask<String, String, Boolean> {
    private ProgressDialog dialog;
    private boolean memoryError = false;
    private Context mContext;
    private DBAdapter dbAdapter;
    private Cursor cursor;
    private DateTime dT;

    public final String fileName = "database.xls";

    public DatabaseExportHelper(Context _context, DBAdapter _db)
    {
        mContext = _context;
        dialog = new ProgressDialog(_context);
        dbAdapter = _db;
        dT = new DateTime(mContext);
    }

    // to show Loading dialog box
    @Override
    protected void onPreExecute() {
        this.dialog.setMessage("Exporting database...");
        this.dialog.show();
    }

    // TODO: Export the database as an excel file
    protected Boolean doInBackground(final String... args) {
        boolean success = false;

        WritableWorkbook workbook;

        try {
            //Saving file in external storage
            String dir = Environment.getExternalStorageDirectory().toString();
            //create folder
            File folder = new File(dir); //folder name
//            folder.mkdirs();

            //create file
            File file = new File(dir, "database.xls");

            if(file.exists())
            {
                file.delete();
            }

            file.createNewFile();

            if (file.exists())
            {
                workbook = Workbook.createWorkbook(file);

                createAlarmTable(workbook);

                workbook.write();

                success = true;

                try {
                    workbook.close();
                } catch (WriteException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return success;
    }

    protected void createAlarmTable(WritableWorkbook workbook)
    {
        try {
            WritableSheet sheet = workbook.createSheet(DBAdapter.ALARM_TABLE, 0);

            int col = 0;

            // Add labels for the first row
            sheet.addCell(new Label(col++, 0, DBAdapter.KEY_ALARM_ID));
            sheet.addCell(new Label(col++, 0, DBAdapter.KEY_ALARM_TITLE));
            sheet.addCell(new Label(col++, 0, DBAdapter.KEY_ALARM_FROMDATE));
            sheet.addCell(new Label(col++, 0, DBAdapter.KEY_ALARM_TODATE));
            sheet.addCell(new Label(col++, 0, DBAdapter.KEY_ALARM_TIME));
            sheet.addCell(new Label(col++, 0, DBAdapter.KEY_ALARM_DAYS));
            sheet.addCell(new Label(col++, 0, DBAdapter.KEY_ALARM_MEDICINEID));
            sheet.addCell(new Label(col++, 0, DBAdapter.KEY_ALARM_INTERVAL));
            sheet.addCell(new Label(col++, 0, DBAdapter.KEY_ALARM_ICON));
            sheet.addCell(new Label(col++, 0, DBAdapter.KEY_ALARM_ENABLED));

            cursor = dbAdapter.getAllAlarmCursor();

            if (cursor.moveToFirst()) {
                // start from second row, first is for labels
                int row = 1;
                Alarm alarm = dbAdapter.createAlarm(cursor);

                do {
                    col = 0;

                    sheet.addCell(new Label(col++, row, String.valueOf(alarm.getId())));
                    sheet.addCell(new Label(col++, row, alarm.getTitle()));
                    sheet.addCell(new Label(col++, row, dT.formatFromDate(alarm)));
                    sheet.addCell(new Label(col++, row, dT.formatToDate(alarm)));
                    sheet.addCell(new Label(col++, row, dT.formatTime(alarm)));
                    sheet.addCell(new Label(col++, row, dT.formatDays(alarm)));
                    // TODO: Get human readable form of medicine id
                    sheet.addCell(new Label(col++, row, String.valueOf(alarm.getMedId())));
                    // TODO: Get human readable form of interval
                    sheet.addCell(new Label(col++, row, String.valueOf(alarm.getInterval())));
                    // TODO: Get human readable form of picture id
                    sheet.addCell(new Label(col++, row, String.valueOf(alarm.getpId())));
                    sheet.addCell(new Label(col++, row, String.valueOf(alarm.getEnabled())));

                    row++;
                } while (cursor.moveToNext());
            }
        } catch (RowsExceededException e)
        {
            e.printStackTrace();
        } catch (WriteException e) {
            e.printStackTrace();
        }
    }

    // close dialog and give msg
    protected void onPostExecute(Boolean success) {
        if (this.dialog.isShowing()) {
            this.dialog.dismiss();
        }

        // TODO: Create similar dialog boxes

//        if (success) {
//            dialogBox();
//        } else {
//            if (memoryError ==true) {
//                dialogBox(SyncStateContract.Constants.Flag.FLAG_MEMORY_ERR);
//            } else {
//                dialogBox(SyncStateContract.Constants.Flag.FLAG_EXPRT_F);
//            }
//        }
    }
}