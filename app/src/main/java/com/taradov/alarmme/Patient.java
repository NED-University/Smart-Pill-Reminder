package com.taradov.alarmme;

/**
 * Created by Administrator on 4/7/2015.
 */


import android.content.Context;
import android.content.res.Resources;
import android.widget.Toast;


import java.util.ArrayList;



public class Patient {

    private int patId;
    private String patientName;
    private String fatherName;
    private String age;
    private String gender;
    private String  ethnicity;
    private String address;
    private String maritalStatus;
    private String profession;
    private String infections;
    private String childrenNo;
    private String  childrenSuffer;
    private String spouseSuffer;
    private String riskBehaviour;
    private String travelHistory;
    private String friendHistory;
    private String cd4;
    private String viralLoad;
    private String diagnosisDate;







    public Patient(Context context) {

        patientName = "";
        fatherName = "";
        age = "";
        gender = "Male";
        ethnicity ="Sindhi";
        address = "";
        maritalStatus = "Single";
        profession = "";
        infections = "";
        childrenNo = "";
        childrenSuffer ="No";
        spouseSuffer ="Yes";
        riskBehaviour = "";
        travelHistory = "No";
        friendHistory = "";
        cd4 = "";
        viralLoad = "";
        diagnosisDate = "";

    }


    //set methods

    public void setPatientName(String pname) {
        patientName = pname;
    }

    public void setFatherName(String fname) {
        fatherName = fname;
    }

    public void setAge(String a) {
        age = a;
    }

    public void setGender(String Gender) {
        gender = Gender;
    }

    public void setEthnicity(String Ethnicity) {
        ethnicity = Ethnicity;
    }

    public void setProfession(String prof) {
        profession = prof;
    }

    public void setMaritalStatus(String marStatus) {
        maritalStatus = marStatus;
    }

    public void setAddress(String addr) {
        address = addr;
    }

    public void setInfections(String inf) {
        infections=inf;
    }





    public void setChildrenno(String cno) {
        childrenNo = cno;
    }

    public void setChildrenSuffer(String csuff) {
        childrenSuffer = csuff;
    }

    public void setSpouseSuffer(String spsuff) {
        spouseSuffer = spsuff;
    }

    public void setRiskBehaviour(String risk) {
        riskBehaviour = risk;
    }

    public void settravelHistory(String travel) {
        travelHistory = travel;
    }

    public void setfriendHistory(String friend) {
        friendHistory = friend;
    }

    public void setcd4(String cd) {
        cd4 = cd;
    }

    public void setViralLoad(String vl) {
        viralLoad = vl;
    }

    public void setDiagnosisDate(String date) {
        diagnosisDate = date;
    }


    // get methods

    public String getPatientName() {
        return patientName;
    }

    public String getFatherName() {
        return fatherName;
    }

    public String getAge() {
        return age;
    }

    public String getEthnicity() {
        return ethnicity;
    }

    public String getProfession() {
        return profession;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public String getAddress() {
        return address;
    }

    public String getInfection() {
        return infections;
    }

    public String getChlidrenno() {
        return childrenNo;
    }

    public String getChlidrenSuffer() {
        return childrenSuffer;
    }

    public String  getSpouseSuffer() {
        return spouseSuffer;
    }

    public String getRiskBehaviour() {
        return riskBehaviour;
    }

    public String getTravelHistory() {
        return travelHistory;
    }

    public String getFriendHistory() {
        return friendHistory;
    }

    public String getcd4() {
        return cd4;
    }

    public String getViralLoad() {
        return viralLoad;
    }

    public String getDiagnosisDate() {
        return diagnosisDate;
    }

    public String getGender() {
        return gender;
    }


}
