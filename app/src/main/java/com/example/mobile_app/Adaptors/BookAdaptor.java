package com.example.mobile_app.Adaptors;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobile_app.Book;
import com.example.mobile_app.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
// Aaptor for books

public class BookAdaptor extends RecyclerView.Adapter<BookAdaptor.BookHolder> {
    // Arraylist for books
    ArrayList<Book> booklist;
    BookHolder.OnBookClickListener cardlistener;

    public BookAdaptor(ArrayList<Book> list, BookHolder.OnBookClickListener listener) {
        this.booklist = list;
        cardlistener = listener;
    }


    @NonNull
    @Override
    public BookAdaptor.BookHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.bookcard, parent, false);
        return new BookHolder(v, cardlistener);
    }

    @Override
    public void onBindViewHolder(@NonNull BookAdaptor.BookHolder holder, int position) {
        holder.title.setText(booklist.get(position).getTitle());
        holder.author.setText(booklist.get(position).getAuthor());
        holder.category.setText(booklist.get(position).getCategory());
        Picasso.get().load(booklist.get(position).getImglink()).fit().into(holder.iv_bookview);
    }

    @Override
    public int getItemCount() {
        return booklist.size();

    }

    public static class BookHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView title, author, category;
        ImageView iv_bookview;
        OnBookClickListener cardlistener;

        public BookHolder(@NonNull View itemView, BookHolder.OnBookClickListener listener) {
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
            //cardlistener.OnBookClick(getLayoutPosition());
        }

        //2 part
        public interface OnBookClickListener {
            void OnBookClick(int position);
        }
    }
}
