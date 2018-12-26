package com.example.zhangzihao.secondhand.dyx.adapter;

/*
    create by:loser
    date:2018/12/25 23:59
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
import com.example.zhangzihao.secondhand.JavaBean.Book;
import com.example.zhangzihao.secondhand.R;

import java.util.ArrayList;

public class DealRvAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private ArrayList<Book> mDate = new ArrayList<>();
    private Context mContext;
    private OnItemClickListener mOnClickListener;

    enum TYPE {
        NONE,
        HAVE
    }

    public DealRvAdapter(Context context) {
        this.mContext = context;
    }


    public void setOnClickListener(OnItemClickListener mOnClickListener) {
        this.mOnClickListener = mOnClickListener;
    }

    public void setData(ArrayList<Book> data) {
        this.mDate = data;
        notifyDataSetChanged();
    }

    public interface OnItemClickListener{
        void onItemClickListener(int position);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE.NONE.ordinal()) {
            return new NoneViewHolder(LayoutInflater.from(mContext).inflate(R.layout.adapter_recycler_deal_type_none, parent, false));
        }
        return new HaveViewHolder(LayoutInflater.from(mContext).inflate(R.layout.adapter_recycler_deal_type_have, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        if (holder instanceof NoneViewHolder) {
            ((NoneViewHolder) holder).textView.setText("没有任何交易记录...");
        } else if (holder instanceof HaveViewHolder) {
            ((HaveViewHolder) holder).position = position;
            if (mDate.get(position).getImgPath() != null) {
                Glide.with(mContext).load(mDate.get(position).getImgPath()).into(((HaveViewHolder) holder).image);
            } else {
                Glide.with(mContext).load(R.mipmap.ic_launcher).into(((HaveViewHolder) holder).image);
            }
            ((HaveViewHolder) holder).name.setText(mDate.get(position).getName());
            ((HaveViewHolder) holder).type.setText(mDate.get(position).getType());
        }

    }

    @Override
    public int getItemViewType(int position) {
        if (mDate.size() == 0) {
            return TYPE.NONE.ordinal();
        }
        return TYPE.HAVE.ordinal();
    }

    @Override
    public int getItemCount() {
        if (mDate.size() == 0) {
            return 1;
        }
        return mDate.size();
    }

    class NoneViewHolder extends RecyclerView.ViewHolder{
        TextView textView;

        NoneViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.deal_type_none_text);
        }
    }


    class HaveViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView name;
        TextView type;
        int position;

        HaveViewHolder(View itemView) {
            super(itemView);

            image = (ImageView) itemView.findViewById(R.id.deal_type_have_image);
            name = (TextView) itemView.findViewById(R.id.deal_type_have_name);
            type = (TextView) itemView.findViewById(R.id.deal_type_have_type);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnClickListener != null) {
                        mOnClickListener.onItemClickListener(position);
                    }
                }
            });
        }
    }
}
