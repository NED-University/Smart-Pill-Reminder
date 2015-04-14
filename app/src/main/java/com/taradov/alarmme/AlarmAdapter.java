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
 * Created by Inshal on 21-Mar-15.
 */

public class AlarmAdapter extends ArrayAdapter<Alarm> {
    int resource;
    Context context;

    public AlarmAdapter(Context _context, int _resource, List<Alarm> _items)
    {
        super(_context, _resource, _items);
        resource = _resource;
        context = _context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        GridLayout alarmView;
        DateTime dt = new DateTime(context);

        Alarm alarm = getItem(position);
        String title = alarm.getTitle();
        String fromDate = dt.formatFromDate(alarm);
        String toDate = dt.formatToDate(alarm);

        if (convertView == null) {
            alarmView = new GridLayout(getContext());
            String inflater = Context.LAYOUT_INFLATER_SERVICE;
            LayoutInflater vi = (LayoutInflater)getContext().getSystemService(inflater);
            vi.inflate(resource, alarmView, true);
        } else {
            alarmView = (GridLayout) convertView;
        }

        TextView toDateView = (TextView)alarmView.findViewById(R.id.db_alarm_to_date);
        TextView fromDateView = (TextView)alarmView.findViewById(R.id.db_alarm_from_date);
        TextView titleView = (TextView)alarmView.findViewById(R.id.db_alarm_title);

        toDateView.setText("ToDate: " + toDate);
        fromDateView.setText("FromDate: " + fromDate);
        titleView.setText("Name: " + title);

        return alarmView;
    }
}
