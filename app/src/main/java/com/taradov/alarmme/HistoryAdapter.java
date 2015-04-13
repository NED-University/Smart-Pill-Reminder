package com.taradov.alarmme;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
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
        LinearLayout historyView;

        DateFormat df = DateFormat.getDateTimeInstance();

        HistoryItem historyItem = getItem(position);
        String id = String.valueOf(historyItem.getId());
        String alarmId = String.valueOf(historyItem.getAlarmId());
        String timeDue = df.format(new Date(historyItem.getTimeDue()));
        String timeTaken = df.format(new Date(historyItem.getTimeTaken()));
        String validation = historyItem.getValidation();

        if (convertView == null) {
            historyView = new LinearLayout(getContext());
            String inflater = Context.LAYOUT_INFLATER_SERVICE;
            LayoutInflater vi = (LayoutInflater)getContext().getSystemService(inflater);
            vi.inflate(resource, historyView, true);
        } else {
            historyView = (LinearLayout) convertView;
        }

//        TextView idView = (TextView)historyView.findViewById(R.id.db_history_item_id);
        TextView alarmView = (TextView)historyView.findViewById(R.id.db_history_item_alarm);
        TextView timeDueView = (TextView)historyView.findViewById(R.id.db_history_item_due);
        TextView timeTakenView = (TextView)historyView.findViewById(R.id.db_history_item_taken);
        TextView validationView = (TextView)historyView.findViewById(R.id.db_history_item_validation);

//        idView.setText(id);
        alarmView.setText(alarmId);
        timeDueView.setText(timeDue);
        timeTakenView.setText(timeTaken);
        validationView.setText(validation);

        return historyView;
    }
}
