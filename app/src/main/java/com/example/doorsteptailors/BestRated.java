package com.example.doorsteptailors;


import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class BestRated extends Fragment {
    RecyclerView recyclerView;
    Adapter adapter;
    ArrayList<String> items;


    @Override

    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


      View  view=inflater.inflate(R.layout.fragment_best_rated, container, false);

        items = new ArrayList<>();
        items.add("First CardView Item");
        items.add("Second CardView Item");
        items.add("Third CardView Item");
        items.add("Fourth CardView Item");
        items.add("Fifth CardView Item");
        items.add("Sixth CardView Item");

        recyclerView = view.findViewById(R.id.BRlistviewID);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new Adapter(getContext(),items);
        recyclerView.setAdapter(adapter);

        return view;

    }

}
