package com.taradov.alarmme;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Calendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

public class EditPatient extends Activity {

	private Patient mPatient;
	private DateTime mDateTime;

	private EditText PatientName_text; 
	private EditText FatherName_text; 
	
	private EditText Addr_text;
	private EditText Prof_text;
	private EditText ChildrenNo_text;

	private RadioGroup gender_radio;
	private RadioGroup marital_radio;
	private RadioGroup child_suffer_radio;
	private RadioGroup spouse_suffer_radio;
	private RadioGroup history_travel_radio;

	private GregorianCalendar mCalendar;


	private Spinner Ethinicity_spinner;
	private Spinner Age_spinner;


	private TextView Diagnosis_date_text; 
	private TextView Infections;

	private Button btn_done;
	private Button btn_cancel;

	private int mYear;
	private int mMonth;
	private int mDay;
	private int mHour;
	private int mMinute;

	ArrayList selectedInfectionItems;
	ArrayList selectedBehaviourItems;
	ArrayList selectedHistoryItems;


	static final int DATE_PICKER_ID = 1111;
	final CharSequence[] infection_items = {" HIV "," HCV "," HBV "," TB"};

	final CharSequence[] behaviour_items={"Drug Use","Promiscuity","Homosexuality","contact with sex workers"};

	final CharSequence[] history_items = {" Travel","Acquaintance with HIV "," Relationship with patient"};

    private DBAdapter dbAdapter;

	public void onCreate(Bundle bundle){
		super.onCreate(bundle);

		setContentView(R.layout.editpatient);

		mPatient= new Patient(this);
		mDateTime=new DateTime(this);

		PatientName_text =(EditText) findViewById(R.id.edit_patientname);
		FatherName_text=(EditText) findViewById(R.id.edit_patientFathername);
		Addr_text=(EditText) findViewById(R.id.edit_address);
		Prof_text=(EditText) findViewById(R.id.edit_profession);
		ChildrenNo_text=(EditText) findViewById(R.id.edit_no_of_children);

		gender_radio=(RadioGroup) findViewById(R.id.gender_radioGroup);
		gender_radio.setOnCheckedChangeListener(mGenderSelectedListener);
		marital_radio=(RadioGroup) findViewById(R.id.marital_radioGroup);
		marital_radio.setOnCheckedChangeListener(mMaritalSelectedListener);
		child_suffer_radio=(RadioGroup) findViewById(R.id.child_suffering_radioGroup);
		child_suffer_radio.setOnCheckedChangeListener(mChildSufferSelectedListener);
		spouse_suffer_radio=(RadioGroup) findViewById(R.id.spouse_suffering_radioGroup);
		spouse_suffer_radio.setOnCheckedChangeListener(mSpouseSufferSelectedListener);
		history_travel_radio=(RadioGroup) findViewById(R.id.history_travel_radioGroup);
		history_travel_radio.setOnCheckedChangeListener(mHistoryTravelSelectedListener);


		Ethinicity_spinner=(Spinner)findViewById(R.id.spinner_ethinicity);
		Ethinicity_spinner.setOnItemSelectedListener(mEthinicitySelectedListener);
		Age_spinner=(Spinner)findViewById(R.id.spinner_age);
		Age_spinner.setOnItemSelectedListener(mAgeSelectedListener);



		Diagnosis_date_text = (TextView) findViewById(R.id.diagnosis_date_view);
		Infections = (TextView) findViewById(R.id.infections_view);
		selectedInfectionItems=new ArrayList();
		selectedBehaviourItems=new ArrayList();
		selectedHistoryItems=new ArrayList();
		// Get current date by calender

		final Calendar c = Calendar.getInstance();
		mYear  = c.get(Calendar.YEAR);
		mMonth = c.get(Calendar.MONTH);
		mDay   = c.get(Calendar.DAY_OF_MONTH);

		update();

        dbAdapter = new DBAdapter(this);
        dbAdapter.open();

	}
	// Listeners for radio groups
	private RadioGroup.OnCheckedChangeListener mGenderSelectedListener = new RadioGroup.OnCheckedChangeListener() {

		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {

			RadioButton Male_btn= (RadioButton) findViewById(R.id.Male_radio);
			if(Male_btn.isChecked())
				mPatient.setGender(checkedId);
			else
				mPatient.setGender(checkedId);


		}
	};

