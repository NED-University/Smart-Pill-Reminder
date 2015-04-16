package com.taradov.alarmme;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Inshal on 21-Mar-15.
 */

public class AlarmAdapter extends ArrayAdapter<Alarm> {
    int resource;
    Context context;

    public AlarmAdapter(Context _context, int _resource, List<Alarm> _items) {
        super(_context, _resource, _items);
        resource = _resource;
        context = _context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LinearLayout alarmView;
        DateTime dt = new DateTime(context);

        Alarm alarm = getItem(position);
        String title = alarm.getTitle();
        String fromDate = dt.formatDate(alarm);
        String toDate = dt.formatToDate(alarm);

        if (convertView == null) {
            alarmView = new LinearLayout(getContext());
            String inflater = Context.LAYOUT_INFLATER_SERVICE;
            LayoutInflater vi = (LayoutInflater) getContext().getSystemService(inflater);
            vi.inflate(resource, alarmView, true);
        } else {
            alarmView = (LinearLayout) convertView;
        }

        TextView toDateView = (TextView) alarmView.findViewById(R.id.db_alarm_to_date);
        TextView fromDateView = (TextView) alarmView.findViewById(R.id.db_alarm_from_date);
        TextView titleView = (TextView) alarmView.findViewById(R.id.db_alarm_title);

        toDateView.setText(toDate);
        fromDateView.setText(fromDate);
        titleView.setText(title);

        return alarmView;
    }
}
