package com.example.mobile_app.Adaptors;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobile_app.Book;
import com.example.mobile_app.R;
import java.util.ArrayList;

public class BookAdaptor extends RecyclerView.Adapter<BookAdaptor.BookHolder>
{
    // Arraylist for books
    ArrayList<Book>booklist;
    public BookAdaptor(ArrayList<Book>booklist){
        this.booklist=booklist;
    }


    @NonNull
    @Override
    public BookAdaptor.BookHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.bookcard,parent,false);
        return new BookHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull BookAdaptor.BookHolder holder, int position) {
        holder.title.setText(booklist.get(position).getTitle());
        holder.author.setText(booklist.get(position).getAuthor());
        holder.category.setText(booklist.get(position).getCategory());


    }

    @Override
    public int getItemCount() {
        return booklist.size();
    }

    public static class BookHolder extends RecyclerView.ViewHolder
    {
        ImageView bookimg;
        TextView title,author,category;
        public BookHolder(@NonNull View itemView){
            super(itemView);
            bookimg = itemView.findViewById(R.id.iv_bookcard_bookpic);
            title = itemView.findViewById(R.id.tv_bookcard_title);
            author = itemView.findViewById(R.id.tv_bookcard_author);
            category = itemView.findViewById(R.id.tv_bookcard_category);

        }
    }
}