	private RadioGroup.OnCheckedChangeListener mChildSufferSelectedListener = new RadioGroup.OnCheckedChangeListener() {

		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {

			RadioButton yes_btn= (RadioButton) findViewById(R.id.yes_child_suffering_radio);
			if(yes_btn.isChecked())
				mPatient.setChildrenSuffer(checkedId);
			else
				mPatient.setChildrenSuffer(checkedId);


		}
	};

	private RadioGroup.OnCheckedChangeListener mMaritalSelectedListener = new RadioGroup.OnCheckedChangeListener() {

		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {

			RadioButton Single_btn= (RadioButton) findViewById(R.id.single_radio);
			if(Single_btn.isChecked())
				mPatient.setMaritalStatus(checkedId);
			else
				mPatient.setMaritalStatus(checkedId);


		}
	};

	private RadioGroup.OnCheckedChangeListener mSpouseSufferSelectedListener = new RadioGroup.OnCheckedChangeListener() {

		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {

			RadioButton yes_btn= (RadioButton) findViewById(R.id.yes_spouse_suffering_radio);
			if(yes_btn.isChecked())
				mPatient.setSpouseSuffer(checkedId);
			else
				mPatient.setSpouseSuffer(checkedId);


		}
	};




	private RadioGroup.OnCheckedChangeListener mHistoryTravelSelectedListener = new RadioGroup.OnCheckedChangeListener() {

		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {

			RadioButton yes_btn= (RadioButton) findViewById(R.id.yes_history_travel_radio);
			if(yes_btn.isChecked())
				mPatient.settravelHistory(checkedId);
			else
				mPatient.settravelHistory(checkedId);

		}
	};
	private void update() {

		Diagnosis_date_text.setText(mDateTime.formatDiagnosisDate(mPatient));

	}
	// listeners for spinners

	private AdapterView.OnItemSelectedListener mEthinicitySelectedListener = new AdapterView.OnItemSelectedListener()
	{
		@Override
		public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) 
		{
			mPatient.setEthinicity(position);
			update();
		}

