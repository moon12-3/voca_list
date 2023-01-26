package com.example.myapplication;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


public class MainFragment extends Fragment {

    List<WordModel> vocaList = new ArrayList<>();
    RoomDB db;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.fragment_container);

        db = RoomDB.getInstance(getActivity().getApplicationContext());

        vocaList = db.mainDao().getAll();

        view.findViewById(R.id.add_article).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).changeFragment(1);
            }
        });

        VocaListAdapter recyclerAdapter = new VocaListAdapter(vocaList, (Activity) getActivity().getApplicationContext());
        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }
}