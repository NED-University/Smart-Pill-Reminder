package com.taradov.alarmme;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.lang.Boolean;
import java.sql.Date;
import java.text.SimpleDateFormat;

import jxl.*;
import jxl.write.*;

/**
 * Created by Inshal on 14-Mar-15.
 */
//new async task for file export to csv
public class DatabaseExportHelper extends AsyncTask<String, String, Boolean> {
    private ProgressDialog dialog;
    private boolean memoryError = false;

    public DatabaseExportHelper(Context _context)
    {
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
}