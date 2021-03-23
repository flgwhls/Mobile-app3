package com.example.mobile_app.Adaptors;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobile_app.Book4Sell;
import com.example.mobile_app.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Book4SellAdapter extends RecyclerView.Adapter<Book4SellAdapter.Book4SellHolder> {
    // Arraylist for books
    ArrayList<Book4Sell> sellbooklist;
    Book4SellHolder.OnBookClickListener cardlistener;

    public Book4SellAdapter(ArrayList<Book4Sell> list, Book4SellHolder.OnBookClickListener listener) {
        this.sellbooklist = list;
        cardlistener = listener;
    }

    @NonNull
    @Override
    public Book4SellAdapter.Book4SellHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.bookcard, parent, false);
        return new Book4SellHolder(v, cardlistener);
    }

    @Override
    public void onBindViewHolder(@NonNull Book4SellHolder holder, int position) {
        holder.title.setText(sellbooklist.get(position).getTitle());
        holder.author.setText(sellbooklist.get(position).getAuthor());
        holder.category.setText(sellbooklist.get(position).getCategory());

        Picasso.get().load(sellbooklist.get(position).getImglink()).fit().into(holder.iv_bookview);


    }

    @Override
    public int getItemCount() {
        return sellbooklist.size();

    }

    public static class Book4SellHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView title, author, category;
        ImageView iv_bookview;
        OnBookClickListener cardlistener;

        public Book4SellHolder(@NonNull View itemView, Book4SellAdapter.Book4SellHolder.OnBookClickListener listener) {
            super(itemView);
            cardlistener = listener;
            title = itemView.findViewById(R.id.tv_bookcard_title);
            author = itemView.findViewById(R.id.tv_bookcard_author);
            category = itemView.findViewById(R.id.tv_bookcard_category);
            iv_bookview = itemView.findViewById(R.id.iv_bookcard_bookpic);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            cardlistener.OnBookClick(getAdapterPosition());
        }

        //2 part
        public interface OnBookClickListener {
            void OnBookClick(int position);
        }


    }
}
