package com.example.myapplication;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class VocaListAdapter extends RecyclerView.Adapter<VocaListAdapter.ViewHolder>{
    private List<WordModel> vocaList;
    private Activity context;
    private RoomDB db;

    public VocaListAdapter(List<WordModel> vocaList, Activity context) {
        this.vocaList = vocaList;
        this.context = context;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView mean;
        TextView date;
        Button delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.voca_name);
            mean = itemView.findViewById(R.id.voca_mean);
            date = itemView.findViewById(R.id.voca_date);
            delete = itemView.findViewById(R.id.delete_btn);
        }

        void onBind(WordModel item) {
            name.setText(item.getText());
            mean.setText(item.getMean());
            long now = System.currentTimeMillis();
            Date d = new Date(now);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String getTime = sdf.format(d);
            date.setText(getTime);
            
            // 삭제버튼
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    WordModel wordModel = vocaList.get(getAdapterPosition());

                    db.mainDao().delete(wordModel);

                    int position = getAdapterPosition();
                    vocaList.remove(position);
                    notifyItemRemoved(position);
                    notifyItemRangeChanged(position, vocaList.size());
                }

            });
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
        final WordModel wordModel = vocaList.get(position);
        db = RoomDB.getInstance(context);
        holder.onBind(wordModel);

    }

    @Override
    public int getItemCount() {
        return vocaList.size();
    }
}
