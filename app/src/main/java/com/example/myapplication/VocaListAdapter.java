package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class VocaListAdapter extends RecyclerView.Adapter<VocaListAdapter.ViewHolder>{
    private ArrayList<String> vocaNameList;

    public VocaListAdapter(ArrayList<String> vocaNameList) {
        this.vocaNameList = vocaNameList;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView date;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.voca_name);
            date = itemView.findViewById(R.id.voca_date);
        }

        void onBind(String item) {
            name.setText(item);
            date.setText(String.valueOf(System.currentTimeMillis()));
        }

    }

    @NonNull
    @Override
    public VocaListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.voca_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VocaListAdapter.ViewHolder holder, int position) {
        holder.onBind(vocaNameList.get(position));
    }

    @Override
    public int getItemCount() {
        return vocaNameList.size();
    }
}
