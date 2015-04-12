package com.taradov.alarmme;

import android.content.Context;
import android.database.Cursor;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Patient {

	private String  patientName;
	private String  fatherName;
	private int     age;
	private int     gender;
	private int     ethinicity;
	private String  address;
	private int     maritalStatus;
	private String  profession;
	private int     infections;
	private String  childrenNo;
	private int     childrenSuffer;
	private int     spouseSuffer;
	private int     riskBehaviour;
	private int     travelHistory;
	private int     friendHistory;
	private int     clinicalFeatures;
	private String  diagnosisDate;

	public Patient (Context context){
		patientName     = "";
		fatherName      = "";
		age             = 0;
		gender          = R.id.Male_radio;
		ethinicity      = 0;
		address         = "";
		maritalStatus   = R.id.single_radio;
		profession      = "";
		infections      = 0;
		childrenNo      = "";
		childrenSuffer  = R.id.yes_child_suffering_radio;
		spouseSuffer    = R.id.yes_spouse_suffering_radio;
		riskBehaviour   = 0;
		travelHistory   = R.id.yes_history_travel_radio;
		friendHistory   = 0;
		clinicalFeatures= 0;
		diagnosisDate   = "";
	}

    public void fromCursor(Cursor _cursor)
    {
        patientName     = _cursor.getString(DBAdapter.PATIENT_NAME_COLUMN);
        fatherName      = _cursor.getString(DBAdapter.PATIENT_FATHERNAME_COLUMN);
        age             = _cursor.getInt(DBAdapter.PATIENT_AGE_COLUMN);
        gender          = _cursor.getInt(DBAdapter.PATIENT_GENDER_COLUMN);
        ethinicity      = _cursor.getInt(DBAdapter.PATIENT_ETHNICITY_COLUMN);
        address         = _cursor.getString(DBAdapter.PATIENT_ADDRESS_COLUMN);
        maritalStatus   = _cursor.getInt(DBAdapter.PATIENT_MARITALSTATUS_COLUMN);
        profession      = _cursor.getString(DBAdapter.PATIENT_PROFESSION_COLUMN);
        infections      = _cursor.getInt(DBAdapter.PATIENT_INFECTIONS_COLUMN);
        childrenNo      = _cursor.getString(DBAdapter.PATIENT_CHILDRENNO_COLUMN);
        childrenSuffer  = _cursor.getInt(DBAdapter.PATIENT_CHILDRENSUFFER_COLUMN);
        spouseSuffer    = _cursor.getInt(DBAdapter.PATIENT_SPOUSESUFFER_COLUMN);
        riskBehaviour   = _cursor.getInt(DBAdapter.PATIENT_RISK_COLUMN);
        travelHistory   = _cursor.getInt(DBAdapter.PATIENT_TRAVELHISTORY_COLUMN);
        friendHistory   = _cursor.getInt(DBAdapter.PATIENT_FRIENDHISTORY_COLUMN);
        clinicalFeatures= _cursor.getInt(DBAdapter.PATIENT_CLINICALFEATUR_COLUMN);
        diagnosisDate   = _cursor.getString(DBAdapter.PATIENT_DIAGNOSISDATE_COLUMN);
    }

	//set methods

	public void setPatientName(String pname){
		patientName=pname;
	}

	public void setFatherName(String fname){
		fatherName=fname;
	}

	public void setAge(int position){
		age=position;
	}

	public void setGender(int Gender) {
		gender = Gender;
	}

	public void setEthinicity(int Ethinicity){
		ethinicity=Ethinicity;
	}
	public void setProfession(String prof){
		profession=prof;
	}
	public void setMaritalStatus(int marStatus){
		maritalStatus=marStatus;
	}
	public void setAddress(String addr){
		address=addr;
	}
	public void setInfections(int inf){
		infections=inf;
	}
	public void setChildrenno(String cno){
		childrenNo=cno;
	}
	public void setChildrenSuffer(int csuff){
		childrenSuffer=csuff;
	}
	public void setSpouseSuffer(int spsuff){
		spouseSuffer=spsuff;
	}
	public void setRiskBehaviour(int risk){
		riskBehaviour=risk;
	}
	public void settravelHistory(int travel){
		travelHistory=travel;
	}
	public void setfriendHistory(int friend){
		friendHistory=friend;
	}
	public void setclinicalFeatures(int clin){
		clinicalFeatures=clin;
	}
	public void setDiagnosisDate(String date){
		diagnosisDate=date;
	}

	// get methods

	public String getPatientName(){
		return patientName;
	}

	public String getFatherName(){
		return fatherName;
	}

	public int getAge(){
		return age;
	}

	public int getEthinicity(){
		return ethinicity;
	}
	public String getProfession(){
		return profession;
	}
	public int getMaritalStatus(){
		return maritalStatus;
	}
	public String getAddress(){
		return 	address;
	}
	public int getInfection(){
		return 	infections;
	}
	public String getChildrenno(){
		return childrenNo	;
	}
	public int getChildrenSuffer(){
		return childrenSuffer	;
	}
	public int getSpouseSuffer(){
		return spouseSuffer	;
	}

	public int getRiskBehaviour(){
		return riskBehaviour	;
	}
	public int getTravelHistory(){
		return travelHistory	;
	}
	public int getFriendHistory(){
		return friendHistory	;
	}
	public int getClinicalFeatures(){
		return clinicalFeatures	;
	}
	public String getDiagnosisDate(){
		return diagnosisDate	;
	}

	public int getGender() {
		return gender;
	}

}
