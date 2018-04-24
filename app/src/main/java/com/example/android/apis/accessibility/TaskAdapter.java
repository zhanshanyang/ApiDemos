package com.example.android.apis.accessibility;

/**
 * Created by yangzhanshan on 2018/4/24.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.android.apis.R;

/** Adds Accessibility information to individual child views of rows in the list. */
final class TaskAdapter extends BaseAdapter {

    private String[] mLabels = null;
    private boolean[] mCheckboxes = null;
    private Context mContext = null;

    public TaskAdapter(Context context, String[] labels, boolean[] checkboxes) {
        super();
        mContext = context;
        mLabels = labels;
        mCheckboxes = checkboxes;
    }

    @Override
    public int getCount() {
        return mLabels.length;
    }

    /** Expands the views for individual list entries, and sets content descriptions for use by the
     *  TaskBackAccessibilityService.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(R.layout.tasklist_row, parent, false);
        }

        CheckBox checkbox = (CheckBox) convertView.findViewById(R.id.tasklist_finished);
        checkbox.setChecked(mCheckboxes[position]);

        TextView label = (TextView)(convertView.findViewById(R.id.tasklist_label));
        label.setText(mLabels[position]);

        String contentDescription = new StringBuilder()
                .append(mContext.getString(R.string.task_name))
                .append(' ')
                .append(mLabels[position]).toString();
        label.setContentDescription(contentDescription);

        convertView.setTag(position);

        return convertView;
    }

    @Override
    public Object getItem(int position) {
        return mLabels[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}