package com.example.myapplication;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.room.Room;

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

    List<WordModel> vocaList = new ArrayList<>();
    RoomDB db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_plus, container, false);

        text = view.findViewById(R.id.text);
        mean = view.findViewById(R.id.mean);
        add = view.findViewById(R.id.plus_btn);

        db = RoomDB.getInstance((Activity) getActivity().getApplicationContext());

        vocaList = db.mainDao().getAll();

        add.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                String sText = text.getText().toString().trim();
                if (!sText.equals(""))
                {
                    WordModel data = new WordModel();
                    data.setText(sText);
                    db.mainDao().insert(data);

                    text.setText("");

                    vocaList.clear();
                    vocaList.addAll(db.mainDao().getAll());

                }
            }
        });

        return view;
    }
}