package com.taradov.alarmme;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Inshal on 21-Mar-15.
 */
public class MedicineAdapter extends ArrayAdapter<Medicine> {
    int resource;
    Context context;

    public MedicineAdapter(Context _context, int _resource, List<Medicine> _items) {
        super(_context, _resource, _items);
        resource = _resource;
        context = _context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LinearLayout medicineView;

        Medicine medicine = getItem(position);
        String id = String.valueOf(medicine.getId());
        String name = medicine.getName();
        String color = String.valueOf(medicine.getColor());
        String audio = String.valueOf(medicine.getAudio());

        if (convertView == null) {
            medicineView = new LinearLayout(getContext());
            String inflater = Context.LAYOUT_INFLATER_SERVICE;
            LayoutInflater vi = (LayoutInflater) getContext().getSystemService(inflater);
            vi.inflate(resource, medicineView, true);
        } else {
            medicineView = (LinearLayout) convertView;
        }

        TextView idView = (TextView) medicineView.findViewById(R.id.db_medicine_id);
        TextView nameView = (TextView) medicineView.findViewById(R.id.db_medicine_name);
        TextView colorView = (TextView) medicineView.findViewById(R.id.db_medicine_color);
        TextView audioView = (TextView) medicineView.findViewById(R.id.db_medicine_audio);

        idView.setText(id);
        nameView.setText(name);
        colorView.setText(color);
        audioView.setText(audio);

        return medicineView;
    }
}
