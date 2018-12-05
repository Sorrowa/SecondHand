package com.example.zhangzihao.secondhand.zzh.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.example.zhangzihao.secondhand.JavaBean.Book;

import java.util.ArrayList;

//todo:
public class MyBookAdapter extends RecyclerView.Adapter<MyBookAdapter.ViewHolder> {

    private ArrayList<Book> books;
    private Context context;

    public MyBookAdapter(ArrayList<Book> books,Context context){
        this.context=context;
        this.books=books;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
