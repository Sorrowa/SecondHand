package com.example.zhangzihao.secondhand.zzh.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.zhangzihao.secondhand.JavaBean.Book;
import com.example.zhangzihao.secondhand.R;
import com.example.zhangzihao.secondhand.zzh.View.BookInfoActibity;

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
        View view= LayoutInflater.from(context).inflate(R.layout.main_item_view
                ,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.setInfo(books.get(position));

        //todo:跳转到图书信息界面
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, BookInfoActibity.class);
                intent.putExtra("book", books.get(position).getBookId());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        View view;
        ImageView mimageView;
        TextView bookName;
        TextView bookType;

        public ViewHolder(View itemView) {
            super(itemView);
            view=itemView;
            mimageView=itemView.findViewById(R.id.book_image_main);
            bookName=itemView.findViewById(R.id.book_name_main);
            bookType=itemView.findViewById(R.id.book_type_main);
        }

        public void setInfo(Book book){
            //todo:设置图片
            bookName.setText(book.getName());
            //bookWords.setText(book.getIntroduction());
            bookType.setText(book.getType());
            if (book.getImgPath()!=null) {
                Glide.with(context).load(book.getImgPath()).into(mimageView);
            }
        }
    }
}
