package com.example.mobile_app.Adaptors;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.mobile_app.ForumTopic;
import com.example.mobile_app.R;

import java.util.ArrayList;

public class ForumTopicAdaptor extends RecyclerView.Adapter<ForumTopicAdaptor.ForumTopicHolder>{

    ArrayList<ForumTopic>topicList;

    ForumTopicHolder.OnTopicClickListener topicListener;


    public ForumTopicAdaptor(ArrayList<ForumTopic>list, ForumTopicHolder.OnTopicClickListener listener){
        this.topicList=list;
        topicListener = listener;
    }

    @NonNull
    @Override
    public ForumTopicAdaptor.ForumTopicHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.topiccard_new,parent,false);
        return new ForumTopicAdaptor.ForumTopicHolder(v, topicListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ForumTopicAdaptor.ForumTopicHolder holder, int position) {
        holder.topicName.setText(topicList.get(position).getTopicName());
        holder.topicDesc.setText(topicList.get(position).getTopicDesc());
    }

    @Override
    public int getItemCount() {
        return topicList.size();

    }

    public static class ForumTopicHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {

        TextView topicName,topicDesc;


        OnTopicClickListener topicListener;


        public ForumTopicHolder(@NonNull View itemView, ForumTopicAdaptor.ForumTopicHolder.OnTopicClickListener listener){
            super(itemView);
            topicListener = listener;
            topicName = itemView.findViewById(R.id.topiccard_name);
            topicDesc = itemView.findViewById(R.id.topiccard_desc);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            topicListener.OnTopicClick(getAdapterPosition());
        }

        public interface OnTopicClickListener
        {
            void OnTopicClick(int position);
        }


    }
}
