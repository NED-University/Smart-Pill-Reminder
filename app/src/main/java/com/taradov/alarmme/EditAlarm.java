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

import java.util.Calendar;
import java.util.GregorianCalendar;

import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.text.TextWatcher;
import android.text.Editable;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Spinner;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.content.Intent;
import android.content.Context;
import android.content.DialogInterface;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class EditAlarm extends Activity implements OnItemClickListener
{
	private MultiAutoCompleteTextView mTitle;
	private CheckBox mAlarmEnabled;
	private Spinner mOccurence;
	private Spinner mInterval;
	private Button mDateButton;
	private Button mTimeButton;
	private Button mColorPickerButton;
	private LinearLayout mLayout;
	private EditText mEditText;
	GridView menu;

	private String[] menu_text = {"Inhaler","Capsule","Syringe"};

	public String[] medicine={"ARINAC","PANADOL","KLARACID","DISPIRIN"};

	private Integer[] menu_icon = {
			R.drawable.inhaler,
			R.drawable.capsule,
			R.drawable.syringe}; 


	private Alarm mAlarm;
	private DateTime mDateTime;

	private GregorianCalendar mCalendar;
	private int mYear;
	private int mMonth;
	private int mDay;
	private int mHour;
	private int mMinute;

	static final int DATE_DIALOG_ID = 0;
	static final int TIME_DIALOG_ID = 1;
	static final int DAYS_DIALOG_ID = 2;
	public int selectedid;

    private DBAdapter dbAdapter;
    private Cursor cursor;
    private boolean editAlarm;
    private String alarmName;

	@Override
	public void onCreate(Bundle bundle)
	{
		super.onCreate(bundle);
		setContentView(R.layout.edit);
		menu = (GridView)findViewById(R.id.Menu);
		menu.setAdapter(new ImageAdapter(this));
		mLayout = (LinearLayout) findViewById(R.id.linearLayout);

				ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,medicine);
		////		AutoCompleteTextView textView = (AutoCompleteTextView)
		////				findViewById(R.id.txtCountries);
		//
				 mTitle =(MultiAutoCompleteTextView) findViewById (R.id.editText);
				mTitle.setThreshold(1);
				mTitle.setAdapter(adapter);
				 mTitle.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());

		TextView textView = new TextView(this);
		textView.setText("New text");
		
		

		menu.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				if (position == 0) {
					Toast.makeText(getBaseContext(),
							"Inhaler was clicked",
							Toast.LENGTH_LONG).show();
					selectedid=menu_icon[position];

					mAlarm.setpId(selectedid);
				}

				if (position == 1) {
					Toast.makeText(getBaseContext(),
							"Capsule was clicked",
							Toast.LENGTH_LONG).show();
					selectedid=menu_icon[position];

					mAlarm.setpId(selectedid);

				}
				if (position == 2) {
					Toast.makeText(getBaseContext(),
							"Syringe was clicked",
							Toast.LENGTH_LONG).show();
					selectedid=menu_icon[position];

					mAlarm.setpId(selectedid);

				}

			}    });

		//mTitle = (MultiAutoCompleteTextView)findViewById(R.id.editText);
		mAlarmEnabled = (CheckBox)findViewById(R.id.alarm_checkbox);
		mOccurence = (Spinner)findViewById(R.id.occurence_spinner);
		mInterval = (Spinner)findViewById(R.id.interval_spinner);
		mDateButton = (Button)findViewById(R.id.date_button);
		mTimeButton = (Button)findViewById(R.id.time_button);
		mColorPickerButton = (Button)findViewById(R.id.color_picker);

		mAlarm = new Alarm(this);
		mAlarm.fromIntent(getIntent());

		mDateTime = new DateTime(this);

		mTitle.setText(mAlarm.getTitle());

		mTitle.addTextChangedListener(mTitleChangedListener);

		mOccurence.setSelection(mAlarm.getOccurence());
		mOccurence.setOnItemSelectedListener(mOccurenceSelectedListener); 


		mInterval.setSelection(mAlarm.getInterval());
		mInterval.setOnItemSelectedListener(mIntervalSelectedListener); 

		mAlarmEnabled.setChecked(mAlarm.getEnabled());
		mAlarmEnabled.setOnCheckedChangeListener(mAlarmEnabledChangeListener); 

		mCalendar = new GregorianCalendar();
		mCalendar.setTimeInMillis(mAlarm.getFromDate());
		mYear = mCalendar.get(Calendar.YEAR);
		mMonth = mCalendar.get(Calendar.MONTH);
		mDay = mCalendar.get(Calendar.DAY_OF_MONTH);
		mHour = mCalendar.get(Calendar.HOUR_OF_DAY);
		mMinute = mCalendar.get(Calendar.MINUTE);

		updateButtons();

        dbAdapter = new DBAdapter(this);
        dbAdapter.open();
        alarmName = mAlarm.getTitle();
        editAlarm = getIntent().getBooleanExtra("EDIT", false);
	}

	@Override
	protected Dialog onCreateDialog(int id)
	{
		if (DATE_DIALOG_ID == id)
			return new DatePickerDialog(this, mDateSetListener, mYear, mMonth, mDay);
		else if (TIME_DIALOG_ID == id)
			return new TimePickerDialog(this, mTimeSetListener, mHour, mMinute, mDateTime.is24hClock());
		else if (DAYS_DIALOG_ID == id)
			return DaysPickerDialog();
		else
			return null;
	}

	@Override
	protected void onPrepareDialog(int id, Dialog dialog)
	{
		if (DATE_DIALOG_ID == id)
			((DatePickerDialog)dialog).updateDate(mYear, mMonth, mDay);
		else if (TIME_DIALOG_ID == id)
			((TimePickerDialog)dialog).updateTime(mHour, mMinute);
	}    

	public void onDateClick(View view)
	{
		if (Alarm.ONCE == mAlarm.getOccurence())
			showDialog(DATE_DIALOG_ID);
		else if (Alarm.WEEKLY == mAlarm.getOccurence())
			showDialog(DAYS_DIALOG_ID);
	}

	public void onTimeClick(View view)
	{
		showDialog(TIME_DIALOG_ID);
	}

	public void onColorClick(View view)
	{
		Intent intent = new Intent(EditAlarm.this,ColorPickerActivity.class);
		startActivity(intent);
	}

	public void onDoneClick(View view)
	{
		Intent intent = new Intent();

		mAlarm.toIntent(intent);
        // TODO: Get medicine ID
        if(editAlarm)
        {
            dbAdapter.updateAlarm(alarmName, mAlarm);
        }
        else
            dbAdapter.addAlarm(mAlarm, 0);
		setResult(RESULT_OK, intent);
		finish();
	}

	public void onCancelClick(View view)
	{
		setResult(RESULT_CANCELED, null);  
		finish();
	}

	private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener()
	{
		public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
		{
			mYear = year;
			mMonth = monthOfYear;
			mDay = dayOfMonth;

			mCalendar = new GregorianCalendar(mYear, mMonth, mDay, mHour, mMinute);
			mAlarm.setFromDate(mCalendar.getTimeInMillis());

			updateButtons();
		}
	};

	private TimePickerDialog.OnTimeSetListener mTimeSetListener = new TimePickerDialog.OnTimeSetListener()
	{
		public void onTimeSet(TimePicker view, int hourOfDay, int minute)
		{
			mHour = hourOfDay;
			mMinute = minute;

			mCalendar = new GregorianCalendar(mYear, mMonth, mDay, mHour, mMinute);
			mAlarm.setFromDate(mCalendar.getTimeInMillis());

			updateButtons();
		}
	};

	private TextWatcher mTitleChangedListener = new TextWatcher()
	{
		public void afterTextChanged(Editable s)
		{
			mAlarm.setTitle(mTitle.getText().toString());
			
		}

		public void beforeTextChanged(CharSequence s, int start, int count, int after)
		{
		}

		public void onTextChanged(CharSequence s, int start, int before, int count)
		{
		}
	}; 

	private AdapterView.OnItemSelectedListener mOccurenceSelectedListener = new AdapterView.OnItemSelectedListener()
	{
		@Override
		public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) 
		{
			mAlarm.setOccurence(position);
			updateButtons();
		}

		@Override
		public void onNothingSelected(AdapterView<?> parent)
		{
		}
	};

	private AdapterView.OnItemSelectedListener mIntervalSelectedListener = new AdapterView.OnItemSelectedListener()
	{
		@Override
		public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) 
		{
			mAlarm.setInterval(position);
			updateButtons();
		}

		@Override
		public void onNothingSelected(AdapterView<?> parent)
		{
		}
	};


	private CompoundButton.OnCheckedChangeListener mAlarmEnabledChangeListener = new CompoundButton.OnCheckedChangeListener()
	{
		@Override
		public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
		{
			mAlarm.setEnabled(isChecked);
		}
	};

	private void updateButtons()
	{
		if (Alarm.ONCE == mAlarm.getOccurence())
			mDateButton.setText(mDateTime.formatFromDate(mAlarm));
		else if (Alarm.WEEKLY == mAlarm.getOccurence())
			mDateButton.setText(mDateTime.formatDays(mAlarm));
		mTimeButton.setText(mDateTime.formatTime(mAlarm));
	}

	private Dialog DaysPickerDialog()
	{
		AlertDialog.Builder builder;
		final boolean[] days = mDateTime.getDays(mAlarm);
		final String[] names = mDateTime.getFullDayNames();

		builder = new AlertDialog.Builder(this);

		builder.setTitle("Week days");

		builder.setMultiChoiceItems(names, days, new DialogInterface.OnMultiChoiceClickListener()
		{
			public void onClick(DialogInterface dialog, int whichButton, boolean isChecked)
			{
			}
		});

		builder.setPositiveButton("OK", new DialogInterface.OnClickListener()
		{
			public void onClick(DialogInterface dialog, int whichButton)
			{
				mDateTime.setDays(mAlarm, days);
				updateButtons();
			}
		});

		builder.setNegativeButton("Cancel", null);

		return builder.create();
	}

	public class MenuItem extends ArrayAdapter {

		public MenuItem(Context context, int textViewResourceId, String[] objects) {

			super(context, textViewResourceId, objects);


		}

		@Override 
		public View getView(int position, View convertView, ViewGroup parent) {

			LayoutInflater inflater=getLayoutInflater();
			View row=inflater.inflate(R.layout.menu_item, parent, false);
			TextView tv=(TextView)row.findViewById(R.id.text);
			tv.setText(menu_text[position]);
			tv.setCompoundDrawablesWithIntrinsicBounds(0, menu_icon[position], 0, 0);
			return row;

		}

	}


	public class ImageAdapter extends BaseAdapter {
		Context mContext;
		public static final int ACTIVITY_CREATE = 10;

		public ImageAdapter(Context c) {
			mContext = c;
		}

		public int getCount() {
			// TODO Auto-generated method stub
			return 3; // THE NUMBER OF COUNTRIES SHOULD BE ADDED HERE IF
			// INCREASED!!!!!!!!!!
		}

		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			View v;
			if (convertView == null) {
				LayoutInflater li = getLayoutInflater();
				v = li.inflate(R.layout.gridinfo, null);
				TextView tv = (TextView) v.findViewById(R.id.icon_text);
				tv.setText(menu_text[position]);
				ImageView iv = (ImageView) v.findViewById(R.id.icon_image);
				iv.setImageResource(menu_icon[position]);


			} else {
				v = convertView;
			}
			return v;
		}
	}
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub

	}

    @Override
    public void onResume()
    {
        super.onResume();
        dbAdapter.open();
    }

    @Override
    public void onStart()
    {
        super.onStart();
        dbAdapter.open();
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
        dbAdapter.close();
    }

    @Override
    public void onStop() {
        super.onStop();
        dbAdapter.close();
    }
}

