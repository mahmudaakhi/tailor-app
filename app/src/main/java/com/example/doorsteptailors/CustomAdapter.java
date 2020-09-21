package com.example.doorsteptailors;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {

    Context context;

    String[] tailorNames;
    private LayoutInflater inflater;
    ArrayList<TailorListItems> tailorListItems;


    public CustomAdapter(Context bestRated, ArrayList<TailorListItems> tailorListItems) {
        this.context = bestRated;
        this.tailorListItems = tailorListItems;

    }

    @Override
    public int getCount() {
        return tailorListItems.size();
    }

    @Override
    public Object getItem(int position) {
        return tailorListItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        TailorListItems tailorListItemsNew = tailorListItems.get(position);

        if(convertView == null)
        {
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.row,null);
        }
        //ImageView imageView = (ImageView) convertView.findViewById(R.id.imageViewTailorId);
        TextView textView = (TextView) convertView.findViewById(R.id.TailorNameID);
        TextView textView1 = (TextView) convertView.findViewById(R.id.RegionId);

        textView.setText(tailorListItemsNew.getTailorName());
        textView1.setText("Location:"+tailorListItemsNew.getAvailability());
        //imageView.setImageResource(images[position]);

        return convertView;
    }
}
