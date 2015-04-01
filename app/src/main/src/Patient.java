package com.taradov.alarmme;

import android.content.Context;

public class Patient {

	private String patientName;
	private String fatherName;
	private int age;
	private int gender;
	private int ethinicity;
	private String address;
	private int maritalStatus;
	private String profession;
	private int infections;
	private String childrenNo;
	private int childrenSuffer;
	private int spouseSuffer;
	private int riskBehaviour;
	private int travelHistory;
	private int friendHistory;
	private int clinicalFeatures;
	private long diagnosisDate;
	
	
	public Patient (Context context){
		patientName="";
		fatherName="";
		age=0;
		gender=0;
		ethinicity=0;
		address="";
		maritalStatus=0;
		profession="";
		infections=0;
		childrenNo="";
		childrenSuffer=0;
		spouseSuffer=0;
		riskBehaviour=0;
		travelHistory=0;
		friendHistory=0;
		clinicalFeatures=0;
		diagnosisDate=System.currentTimeMillis();
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
	public void setDiagnosisDate(long date){
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
	public String getChlidrenno(){
		return childrenNo	;
	}
	public int getChlidrenSuffer(){
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
	public long getDiagnosisDate(){
		return diagnosisDate	;
	}

	public int getGender() {
		return gender;
	}







}
