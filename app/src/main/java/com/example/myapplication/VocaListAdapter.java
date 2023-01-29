package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class VocaListAdapter extends RecyclerView.Adapter<VocaListAdapter.ViewHolder>{
    private List<Word> vocaList;
    private WordDB db;

    public VocaListAdapter(List<Word> vocaList) {
        this.vocaList = vocaList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView mean;
        TextView date;
        ImageView delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.voca_name);
            mean = itemView.findViewById(R.id.voca_mean);
            date = itemView.findViewById(R.id.voca_date);
            delete = itemView.findViewById(R.id.delete_btn);
        }

        void onBind(Word item) {
            mean.setText(item.mean);
            long now = System.currentTimeMillis();
            Date d = new Date(now);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String getTime = sdf.format(d);
            date.setText(getTime);

            // 삭제버튼
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Word wordModel = vocaList.get(getAdapterPosition());

                    db.wordDao().delete(wordModel);

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
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.voca_list_item, parent, false);
        VocaListAdapter.ViewHolder vh = new VocaListAdapter.ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull VocaListAdapter.ViewHolder holder, int position) {
        Word item = vocaList.get(position);
        holder.name.setText(item.text);
        holder.onBind(item);
//        db = WordDB.getInstance(context);
    }

    @Override
    public int getItemCount() {
        return vocaList.size();
    }
}
