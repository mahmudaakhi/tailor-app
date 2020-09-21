package com.example.doorsteptailors;


import android.annotation.SuppressLint;
import android.database.AbstractCursor;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class BestRated extends Fragment {

    private ListView listView;
    private String[] TailorNames;
    View view;
    ArrayList<TailorListItems> tailorListItems ;
    //TailorListItems tailorListItemsNew;
    //DatabaseHelper databaseHelper = new DatabaseHelper(this.getContext());

    //int[] images = {R.drawable.tailor1,R.drawable.tailor2,R.drawable.tailor3};


    @SuppressLint("ValidFragment")
    public BestRated(ArrayList<TailorListItems> tailorListItems) {
        this.tailorListItems = tailorListItems;
    }

    /*@Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Toast.makeText(this.getContext(),"Data on BestRated"+tailorListItemsNew.getTailorName(),Toast.LENGTH_LONG).show();
    }*/


    @Override

    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        view=inflater.inflate(R.layout.fragment_best_rated, container, false);
        TailorNames = getResources().getStringArray(R.array.tailor_name);
        //TextView textView = (TextView) view.findViewById(R.id.BestRatedtxtId);
        listView = (ListView) view.findViewById(R.id.BRlistviewID);

        //Toast.makeText(getActivity(),"Data on BestRated"+tailorListItemsNew.getTailorName(),Toast.LENGTH_LONG).show();


                //listData.add(cursor.getString(0)+"."+cursor.getString(1)+"\n"+cursor.getString(5)+"\n"+cursor.getString(3)+" BDT");
                //Toast.makeText(this,"Data is available in database and data is:"+cursor.getString(7),Toast.LENGTH_LONG).show();
                //tailorListItemsNew.getTailorName();
                //tailorListItemsNew.getAvailability();
                //tailorListItemsNew.getRating();
                //Toast.makeText(this.getContext(),"Data is available in database and data is:"+tailorListItemsNew.getTailorName(),Toast.LENGTH_LONG).show();
                //tailorListItems.add(tailorListItemsNew);



        CustomAdapter adapter = new CustomAdapter(this.getContext(),tailorListItems);
        listView.setAdapter(adapter);


        return view;

    }

}
