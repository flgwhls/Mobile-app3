package com.example.mobile_app.Adaptors;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mobile_app.Activities;
import com.example.mobile_app.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class ActivitiesAdaptor extends FirebaseRecyclerAdapter<Activities, ActivitiesAdaptor.myviewholder>
{
    public ActivitiesAdaptor(@NonNull FirebaseRecyclerOptions<Activities> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull Activities model) {

        holder.date.setText(model.getDate());
        holder.type.setText(model.getType());
        Glide.with(holder.url.getContext()).load(model.getUrl()).into(holder.url);
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.activitiescard,parent,false);
        return new myviewholder(view);
    }

    class myviewholder extends RecyclerView.ViewHolder
    {



        ImageView url;
        TextView date,type;
        public myviewholder(@NonNull View itemView)
        {
            super(itemView);
            url=(ImageView)itemView.findViewById(R.id.iv_activities_card);
            date=(TextView) itemView.findViewById(R.id.tv_date_card);
            type=(TextView)itemView.findViewById(R.id.tv_type_card);

        }
    }
}
