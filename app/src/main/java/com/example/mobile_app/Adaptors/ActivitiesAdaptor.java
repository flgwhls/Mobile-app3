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

    ArrayList<Activities> list;
    ActivitiesHolder.OnActivitiesClickListener listener;

    public ActivitiesAdaptor (ArrayList<Activities>list, ActivitiesHolder.OnActivitiesClickListener _listener){
        this.list=list;
        listener= _listener;
}
    @NonNull
    @Override
    public ActivitiesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.activitiescard,parent,false);
        return new ActivitiesHolder(v, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ActivitiesHolder holder, int position) {


        holder.date.setText(list.get(position).getDate());
        holder.type.setText(list.get(position).getType());
        Picasso.get().load(list.get(position).getUrl()).fit().into(holder.iv);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    //implements is 2 part (implements View.OnClickListener + alt+invio need to select implement method)
    public static class ActivitiesHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {

        ImageView iv;
        TextView date,type;

        OnActivitiesClickListener listener;
        public ActivitiesHolder(@NonNull View itemView,OnActivitiesClickListener _listener) {
            super(itemView);

            listener=_listener;
            date=itemView.findViewById(R.id.tv_activitiescard_date);
            type=itemView.findViewById(R.id.tv_act_type);
            iv=itemView.findViewById(R.id.iv_activitiescard_img);
            itemView.setOnClickListener(this);

        }
        // derive from alt+invio (Implements)
        @Override
        public void onClick(View v) {

            listener.OnActivitiesClick(getAdapterPosition());
        }

        //2 part
        public interface OnActivitiesClickListener
        {
            public void OnActivitiesClick(int pos);
        }
    }

}

