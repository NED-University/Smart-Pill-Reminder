package com.taradov.alarmme;

/**
 * Created by Administrator on 4/7/2015.
 */


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
import android.content.res.Resources;
import android.database.Cursor;
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
    private EditText Age_text;
    private EditText Addr_text;
    private EditText Prof_text;
    private EditText ChildrenNo_text;
    private EditText cd4_text;
    private EditText viralLoad_text;

    private RadioGroup gender_radio;
    private RadioGroup marital_radio;
    private RadioGroup child_suffer_radio;
    private RadioGroup spouse_suffer_radio;
    private RadioGroup history_travel_radio;

    private GregorianCalendar mCalendar;


    private Spinner Ethnicity_spinner;
    //private Spinner Age_spinner;


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
   // boolean isSelectedArray={true,false,true,false};
    ArrayList selectedBehaviourItems;
    ArrayList selectedFriendHistoryItems;

    private String[] infectionsArray;
    private String[] behaviourArray;
    private String[] friendHistoryArray;
    Resources res;




    static final int DATE_PICKER_ID = 1111;
    final CharSequence[] infection_items = {"HIV", "HCV", "HBV", "TB"};

    final CharSequence[] behaviour_items = {"DrugUse", "Promiscuity", "Homosexuality", "contact with sex workers"};

    final CharSequence[] history_items = {"Travel", "Acquaintance with HIV ", "Relationship with patient"};

    private DBAdapter dbAdapter;
    private  boolean editPatient;
    private  String patientName;


    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);

        setContentView(R.layout.edit_patient);

        mPatient = new Patient(this);
        mDateTime = new DateTime(this);

        PatientName_text = (EditText) findViewById(R.id.edit_patientname);
        FatherName_text = (EditText) findViewById(R.id.edit_patientFathername);
        Age_text = (EditText) findViewById(R.id.edit_age);
        Addr_text = (EditText) findViewById(R.id.edit_address);
        Prof_text = (EditText) findViewById(R.id.edit_profession);
        ChildrenNo_text = (EditText) findViewById(R.id.edit_no_of_children);
        cd4_text = (EditText) findViewById(R.id.edit_CD4);
        viralLoad_text = (EditText) findViewById(R.id.edit_viralLoad);

        gender_radio = (RadioGroup) findViewById(R.id.gender_radioGroup);
        gender_radio.setOnCheckedChangeListener(mGenderSelectedListener);
        marital_radio = (RadioGroup) findViewById(R.id.marital_radioGroup);
        marital_radio.setOnCheckedChangeListener(mMaritalSelectedListener);
        child_suffer_radio = (RadioGroup) findViewById(R.id.child_suffering_radioGroup);
        child_suffer_radio.setOnCheckedChangeListener(mChildSufferSelectedListener);
        spouse_suffer_radio = (RadioGroup) findViewById(R.id.spouse_suffering_radioGroup);
        spouse_suffer_radio.setOnCheckedChangeListener(mSpouseSufferSelectedListener);
        history_travel_radio = (RadioGroup) findViewById(R.id.history_travel_radioGroup);
        history_travel_radio.setOnCheckedChangeListener(mHistoryTravelSelectedListener);


        Ethnicity_spinner = (Spinner) findViewById(R.id.spinner_ethinicity);
        Ethnicity_spinner.setOnItemSelectedListener(mEthnicitySelectedListener);
