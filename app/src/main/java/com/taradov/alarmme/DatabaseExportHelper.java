package com.taradov.alarmme;

import android.app.ProgressDialog;
import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.security.Key;
import java.util.ArrayList;






import java.io.File;
import java.io.IOException;
import java.lang.Boolean;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;

import jxl.*;
import jxl.read.biff.BiffException;
import jxl.write.*;
import jxl.write.Number;
import jxl.write.biff.RowsExceededException;


/**
 * Created by Inshal on 14-Mar-15.
 */
//new async task for file export to csv



public class DatabaseExportHelper extends AsyncTask<String, String, Boolean> {
    private ProgressDialog dialog;
    private boolean memoryError = false;

    public DatabaseExportHelper(Context _context) {
        dialog = new ProgressDialog(_context);
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

        return success;
    }

    // close dialog and give msg
    protected void onPostExecute(Boolean success) {
        if (this.dialog.isShowing()) {
            this.dialog.dismiss();
        }

        // TODO: Understand how this code segment works

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



   private void Exportexcel(DBAdapter dbAdapter, Cursor cursor)

   {

       dbAdapter = new DBAdapter(this);
       dbAdapter.open();

       @Override
       public void onDestroy()
       {
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


       Workbook workbook = Workbook.createWorkbook(new File("output.xls");
       WritableSheet sheet;
       sheet = workbook.createSheet("Alarm", 0);

       // adding alarm table constants



       Label item = new Label(0, 0, DBAdapter.KEY_ALARM_TITLE);
       worksheet.addCell(item);

       item = new Label(0, 1, DBAdapter.KEY_ALARM_FROMDATE);
       worksheet.addCell(item);

       item = new Label(0, 2, DBAdapter.KEY_ALARM_TODATE);
       worksheet.addCell(item);

       item = new Label(0, 3, DBAdapter.KEY_ALARM_TIME;
       worksheet.addCell(item);

       item = new Label(0, 4, DBAdapter.KEY_ALARM_DAYS);
       worksheet.addCell(item);

       item = new Label(0, 5, DBAdapter.KEY_ALARM_MEDICINEID);
       worksheet.addCell(item);

       item = new Label(0, 6, DBAdapter.KEY_ALARM_INTERVAL);
       worksheet.addCell(item);

       item = new Label(0, 7, DBAdapter.KEY_ALARM_ICON);
       worksheet.addCell(item);

       item = new Label(0, 8, DBAdapter.KEY_ALARM_ENABLED);
       worksheet.addCell(item);




       sheet = workbook.createSheet("Medicine", 1);

       //medicine table constants

       Label item = new Label(0, 0, DBAdapter.KEY_MEDICINE_ID  );

       worksheet.addCell(item);

       item = new Label(0, 1, DBAdapter.KEY_MEDICINE_NAME);
       worksheet.addCell(item);

       item = new Label(0, 2, DBAdapter.KEY_MEDICINE_COLOR);
       worksheet.addCell(item);

       item = new Label(0, 3, DBAdapter.KEY_MEDICINE_AUDIO);
       worksheet.addCell(item);


       sheet = workbook.createSheet("History", 3);

       //history table constants
       Label item = new Label(0, 0, DBAdapter.KEY_HISTORY_ID);

       worksheet.addCell(item);

       item = new Label(0, 1, DBAdapter.KEY_HISTORY_ALARMID);
       worksheet.addCell(item);

       item = new Label(0, 2, DBAdapter.KEY_HISTORY_TIMEDUE);
       worksheet.addCell(item);

       item = new Label(0, 3, DBAdapter.KEY_HISTORY_TIMETAKEN);
       worksheet.addCell(item);

       item = new Label(0, 4, DBAdapter.KEY_HISTORY_QRCODE);
       worksheet.addCell(item);

       item = new Label(0, 5, DBAdapter.KEY_HISTORY_VALIDATION);
       worksheet.addCell(item);



       sheet = workbook.createSheet("Patient", 4);

       //patient table constansts
       Label item = new Label(0, 0, DBAdapter.KEY_PATIENT_ID);

       worksheet.addCell(item);

       item = new Label(0, 1, DBAdapter.KEY_PATIENT_NAME);
       worksheet.addCell(item);

       item = new Label(0, 2, DBAdapter.KEY_PATIENT_FATHERNAME);
       worksheet.addCell(item);

       item = new Label(0, 3, DBAdapter.KEY_PATIENT_AGE);
       worksheet.addCell(item);

       item = new Label(0, 4, DBAdapter.KEY_PATIENT_GENDER);
       worksheet.addCell(item);

       item = new Label(0, 5, DBAdapter.KEY_PATIENT_ETHNICITY);
       worksheet.addCell(item);
      \
       item = new Label(0, 6, DBAdapter.KEY_PATIENT_ADDRESS);
       worksheet.addCell(item);

       item = new Label(0, 7, DBAdapter.KEY_PATIENT_MARITALSTATUS);
       worksheet.addCell(item);

       item = new Label(0, 8, DBAdapter.KEY_PATIENT_PROFESSION);
       worksheet.addCell(item);

       item = new Label(0, 9, DBAdapter.KEY_PATIENT_INFECTIONS);
       worksheet.addCell(item);

       item = new Label(0, 10, DBAdapter.KEY_PATIENT_CHILDRENNO);
       worksheet.addCell(item);

       item = new Label(0, 11, DBAdapter.KEY_PATIENT_CHILDRENSUFFER);
       worksheet.addCell(item);

       item = new Label(0, 12, DBAdapter.KEY_PATIENT_SPOUSESUFFER);
       worksheet.addCell(item);

       item = new Label(0, 13, DBAdapter.KEY_PATIENT_RISK);
       worksheet.addCell(item);

       item = new Label(0, 14, DBAdapter.KEY_PATIENT_TRAVELHISTORY);
       worksheet.addCell(item);

       item = new Label(0, 15, DBAdapter.KEY_PATIENT_FRIENDHISTORY);
       worksheet.addCell(item);

       item = new Label(0, 16, DBAdapter.KEY_PATIENT_CLINICALFEATURES);
       worksheet.addCell(item);

       item = new Label(0, 17, DBAdapter.KEY_PATIENT_DIAGNOSISDATE);
       worksheet.addCell(item);






       private void exportToExcel(Cursor cursor) {
           final String fileName = "TodoList.xls";

           //Saving file in external storage
           File sdCard = Environment.getExternalStorageDirectory();
           File directory = new File(sdCard.getAbsolutePath() + "javatechig.todo");

           //create directory if not exist
           if(!directory.isDirectory()){
               directory.mkdirs();
           }

           //file path
           File file = new File(directory, fileName);

           WorkbookSettings wbSettings = new WorkbookSettings();
           wbSettings.setLocale(new Locale("en", "EN"));
           WritableWorkbook workbook;

           try {
               workbook = Workbook.createWorkbook(file, wbSettings);
               //Excel sheet name. 0 represents first sheet
               WritableSheet sheet = workbook.createSheet("Alarm", 0);


               try {
                   // column and row
                   sheet.addCell(new Label(0, 0, "Id"));
                   sheet.addCell(new Label(1, 0, "Title"));
                   sheet.addCell(new Label(2, 0, "FromDate"));
                   sheet.addCell(new Label(3, 0, "ToDate"));
                   sheet.addCell(new Label(4, 0, "Time"));
                   sheet.addCell(new Label(5, 0, "Days"));
                   sheet.addCell(new Label(6, 0, "MedicineId"));
                   sheet.addCell(new Label(7, 0, "Interval"));
                   sheet.addCell(new Label(8, 0, "Icon"));
                   sheet.addCell(new Label(9, 0, "Enabled"));


                   if (cursor.moveToFirst()) {
                       do {
                           String Id = cursor.getString(cursor.getColumnIndex(p.getId()));
                           String Title = cursor.getString(cursor.getColumnIndex(p.getgetTitle()));
                           String FromDate = cursor.getString(cursor.getColumnIndex(p.getFromDate()));
                           String ToDate = cursor.getString(cursor.getColumnIndex(p.getTodate()));
                           String Time = cursor.getString(cursor.getColumnIndex(p.getTime()));
                           String Days = cursor.getString(cursor.getColumnIndex(p.getDays()));
                           String MedicineId = cursor.getString(cursor.getColumnIndex(p.getMedicineId()));
                           String Interval = cursor.getString(cursor.getColumnIndex(p.getInterval()));
                           String Icon = cursor.getString(cursor.getColumnIndex(p.getIcon()));
                           String Enabled = cursor.getString(cursor.getColumnIndex(p.getEnabled()));


                           int i = cursor.getPosition() + 1;
                           sheet.addCell(new Label(0, i, Id));
                           sheet.addCell(new Label(1, i, format));
                           sheet.addCell(new Label(2, i, type));
                           sheet.addCell(new Label(1, i, name));
                           sheet.addCell(new Label(1, i, price));
                           sheet.addCell(new Label(1, i, quantity));
                       } while (cursor.moveToNext());
                   }
                   //closing cursor
                   cursor.close();
               }
               catch (RowsExceededException e)
               {
                   e.printStackTrace();
               } catch (WriteException e) {
                   e.printStackTrace();
               }
               workbook.write();
               try {
                   workbook.close();
               }
                    catch (WriteException e) {
                   e.printStackTrace();
               }
           } catch (IOException e) {
               e.printStackTrace();
           }
       }








}