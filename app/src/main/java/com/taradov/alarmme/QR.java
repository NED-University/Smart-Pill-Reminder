/**************************************************************************
 *
 * Copyright (C) 2012 Alex Taradov <taradov@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 *************************************************************************/

package com.taradov.alarmme;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QR extends Activity {
	TextView tvStatus;
	TextView tvResult;

    private HistoryItem historyItem;
    private DBAdapter dbAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.qr);

		tvStatus = (TextView) findViewById(R.id.tvStatus);
		tvResult = (TextView) findViewById(R.id.tvResult);

		Button scanBtn = (Button) findViewById(R.id.btnScan);

		//in some trigger function e.g. button press within your code you should add:
		scanBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				try {

					Intent intent = new Intent(
							"com.google.zxing.client.android.SCAN");
					intent.putExtra("SCAN_MODE", "QR_CODE_MODE,PRODUCT_MODE");
					startActivityForResult(intent, 0);

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					Toast.makeText(getApplicationContext(), "ERROR:" + e, Toast.LENGTH_LONG).show();
				}
			}
		});

        historyItem = new HistoryItem(this);
        historyItem.fromIntent(getIntent());

        dbAdapter = new DBAdapter(this);
        dbAdapter.open();
	}

	//In the same activity you�ll need the following to retrieve the results:
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		if (requestCode == 0) {

			if (resultCode == RESULT_OK) {
				tvStatus.setText(intent.getStringExtra("SCAN_RESULT_FORMAT"));
				tvResult.setText(intent.getStringExtra("SCAN_RESULT"));

                dbAdapter.updateHistory(historyItem.getAlarmId(), historyItem);
            } else if (resultCode == RESULT_CANCELED) {
				tvStatus.setText("Press a button to start a scan.");
				tvResult.setText("Scan cancelled.");
			}
		}
	}

    @Override
    public void onDestroy() {
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

    @Override
    public void onStart()
    {
        super.onStart();
        dbAdapter.open();
    }
}