		@Override
		public void onNothingSelected(AdapterView<?> parent)
		{
		}
	};

	private AdapterView.OnItemSelectedListener mAgeSelectedListener = new AdapterView.OnItemSelectedListener()
	{
		@Override
		public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) 
		{
			mPatient.setAge(position);
			update();
		}

		@Override
		public void onNothingSelected(AdapterView<?> parent)
		{
		}
	};

	//ClickListener for TextvIews

	public void onInfectionsClick(View v){
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Select the Infection Type");
		builder.setMultiChoiceItems(infection_items, null,
				new DialogInterface.OnMultiChoiceClickListener() {
			// indexSelected contains the index of item (of which checkbox checked)
			@Override
			public void onClick(DialogInterface dialog, int indexSelected,
					boolean isChecked) {
				if (isChecked) {
					// If the user checked the item, add it to the selected items
					// write your code when user checked the checkbox 
					selectedInfectionItems.add(indexSelected);
				} else if (selectedInfectionItems.contains(indexSelected)) {
					// Else, if the item is already in the array, remove it 
					// write your code when user Uchecked the checkbox 
					selectedInfectionItems.remove(Integer.valueOf(indexSelected));
				}
			}
		})
		// Set the action buttons
		.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int id) {
				mPatient.setInfections(id);

			}
		})
		.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int id) {
				//  Your code when user clicked on Cancel

			}
		});

		Dialog dialog = builder.create();//AlertDialog dialog; create like this outside onClick
		dialog.show();
	}

	public void onBehaviourClick(View v){
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Select the High Risk Behaviour Type");
		builder.setMultiChoiceItems(behaviour_items, null,
				new DialogInterface.OnMultiChoiceClickListener() {
			// indexSelected contains the index of item (of which checkbox checked)
			@Override
			public void onClick(DialogInterface dialog, int indexSelected,
					boolean isChecked) {
				if (isChecked) {
					// If the user checked the item, add it to the selected items
					// write your code when user checked the checkbox 
					selectedBehaviourItems.add(indexSelected);
				} else if (selectedBehaviourItems.contains(indexSelected)) {
					// Else, if the item is already in the array, remove it 
					// write your code when user Uchecked the checkbox 
					selectedBehaviourItems.remove(Integer.valueOf(indexSelected));
				}
			}
		})
		// Set the action buttons
		.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int id) {
				mPatient.setRiskBehaviour(id);

			}
		})
		.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int id) {
				//  Your code when user clicked on Cancel

			}
		});

		Dialog dialog = builder.create();//AlertDialog dialog; create like this outside onClick
		dialog.show();

	}

	public void onHistoryFriendClick(View v){
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Select History of Friend /Partner");
		builder.setMultiChoiceItems(history_items, null,
				new DialogInterface.OnMultiChoiceClickListener() {
			// indexSelected contains the index of item (of which checkbox checked)
			@Override
			public void onClick(DialogInterface dialog, int indexSelected,
					boolean isChecked) {
				if (isChecked) {
					// If the user checked the item, add it to the selected items
					// write your code when user checked the checkbox 
					selectedHistoryItems.add(indexSelected);
				} else if (selectedHistoryItems.contains(indexSelected)) {
					// Else, if the item is already in the array, remove it 
					// write your code when user Uchecked the checkbox 
					selectedHistoryItems.remove(Integer.valueOf(indexSelected));
				}
			}
		})
		// Set the action buttons
		.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int id) {
				mPatient.setfriendHistory(id);

			}
		})
		.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int id) {
				//  Your code when user clicked on Cancel

			}
		});

		Dialog dialog = builder.create();//AlertDialog dialog; create like this outside onClick
		dialog.show();


	}


	public void onClick(View v) {
		showDialog(DATE_PICKER_ID);


	}


	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case DATE_PICKER_ID:

			// open datepicker dialog. 
			// set date picker for current date 
			// add pickerListener listner to date picker
			return new DatePickerDialog(this, pickerListener, mYear, mMonth,mDay);
		}
		return null;
	}

	private DatePickerDialog.OnDateSetListener pickerListener = new DatePickerDialog.OnDateSetListener() {

		// when dialog box is closed, below method will be called.
		@Override
		public void onDateSet(DatePicker view, int selectedYear,
				int selectedMonth, int selectedDay) {

			mYear  = selectedYear;
			mMonth = selectedMonth;
			mDay   = selectedDay;

			//			mCalendar = new GregorianCalendar(mYear, mMonth, mDay, mHour, mMinute);
			//			mPatient.setDiagnosisDate(mCalendar.getTimeInMillis());

			// Show selected date 
			Diagnosis_date_text.setText(new StringBuilder().append(mMonth + 1)
					.append("-").append(mDay).append("-").append(mYear)
					.append(" "));

		}
	};


	public void onCancelClick(View v){
		setResult(RESULT_CANCELED, null);  

		finish();

	}
	public void onDoneClick(View v){
		set();
		Intent intent = new Intent();
		setResult(RESULT_OK, intent);

        dbAdapter.removePatient();
        dbAdapter.addPatient(mPatient);

		finish();

	}

	public void set(){
		mPatient.setPatientName(PatientName_text.getText().toString());
		mPatient.setFatherName(FatherName_text.getText().toString());
		
		mPatient.setAddress(Addr_text.getText().toString());
		mPatient.setProfession(Prof_text.getText().toString());
		mPatient.setChildrenno(ChildrenNo_text.getText().toString());
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


