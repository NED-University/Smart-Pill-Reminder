package com.taradov.alarmme;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridLayout;
import android.widget.TextView;

import java.sql.Date;
import java.text.DateFormat;
import java.util.List;

/**
 * Created by Inshal on 21-Mar-15.
 */
public class HistoryAdapter extends ArrayAdapter<HistoryItem> {
    int resource;
    Context context;

    public HistoryAdapter(Context _context, int _resource, List<HistoryItem> _items)
    {
        super(_context, _resource, _items);
        resource = _resource;
        context = _context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        GridLayout historyView;

        DateFormat df = DateFormat.getDateTimeInstance();

        // TODO: Add QRCode entry

        HistoryItem historyItem = getItem(position);
        String id = String.valueOf(historyItem.getId());
        String alarmId = String.valueOf(historyItem.getAlarmId());
        String timeDue = df.format(new Date(historyItem.getTimeDue()));

        String timeTaken = "";
        if (historyItem.getTimeTaken() != 0)
            timeTaken = df.format(new Date(historyItem.getTimeTaken()));

        String validation = historyItem.getValidation();

        if (convertView == null) {
            historyView = new GridLayout(getContext());
            String inflater = Context.LAYOUT_INFLATER_SERVICE;
            LayoutInflater vi = (LayoutInflater)getContext().getSystemService(inflater);
            vi.inflate(resource, historyView, true);
        } else {
            historyView = (GridLayout) convertView;
        }

//        TextView idView = (TextView)historyView.findViewById(R.id.db_history_item_id);
        TextView alarmView = (TextView)historyView.findViewById(R.id.db_history_item_alarm);
        TextView timeDueView = (TextView)historyView.findViewById(R.id.db_history_item_due);
        TextView timeTakenView = (TextView)historyView.findViewById(R.id.db_history_item_taken);
        TextView validationView = (TextView)historyView.findViewById(R.id.db_history_item_validation);

//        idView.setText(id);
        alarmView.setText("Alarm: " + alarmId);
        timeDueView.setText("TimeDue: " + timeDue);
        timeTakenView.setText("TimeTaken: " + timeTaken);
        validationView.setText("Status: " + validation);

        return historyView;
    }
}
