package com.poseidon.calculation.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.poseidon.calculation.R;

import java.util.ArrayList;

/**
 * Created by Puls on 2/27/2019.
 */

public class SpnAdapter extends BaseAdapter {
    Context mContext;
    ArrayList<String> items;

    public SpnAdapter(Context context, ArrayList<String> items) {
        this.items = items;
        mContext = context;
    }
    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {

            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_count_list, null);
        }

        TextView itemView = (TextView) convertView.findViewById(R.id.tvItem);
        itemView.setText(items.get(position));
        return convertView;
    }
}
