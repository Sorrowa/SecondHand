package com.example.zhangzihao.secondhand.dyx.adapter;

/*
    create by:loser
    date:2018/12/29 0:40
*/

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.zhangzihao.secondhand.Base.URL;
import com.example.zhangzihao.secondhand.JavaBean.Book;
import com.example.zhangzihao.secondhand.R;

import java.util.ArrayList;
import java.util.List;

public class MyBookRvAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Context mContext;
    private List<Book> mList = new ArrayList<>();
    private OnItemClickListener mListener;


    public MyBookRvAdapter(Context context) {
        this.mContext = context;
    }


    public interface OnItemClickListener{
        void onItemClickListener(int position, Book book);
    }


    public void setList(List<Book> list) {
        this.mList = list;
        notifyDataSetChanged();
    }

    public void setListener(OnItemClickListener listener) {
        this.mListener = listener;
    }

    enum TYPE{
        NONE,
        HAVE
    }

    @Override
    public int getItemViewType(int position) {
        if (mList.size() == 0) {
            return TYPE.NONE.ordinal();
        }
        return TYPE.HAVE.ordinal();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE.NONE.ordinal()) {
            return new NoneBook(LayoutInflater.from(mContext).inflate(R.layout.adapter_recycler_my_book_type_none, parent, false));
        }
        return new HaveBook(LayoutInflater.from(mContext).inflate(R.layout.adapter_recycler_my_book_type_have, parent, false));
    }


    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder,  int position) {
        if (holder instanceof HaveBook) {
            ((HaveBook) holder).position = position;
            ((HaveBook) holder).name.setText(mList.get(position).getName());
            ((HaveBook) holder).type.setText(mList.get(position).getType());
            Glide.with(mContext).load(URL.IMGS + mList.get(position).getImgPath()).into(((HaveBook) holder).image);
        }

    }

    @Override
    public int getItemCount() {
        if (mList.size() == 0) {
            return 1;
        }
        return mList.size();
    }

    class HaveBook extends RecyclerView.ViewHolder {
        int position;
        TextView name;
        TextView type;
        ImageView image;

        HaveBook(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.my_book_type_have_name);
            type = (TextView) itemView.findViewById(R.id.my_book_type_have_type);
            image = (ImageView) itemView.findViewById(R.id.my_book_type_have_image);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        mListener.onItemClickListener(position, mList.get(position));
                    }
                }
            });
        }
    }


    class NoneBook extends RecyclerView.ViewHolder {
        NoneBook(View itemView) {
            super(itemView);
        }
    }

}