//        Age_spinner=(Spinner)findViewById(R.id.spinner_age);
//        Age_spinner.setOnItemSelectedListener(mAgeSelectedListener);


        Diagnosis_date_text = (TextView) findViewById(R.id.diagnosis_date_view);
        Infections = (TextView) findViewById(R.id.infections_view);


        selectedBehaviourItems = new ArrayList();
        selectedFriendHistoryItems = new ArrayList();
        // Get current date by calender

        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);


        update();

        res=getResources();
        infectionsArray=res.getStringArray(R.array.infection_items);
        behaviourArray=res.getStringArray(R.array.behaviour_items);
        friendHistoryArray=res.getStringArray(R.array.history_items);

        dbAdapter = new DBAdapter(this);
        dbAdapter.open();
        patientName= mPatient.getPatientName();
        editPatient = getIntent().getBooleanExtra("EDIT", false);


    }

    // Listeners for radio groups
    private RadioGroup.OnCheckedChangeListener mGenderSelectedListener = new RadioGroup.OnCheckedChangeListener() {

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {

            RadioButton Male_btn = (RadioButton) findViewById(R.id.Male_radio);
            if (Male_btn.isChecked())
                mPatient.setGender("Male");
            else
                mPatient.setGender("Female");


        }
    };

    private RadioGroup.OnCheckedChangeListener mChildSufferSelectedListener = new RadioGroup.OnCheckedChangeListener() {

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {

            RadioButton yes_btn = (RadioButton) findViewById(R.id.yes_child_suffering_radio);
            if (yes_btn.isChecked())
                mPatient.setChildrenSuffer("Yes");
            else
                mPatient.setChildrenSuffer("No");


        }
    };

    private RadioGroup.OnCheckedChangeListener mMaritalSelectedListener = new RadioGroup.OnCheckedChangeListener() {

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {

            RadioButton Single_btn = (RadioButton) findViewById(R.id.single_radio);
            if (Single_btn.isChecked())
                mPatient.setMaritalStatus("Single");
            else
                mPatient.setMaritalStatus("Married");


        }
    };

    private RadioGroup.OnCheckedChangeListener mSpouseSufferSelectedListener = new RadioGroup.OnCheckedChangeListener() {

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {

            RadioButton yes_btn = (RadioButton) findViewById(R.id.yes_spouse_suffering_radio);
            if (yes_btn.isChecked())
                mPatient.setSpouseSuffer("Yes");
            else
                mPatient.setSpouseSuffer("No");


        }
    };


    private RadioGroup.OnCheckedChangeListener mHistoryTravelSelectedListener = new RadioGroup.OnCheckedChangeListener() {

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {

            RadioButton yes_btn = (RadioButton) findViewById(R.id.yes_history_travel_radio);
           if (yes_btn.isChecked())
                mPatient.settravelHistory("Yes");
            else
               mPatient.settravelHistory("No");

        }
    };


    // listeners for spinners

    private AdapterView.OnItemSelectedListener mEthnicitySelectedListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {


           if (position==0)
               mPatient.setEthnicity("Sindhi");
            else if (position==1)
               mPatient.setEthnicity("Punjabi");
            else if (position==2)
               mPatient.setEthnicity("Balochi");
            else
                mPatient.setEthnicity("Puston");


        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
        }
    };

