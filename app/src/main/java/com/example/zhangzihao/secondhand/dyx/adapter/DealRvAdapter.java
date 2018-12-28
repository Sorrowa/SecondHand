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
import com.example.zhangzihao.secondhand.Base.URL;
import com.example.zhangzihao.secondhand.JavaBean.Book;
import com.example.zhangzihao.secondhand.JavaBean.DealBean;
import com.example.zhangzihao.secondhand.R;

import java.util.ArrayList;
import java.util.List;

public class DealRvAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private List<DealBean> mDate = new ArrayList<>();
    private Context mContext;
    private OnItemClickListener mOnClickListener;
    private String mEmail;
    /**
     * 0: 没有登入
     * 1: 已经登入
     */
    private int mState = 0;

    enum TYPE {
        NONE,
        HAVE
    }

    public DealRvAdapter(Context context) {
        this.mContext = context;
        this.mState = 0;
    }


    public void setOnClickListener(OnItemClickListener mOnClickListener) {
        this.mOnClickListener = mOnClickListener;
    }

    public void setData(List<DealBean> data, String email) {
        this.mDate = data;
        this.mEmail = email;
        mState = 1;
        notifyDataSetChanged();
    }

    public interface OnItemClickListener{
        void onItemClickListener(int position, DealBean book);
    }

    public void setEmptyData() {
        this.mDate = new ArrayList<>();
        mState = 0;
        notifyDataSetChanged();
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
            if (mState == 0) {
                ((NoneViewHolder) holder).textView.setText("没有登入...");
            } else {
                ((NoneViewHolder) holder).textView.setText("没有任何交易记录...");
            }
        } else if (holder instanceof HaveViewHolder) {
            ((HaveViewHolder) holder).position = position;
            if (mDate.get(position).getImgPath() != null) {
                Glide.with(mContext).load(URL.IMGS + mDate.get(position).getImgPath()).into(((HaveViewHolder) holder).image);
            } else {
                Glide.with(mContext).load(R.mipmap.ic_launcher).into(((HaveViewHolder) holder).image);
            }
            ((HaveViewHolder) holder).name.setText(mDate.get(position).getName());
            ((HaveViewHolder) holder).type.setText(mDate.get(position).getType());
            if (mDate.get(position).getState() == 2) {
                ((HaveViewHolder) holder).confirm.setText("未确认...");
                ((HaveViewHolder) holder).confirm.setTextColor(mContext.getResources().getColor(R.color.fragment_deal_no_confirm));
            } else if (mDate.get(position).getState() == 4) {
                ((HaveViewHolder) holder).confirm.setText("已确认...");
                ((HaveViewHolder) holder).confirm.setTextColor(mContext.getResources().getColor(R.color.fragment_deal_confirmed));
            } else if (mDate.get(position).getState() == 3) {
                ((HaveViewHolder) holder).confirm.setText("待确认...");
                ((HaveViewHolder) holder).confirm.setTextColor(mContext.getResources().getColor(R.color.fragment_deal_confirmed));
            }

            if (mDate.get(position).getChangeBook() != null) {
                //init change
                Glide.with(mContext).load(URL.IMGS + mDate.get(position).getChangeBook().getImgPath()).into(((HaveViewHolder) holder).change_image);
                ((HaveViewHolder) holder).change_name.setText(mDate.get(position).getChangeBook().getName());
                ((HaveViewHolder) holder).change_type.setText(mDate.get(position).getChangeBook().getType());
            }
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
        TextView confirm;
        int position;

        ImageView change_image;
        TextView change_name;
        TextView change_type;


        HaveViewHolder(View itemView) {
            super(itemView);

            image = (ImageView) itemView.findViewById(R.id.deal_type_have_image);
            name = (TextView) itemView.findViewById(R.id.deal_type_have_name);
            type = (TextView) itemView.findViewById(R.id.deal_type_have_type);
            confirm = (TextView) itemView.findViewById(R.id.adapter_comment_type_have_confirm);

            change_image = (ImageView) itemView.findViewById(R.id.deal_type_have_change_book_image);
            change_name = (TextView) itemView.findViewById(R.id.deal_type_have_change_book_name);
            change_type = (TextView) itemView.findViewById(R.id.deal_type_have_change_book_type);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnClickListener != null && mDate.get(position).getState() == 2 && mEmail.equals(mDate.get(position).getEmail())) {
                        mOnClickListener.onItemClickListener(position, mDate.get(position));
                    }
                }
            });
        }
    }
}
