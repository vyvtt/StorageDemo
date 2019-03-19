package com.thuyvy.storm.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.thuyvy.storm.R;
import com.thuyvy.storm.model.Storm;

import java.util.List;

public class StormAdapter extends ArrayAdapter<Storm> {

    private Context context;
    private List<Storm> data;

    public StormAdapter(Context context, List<Storm> data) {
        super(context, 0, data);
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, @Nullable View convertView, @Nullable ViewGroup parent) {

        View view = convertView;
        if (view == null)
            view = LayoutInflater.from(context).inflate(R.layout.simple_list_item, parent, false);

        TextView txtId = view.findViewById(R.id.txtId);
        TextView txtName = view.findViewById(R.id.txtName);
        TextView txtWindSpeed = view.findViewById(R.id.txtWindSpeed);

        Storm storm = data.get(position);

        txtId.setText("ID: " + storm.getId());
        txtName.setText("Name: " + storm.getName());
        txtWindSpeed.setText("Wind Speed: " + storm.getWindSpeed() + "");

        return view;
    }
}
