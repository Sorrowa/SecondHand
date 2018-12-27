package com.example.zhangzihao.secondhand.dyx.adapter;

/*
    create by:loser
    date:2018/12/27 14:18
*/

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
import com.example.zhangzihao.secondhand.JavaBean.Comment;
import com.example.zhangzihao.secondhand.JavaBean.Data;
import com.example.zhangzihao.secondhand.R;

import java.util.List;

public class CommentRvAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<Data.CommentsBean> mComment;

    public CommentRvAdapter(Context context) {
        this.mContext = context;
    }

    public void setComment(List<Data.CommentsBean> comment) {
        this.mComment = comment;
        notifyDataSetChanged();
    }

    enum TYPE {
        NONE,
        HAVE
    }

    @Override
    public int getItemViewType(int position) {
        if (mComment.size() == 0) {
            return TYPE.NONE.ordinal();
        }
        return TYPE.HAVE.ordinal();
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE.NONE.ordinal()) {
            return new NoneComment(LayoutInflater.from(mContext).inflate(R.layout.adapter_recycler_comment_type_none, parent, false));
        }
        return new HaveComment(LayoutInflater.from(mContext).inflate(R.layout.adapter_recycler_comment_type_have, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof NoneComment) {
             //不处理
        } else if (holder instanceof HaveComment) {
            ((HaveComment) holder).mName.setText(mComment.get(position).getUser().getName());
            ((HaveComment) holder).mComment.setText(mComment.get(position).getContent());
            if (mComment.get(position).getUser().getHeadPath() == null) {
                Glide.with(mContext).load(R.mipmap.ic_launcher).into(((HaveComment) holder).mImage);
            } else {
                Glide.with(mContext).load(URL.IMGS + mComment.get(position).getUser().getHeadPath()).into(((HaveComment) holder).mImage);
            }
            ((HaveComment) holder).mDate.setText(mComment.get(position).getDate());
        }
    }

    @Override
    public int getItemCount() {
        if (mComment.size() == 0) {
            return 1;
        }
        return mComment.size();
    }

    class NoneComment extends RecyclerView.ViewHolder {
        TextView mText;

        NoneComment(View itemView) {
            super(itemView);
            mText = (TextView) itemView.findViewById(R.id.adapter_comment_type_none_text);
        }
    }

    class HaveComment extends RecyclerView.ViewHolder {
        TextView mName, mDate, mComment;
        ImageView mImage;
        HaveComment(View itemView) {
            super(itemView);
            mName = (TextView) itemView.findViewById(R.id.adapter_comment_type_have_name);
            mDate = (TextView) itemView.findViewById(R.id.adapter_comment_type_have_date);
            mComment = (TextView) itemView.findViewById(R.id.adapter_comment_type_have_comment);

            mImage = (ImageView) itemView.findViewById(R.id.adapter_comment_type_have_image);
        }
    }

}
