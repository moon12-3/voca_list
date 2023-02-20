package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import java.util.ArrayList;
import java.util.List;


public class MainFragment extends Fragment {

    List<Word> vocaList;
    VocaListAdapter recyclerAdapter;
    private WordDB db;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.fragment_container);

        db = WordDB.getInstance(getActivity().getApplicationContext());

        Context context = getActivity().getApplicationContext();

       class InsertRunnable implements Runnable {

           @Override
           public void run() {
               try {
                    vocaList = db.getInstance(context).wordDao().getAll();
                    recyclerAdapter = new VocaListAdapter(vocaList, getActivity().getApplicationContext());
                    recyclerAdapter.notifyDataSetChanged();

                   recyclerView.setAdapter(recyclerAdapter);
                   recyclerView.setLayoutManager(new LinearLayoutManager(context, RecyclerView.VERTICAL, false));
               }
               catch (Exception e) {
                   Log.d("test", e.toString());
               }
           }
       }

       InsertRunnable insertRunnable = new InsertRunnable();
       Thread t = new Thread(insertRunnable);
       t.start();

        view.findViewById(R.id.add_article).setOnClickListener(v ->{
            ((MainActivity)getActivity()).changeFragment(1);
        });

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        WordDB.destroyInstance();
//        db = null;
    }
}