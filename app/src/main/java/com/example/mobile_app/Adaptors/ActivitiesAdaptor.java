package com.example.mobile_app.Adaptors;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobile_app.Activities;
import com.example.mobile_app.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ActivitiesAdaptor extends RecyclerView.Adapter<ActivitiesAdaptor.ActivitiesHolder> {

    //arraylist of Activities
    ArrayList<Activities> list;
    ActivitiesHolder.OnActivitiesClickListener listener;

    public ActivitiesAdaptor(ArrayList<Activities> list, ActivitiesHolder.OnActivitiesClickListener _listener) {
        this.list = list;
        listener = _listener;
    }

    @NonNull
    @Override
    public ActivitiesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activitiescard, parent, false);
        return new ActivitiesHolder(v, listener);
    }

    //this method will hold the view of card in the recycle view
    @Override
    public void onBindViewHolder(@NonNull ActivitiesHolder holder, int position) {


        holder.date.setText(list.get(position).getDate());
        holder.type.setText(list.get(position).getType());
        Picasso.get().load(list.get(position).getUrl()).fit().into(holder.iv);
    }

    // this method return the exactily position of items in the arraylist
    @Override
    public int getItemCount() {
        return list.size();
    }


    public static class ActivitiesHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        //variables
        ImageView iv;
        TextView date, type;

        OnActivitiesClickListener listener;

        public ActivitiesHolder(@NonNull View itemView, OnActivitiesClickListener _listener) {
            super(itemView);

            listener = _listener;
            //variables views
            date = itemView.findViewById(R.id.tv_activitiescard_date);
            type = itemView.findViewById(R.id.tv_act_type);
            iv = itemView.findViewById(R.id.iv_activitiescard_img);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

            listener.OnActivitiesClick(getAdapterPosition());
        }


        public interface OnActivitiesClickListener {
            public void OnActivitiesClick(int pos);
        }
    }

}

