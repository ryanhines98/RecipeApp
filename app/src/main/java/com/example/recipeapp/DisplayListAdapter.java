package com.example.recipeapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class DisplayListAdapter extends ArrayAdapter<String> {

    private ArrayList<String> items;
    private final LayoutInflater inflater;

    public DisplayListAdapter(Context context, List<String> items) {
        super(context, R.layout.list_item, items);
        inflater = LayoutInflater.from(context);
        this.items = (ArrayList<String>)items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView;
        rowView = inflater.inflate(R.layout.list_item, parent, false);

        TextView textView = rowView.findViewById(R.id.listItemView);
        textView.setText(items.get(position));

        return rowView;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    public void setItems(List<String> items) {
        this.items = (ArrayList<String>)items;
        notifyDataSetChanged();
    }
}
