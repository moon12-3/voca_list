package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class PlusFragment extends Fragment {

    EditText text;
    EditText mean;
    Button add, cancel;
    WordDB db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_plus, container, false);

        text = view.findViewById(R.id.text);
        mean = view.findViewById(R.id.mean);
        add = view.findViewById(R.id.plus_btn);

        Context context = getActivity().getApplicationContext();
        db = WordDB.getInstance(context);


        class InsertRunnable implements Runnable {

            @Override
            public void run() {
                Word word = new Word();
                word.text = text.getText().toString();
                word.mean = mean.getText().toString();
                db.getInstance(context).wordDao().insertAll(word);
            }
        }


        add.setOnClickListener(v -> {
            InsertRunnable insertRunnable = new InsertRunnable();
            Thread t = new Thread(insertRunnable);
            t.start();
            ((MainActivity)getActivity()).changeFragment(2);
        });

        return view;
    }
}