//    private AdapterView.OnItemSelectedListener mAgeSelectedListener = new AdapterView.OnItemSelectedListener()
//    {
//        @Override
//        public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id)
//        {
//            mPatient.setAge(position);
//            update();
//        }
//
//        @Override
//        public void onNothingSelected(AdapterView<?> parent)
//        {
//        }
//    };

    //ClickListener for TextvIews

    public void onInfectionsClick(View v) {
        selectedInfectionItems = new ArrayList();
        boolean[] isSelectedArray = {true, false, true, false};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Select the Infection Type");
        builder.setMultiChoiceItems(infection_items, null,
               new DialogInterface.OnMultiChoiceClickListener() {
                    // indexSelected contains the index of item (of which checkbox checked)
                    @Override
                    public void onClick(DialogInterface dialog, int infectionSelected,
                                        boolean isChecked) {
                        if (isChecked) {
                            // If the user checked the item, add it to the selected items
                            // write your code when user checked the checkbox
                            selectedInfectionItems.add(infectionSelected);
                        } else if (selectedInfectionItems.contains(infectionSelected)) {
                            // Else, if the item is already in the array, remove it
                            // write your code when user Uchecked the checkbox
                            selectedInfectionItems.remove(Integer.valueOf(infectionSelected));
                        }
                    }
                })
                // Set the action buttons
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // mPatient.setInfections(selectedInfectionItems);
                        setInfections(selectedInfectionItems);

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

    public void onBehaviourClick(View v) {
        selectedBehaviourItems = new ArrayList();
        boolean[] isSelectedArray = {true, false, true, false};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select the High Risk Behaviour Type");
        builder.setMultiChoiceItems(behaviour_items, null,
                new DialogInterface.OnMultiChoiceClickListener() {
                    // indexSelected contains the index of item (of which checkbox checked)
                    @Override
                    public void onClick(DialogInterface dialog, int behaviourSelected,
                                        boolean isChecked) {
                        if (isChecked) {
                            // If the user checked the item, add it to the selected items
                            // write your code when user checked the checkbox
                            selectedBehaviourItems.add(behaviourSelected);
                        } else if (selectedBehaviourItems.contains(behaviourSelected)) {
                            // Else, if the item is already in the array, remove it
                            // write your code when user Uchecked the checkbox
                            selectedBehaviourItems.remove(Integer.valueOf(behaviourSelected));
                        }
                    }
                })
                // Set the action buttons
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                       // mPatient.setRiskBehaviour(id);
                        setBehaviour(selectedBehaviourItems);

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

    public void onHistoryFriendClick(View v) {
        selectedFriendHistoryItems = new ArrayList();

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select Friend History");
        builder.setMultiChoiceItems(history_items, null,
                new DialogInterface.OnMultiChoiceClickListener() {
                    // indexSelected contains the index of item (of which checkbox checked)
                    @Override
                    public void onClick(DialogInterface dialog, int friendHistorySelected,
                                        boolean isChecked) {
                        if (isChecked) {
                            // If the user checked the item, add it to the selected items
                            // write your code when user checked the checkbox
                            selectedFriendHistoryItems.add(friendHistorySelected);
                        } else if (selectedFriendHistoryItems.contains(friendHistorySelected)) {
                            // Else, if the item is already in the array, remove it
                            // write your code when user Uchecked the checkbox
                            selectedFriendHistoryItems.remove(Integer.valueOf(friendHistorySelected));
                        }
                    }
                })
                // Set the action buttons
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // mPatient.setRiskBehaviour(id);
                        setFriendHistory(selectedFriendHistoryItems);

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
                return new DatePickerDialog(this, pickerListener, mYear, mMonth, mDay);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener pickerListener = new DatePickerDialog.OnDateSetListener() {

        // when dialog box is closed, below method will be called.
        @Override
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {

            mYear = selectedYear;
            mMonth = selectedMonth;
            mDay = selectedDay;

            //			mCalendar = new GregorianCalendar(mYear, mMonth, mDay, mHour, mMinute);
            //			mPatient.setDiagnosisDate(mCalendar.getTimeInMillis());



            // Show selected date
            Diagnosis_date_text.setText(new StringBuilder().append(mMonth + 1)
                    .append("-").append(mDay).append("-").append(mYear)
                    .append(" "));
           mPatient.setDiagnosisDate((new StringBuilder().append(mMonth + 1)
                   .append("-").append(mDay).append("-").append(mYear)
                   .append(" ")).toString() );


        }
    };


    public void onCancelClick(View v) {
        setResult(RESULT_CANCELED, null);

        finish();

    }

    private void update() {

       // Diagnosis_date_text.setText(mDateTime.formatDiagnosisDate(mPatient));


    }
    public void onDoneClick(View v) {
        set();
        Intent intent = new Intent();
//        if (editPatient) {
//            dbAdapter.updatePatient(patientName, mPatient);
//        } else
//            dbAdapter.addPatient(mPatient);
        setResult(RESULT_OK, intent);

        dbAdapter.removePatient();
        dbAdapter.addPatient(mPatient);


//        dbAdapter.removePatient();
//        dbAdapter.addPatient(mPatient);

        finish();

    }

    public void set() {
        mPatient.setPatientName(PatientName_text.getText().toString());
        mPatient.setFatherName(FatherName_text.getText().toString());
        mPatient.setAge(Age_text.getText().toString());

        mPatient.setcd4(cd4_text.getText().toString());
        mPatient.setViralLoad(viralLoad_text.getText().toString());

        mPatient.setAddress(Addr_text.getText().toString());
        mPatient.setProfession(Prof_text.getText().toString());
        mPatient.setChildrenno(ChildrenNo_text.getText().toString());
    }

    public void setInfections(ArrayList<Integer> arrayList) {
        StringBuilder infections_stringBuilder =new StringBuilder();
        if(arrayList.size()!=0){
            for(int i=0;i<arrayList.size();i++){
                String infections=infectionsArray[arrayList.get(i)];
                infections_stringBuilder = infections_stringBuilder.append(" "+infections);



            }
            mPatient.setInfections(infections_stringBuilder.toString());
        }}

    public void setBehaviour(ArrayList<Integer> arrayList) {
        StringBuilder behaviour_stringBuilder =new StringBuilder();
        if(arrayList.size()!=0){
            for(int i=0;i<arrayList.size();i++){
                String behaviour=behaviourArray[arrayList.get(i)];
                behaviour_stringBuilder = behaviour_stringBuilder.append(" "+behaviour);



            }
            mPatient.setRiskBehaviour(behaviour_stringBuilder.toString());
        }}

    public void setFriendHistory(ArrayList<Integer> arrayList) {
        StringBuilder friendHistory_stringBuilder =new StringBuilder();
        if(arrayList.size()!=0){
            for(int i=0;i<arrayList.size();i++){
                String friendHistory=friendHistoryArray[arrayList.get(i)];
                friendHistory_stringBuilder = friendHistory_stringBuilder.append(" "+friendHistory);



            }
            mPatient.setfriendHistory(friendHistory_stringBuilder.toString());
        }}
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