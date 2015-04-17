package com.taradov.alarmme;

import android.app.ProgressDialog;
import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Environment;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
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
    private DateTime dateTime;
    private DateFormat dateFormat;

    public final String fileName = "database.xls";

    public DatabaseExportHelper(Context _context, DBAdapter _db)
    {
        mContext = _context;
        dialog = new ProgressDialog(_context);
        dbAdapter = _db;
        dateTime = new DateTime(mContext);
        dateFormat = DateFormat.getDateTimeInstance();
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
                createMedicineTable(workbook);
                createHistoryTable(workbook);
                createPatientTable(workbook);

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
            sheet.addCell(new Label(col++, 0, DBAdapter.KEY_ALARM_AUDIO));
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
                    sheet.addCell(new Label(col++, row, dateTime.formatFromDate(alarm)));
                    sheet.addCell(new Label(col++, row, dateTime.formatToDate(alarm)));
                    sheet.addCell(new Label(col++, row, dateTime.formatTime(alarm)));
                    sheet.addCell(new Label(col++, row, dateTime.formatDays(alarm)));
                    // TODO: Get human readable form of audio
                    sheet.addCell(new Label(col++, row, String.valueOf(alarm.getAudio())));
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

    protected void createMedicineTable(WritableWorkbook workbook)
    {
        try {
            WritableSheet sheet = workbook.createSheet(DBAdapter.MEDICINE_TABLE, 1);

            int col = 0;

            // Add labels for the first row
            sheet.addCell(new Label(col++, 0, DBAdapter.KEY_MEDICINE_ID));
            sheet.addCell(new Label(col++, 0, DBAdapter.KEY_MEDICINE_NAME));
            sheet.addCell(new Label(col++, 0, DBAdapter.KEY_MEDICINE_COLOR));
            sheet.addCell(new Label(col++, 0, DBAdapter.KEY_MEDICINE_AUDIO));
            sheet.addCell(new Label(col++, 0, DBAdapter.KEY_MEDICINE_QRCODE));

            cursor = dbAdapter.getAllMedicineCursor();

            if (cursor.moveToFirst()) {
                // start from second row, first is for labels
                int row = 1;
                Medicine medicine = dbAdapter.createMedicineItem(cursor);

                do {
                    col = 0;

                    sheet.addCell(new Label(col++, row, String.valueOf(medicine.getId())));
                    sheet.addCell(new Label(col++, row, medicine.getName()));
                    sheet.addCell(new Label(col++, row, String.valueOf(medicine.getColor())));
                    sheet.addCell(new Label(col++, row, String.valueOf(medicine.getAudio())));
                    sheet.addCell(new Label(col++, row, medicine.getQRcode()));

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

    protected void createHistoryTable(WritableWorkbook workbook)
    {
        try {
            WritableSheet sheet = workbook.createSheet(DBAdapter.HISTORY_TABLE, 2);

            int col = 0;

            // Add labels for the first row
            sheet.addCell(new Label(col++, 0, DBAdapter.KEY_HISTORY_ID));
            sheet.addCell(new Label(col++, 0, DBAdapter.KEY_HISTORY_ALARMID));
            sheet.addCell(new Label(col++, 0, DBAdapter.KEY_HISTORY_TIMEDUE));
            sheet.addCell(new Label(col++, 0, DBAdapter.KEY_HISTORY_TIMETAKEN));
            sheet.addCell(new Label(col++, 0, DBAdapter.KEY_HISTORY_QRCODE));
            sheet.addCell(new Label(col++, 0, DBAdapter.KEY_HISTORY_VALIDATION));


            cursor = dbAdapter.getAllHistoryCursor();

            if (cursor.moveToFirst()) {
                // start from second row, first is for labels
                int row = 1;
                HistoryItem historyItem = dbAdapter.createHistoryItem(cursor);


                do {
                    col = 0;

                    sheet.addCell(new Label(col++, row, String.valueOf(historyItem.getId())));
                    sheet.addCell(new Label(col++, row, String.valueOf(historyItem.getAlarmId())));
                    sheet.addCell(new Label(col++, row, formatDate(historyItem.getTimeDue())));
                    sheet.addCell(new Label(col++, row, formatDate(historyItem.getTimeTaken())));
                    sheet.addCell(new Label(col++, row, historyItem.getQRCode()));
                    sheet.addCell(new Label(col++, row, historyItem.getValidation()));

                    row++;
                } while (cursor.moveToNext());
            }
        } catch (RowsExceededException e) {
            e.printStackTrace();
        } catch (WriteException e) {
            e.printStackTrace();
        }
    }

    protected void createPatientTable(WritableWorkbook workbook)
    {
        try {
            WritableSheet sheet = workbook.createSheet(DBAdapter.PATIENT_TABLE, 4);

            int col = 0;

            // Add labels for the first row
            sheet.addCell(new Label(col++, 0, DBAdapter.KEY_PATIENT_ID              ));
            sheet.addCell(new Label(col++, 0, DBAdapter.KEY_PATIENT_NAME            ));
            sheet.addCell(new Label(col++, 0, DBAdapter.KEY_PATIENT_FATHERNAME      ));
            sheet.addCell(new Label(col++, 0, DBAdapter.KEY_PATIENT_AGE             ));
            sheet.addCell(new Label(col++, 0, DBAdapter.KEY_PATIENT_GENDER          ));
            sheet.addCell(new Label(col++, 0, DBAdapter.KEY_PATIENT_ETHNICITY       ));
            sheet.addCell(new Label(col++, 0, DBAdapter.KEY_PATIENT_ADDRESS         ));
            sheet.addCell(new Label(col++, 0, DBAdapter.KEY_PATIENT_MARITALSTATUS   ));
            sheet.addCell(new Label(col++, 0, DBAdapter.KEY_PATIENT_PROFESSION      ));
            sheet.addCell(new Label(col++, 0, DBAdapter.KEY_PATIENT_INFECTIONS      ));
            sheet.addCell(new Label(col++, 0, DBAdapter.KEY_PATIENT_CHILDRENNO      ));
            sheet.addCell(new Label(col++, 0, DBAdapter.KEY_PATIENT_CHILDRENSUFFER  ));
            sheet.addCell(new Label(col++, 0, DBAdapter.KEY_PATIENT_SPOUSESUFFER    ));
            sheet.addCell(new Label(col++, 0, DBAdapter.KEY_PATIENT_RISK            ));
            sheet.addCell(new Label(col++, 0, DBAdapter.KEY_PATIENT_TRAVELHISTORY   ));
            sheet.addCell(new Label(col++, 0, DBAdapter.KEY_PATIENT_FRIENDHISTORY   ));
            sheet.addCell(new Label(col++, 0, DBAdapter.KEY_PATIENT_CLINICALFEATURES));
            sheet.addCell(new Label(col++, 0, DBAdapter.KEY_PATIENT_CD4             ));
            sheet.addCell(new Label(col++, 0, DBAdapter.KEY_PATIENT_VIRALLOAD       ));
            sheet.addCell(new Label(col++, 0, DBAdapter.KEY_PATIENT_DIAGNOSISDATE   ));

            cursor = dbAdapter.getAllPatientCursor();

            if (cursor.moveToFirst()) {
                // start from second row, first is for labels
                int row = 1;
                Patient patient = dbAdapter.createPatient(cursor);

                do {
                    col = 0;

                    sheet.addCell(new Label(col++, row, String.valueOf(patient.getPatId())));
                    sheet.addCell(new Label(col++, row, patient.getPatientName()));
                    sheet.addCell(new Label(col++, row, patient.getFatherName()));
                    sheet.addCell(new Label(col++, row, patient.getAge()));
                    sheet.addCell(new Label(col++, row, patient.getGender()));
                    sheet.addCell(new Label(col++, row, patient.getEthnicity()));
                    sheet.addCell(new Label(col++, row, patient.getAddress()));
                    sheet.addCell(new Label(col++, row, patient.getMaritalStatus()));
                    sheet.addCell(new Label(col++, row, patient.getProfession()));
                    sheet.addCell(new Label(col++, row, patient.getInfection()));
                    sheet.addCell(new Label(col++, row, patient.getChildrenno()));
                    sheet.addCell(new Label(col++, row, patient.getChildrenSuffer()));
                    sheet.addCell(new Label(col++, row, patient.getSpouseSuffer()));
                    sheet.addCell(new Label(col++, row, patient.getRiskBehaviour()));
                    sheet.addCell(new Label(col++, row, patient.getTravelHistory()));
                    sheet.addCell(new Label(col++, row, patient.getFriendHistory()));
//                    sheet.addCell(new Label(col++, row, patient.getClinicalFeatures));
                    col++;
                    sheet.addCell(new Label(col++, row, patient.getcd4()));
                    sheet.addCell(new Label(col++, row, patient.getViralLoad()));
                    sheet.addCell(new Label(col++, row, patient.getDiagnosisDate()));

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

    private String formatDate(long date)
    {
        return (date != 0) ? dateFormat.format(new Date(date)) : "";
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