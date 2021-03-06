package com.example.zhangzihao.secondhand.zzh.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.zhangzihao.secondhand.JavaBean.Book;
import com.example.zhangzihao.secondhand.R;
import com.example.zhangzihao.secondhand.dyx.activity.BookActivity;
import com.example.zhangzihao.secondhand.zzh.View.BookInfoActibity;

import java.util.ArrayList;

import static com.example.zhangzihao.secondhand.Base.URL.IMGS;

/**
 * 给main界面提供一个RecycleView的适配器
 */
public class MyAdapterForMainRecycleView extends RecyclerView.Adapter<MyAdapterForMainRecycleView.MyViewHolder> {

    private ArrayList<Book> books;
    private Context context;

    public MyAdapterForMainRecycleView(Context context, ArrayList<Book> books){
        this.context=context;
        this.books=books;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.main_item_view,parent,
                false);
        MyViewHolder viewHolder=new MyViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.setInfo(books.get(position));

        //todo:跳转到图书信息界面
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, BookActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable("book", books.get(position));
                intent.putExtra("book_bundle", bundle);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        View view;
        ImageView mimageView;
        TextView bookName;
        TextView bookType;
        //TextView bookWords;

        public MyViewHolder(View itemView) {
            super(itemView);
            view=itemView;
            mimageView=itemView.findViewById(R.id.book_image_main);
            bookName=itemView.findViewById(R.id.book_name_main);
            bookType=itemView.findViewById(R.id.book_type_main);
            //bookWords=itemView.findViewById(R.id.book_words_main);
        }

        public void setInfo(Book book){
            //todo:设置图片
            bookName.setText(book.getName());
            //bookWords.setText(book.getIntroduction());
            bookType.setText(book.getType());
            if (book.getImgPath()!=null){
                Glide.with(context)
                        .load(IMGS+book.getImgPath())
                        .into(mimageView);
            }
        }
    }
}
