package com.taradov.alarmme;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.List;

/**
* Created by Administrator on 4/9/2015.
*/
public class PatientAdapter extends ArrayAdapter<Patient> {
    int resource;
    Context context;

    public PatientAdapter(Context _context, int _resource, List<Patient> _items) {
        super(_context, _resource, _items);
        resource = _resource;
        context = _context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        GridLayout patientView;

        DateTime dt = new DateTime(context);

        Patient patient = getItem(position);

        if (convertView == null) {
            patientView = new GridLayout(getContext());
            String inflater = Context.LAYOUT_INFLATER_SERVICE;
            LayoutInflater vi = (LayoutInflater) getContext().getSystemService(inflater);
            vi.inflate(resource, patientView, true);
        } else {
            patientView = (GridLayout) convertView;
        }

        TextView nameView           = (TextView) patientView.findViewById(R.id.db_patient_name);
        TextView fatherNameView     = (TextView) patientView.findViewById(R.id.db_patient_fathername);
        TextView ageView            = (TextView) patientView.findViewById(R.id.db_patient_age);
        TextView genderView         = (TextView) patientView.findViewById(R.id.db_patient_gender);
        TextView ethnicityView      = (TextView) patientView.findViewById(R.id.db_patient_ethnicity);
        TextView addressView        = (TextView) patientView.findViewById(R.id.db_patient_address);
        TextView maritalStatusView  = (TextView) patientView.findViewById(R.id.db_patient_maritalstatus);
        TextView professionView     = (TextView) patientView.findViewById(R.id.db_patient_profession);
        TextView infectionsView     = (TextView) patientView.findViewById(R.id.db_patient_infections);
        TextView childrenNoView     = (TextView) patientView.findViewById(R.id.db_patient_childrenno);
        TextView childrenSufferView = (TextView) patientView.findViewById(R.id.db_patient_childrensuffer);
        TextView spouseSufferView   = (TextView) patientView.findViewById(R.id.db_patient_spousesuffer);
        TextView riskBehaviourView  = (TextView) patientView.findViewById(R.id.db_patient_riskbehaviour);
        TextView travelHistoryView  = (TextView) patientView.findViewById(R.id.db_patient_travelhistory);
        TextView friendHistoryView  = (TextView) patientView.findViewById(R.id.db_patient_friendhistory);
        TextView cd4View            = (TextView) patientView.findViewById(R.id.db_patient_cd4);
        TextView viralLoadView      = (TextView) patientView.findViewById(R.id.db_patient_viralload);
        TextView diagnosisDateView  = (TextView) patientView.findViewById(R.id.db_patient_diagnosisdate);

        nameView.setText("Patient's Name: " + patient.getPatientName());
        fatherNameView.setText("Father's Name: " + patient.getFatherName());
        ageView.setText("Age: " + patient.getAge());
        genderView.setText("Gender: " + patient.getGender());
        ethnicityView.setText("Ethnicity: " + patient.getEthnicity());
        addressView.setText("Address: " + patient.getAddress());
        maritalStatusView.setText("Marital Status: " + patient.getMaritalStatus());
        professionView.setText("Profession: " + patient.getProfession());
        infectionsView.setText("Infections: " + patient.getInfection());
        childrenNoView.setText("No. of Children: " + patient.getChildrenno());
        childrenSufferView.setText("Children Suffer: " + patient.getChildrenSuffer());
        spouseSufferView.setText("Spouse Suffer: " + patient.getSpouseSuffer());
        riskBehaviourView.setText("Risk Behaviour: " + patient.getRiskBehaviour());
        travelHistoryView.setText("Travel History: " + patient.getTravelHistory());
        friendHistoryView.setText("Friend History: " + patient.getFriendHistory());
        cd4View.setText("CD4 Levels: " + patient.getcd4());
        viralLoadView.setText("Viral Load: " + patient.getViralLoad());
        diagnosisDateView.setText("Diagnosis Date: " + patient.getDiagnosisDate());

        return patientView;
    }



}
