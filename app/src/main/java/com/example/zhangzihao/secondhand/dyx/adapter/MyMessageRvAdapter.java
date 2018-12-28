package com.example.zhangzihao.secondhand.dyx.adapter;

/*
    create by:loser
    date:2018/12/29 3:23
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
import com.example.zhangzihao.secondhand.JavaBean.MessageBean;
import com.example.zhangzihao.secondhand.R;

import java.util.List;

public class MyMessageRvAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<MessageBean> mComment;

    public MyMessageRvAdapter(Context context) {
        this.mContext = context;
    }

    public void setComment(List<MessageBean> comment) {
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
            return new NoneMessage(LayoutInflater.from(mContext).inflate(R.layout.adapter_recycler_my_message_type_none, parent, false));
        }
        return new HaveMessage(LayoutInflater.from(mContext).inflate(R.layout.adapter_recycler_my_message_type_have, parent, false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof NoneMessage) {
            //不处理
        } else if (holder instanceof HaveMessage) {
            ((HaveMessage) holder).mName.setText(mComment.get(position).getBook().getName());
            ((HaveMessage) holder).mComment.setText("评论：" + mComment.get(position).getContent());
            ((HaveMessage) holder).mType.setText("(" + mComment.get(position).getBook().getType() + ")");
            Glide.with(mContext).load(URL.IMGS + mComment.get(position).getBook().getImgPath()).into(((HaveMessage) holder).mImage);

        }
    }

    @Override
    public int getItemCount() {
        if (mComment.size() == 0) {
            return 1;
        }
        return mComment.size();
    }

    class NoneMessage extends RecyclerView.ViewHolder {


        NoneMessage(View itemView) {
            super(itemView);

        }
    }

    class HaveMessage extends RecyclerView.ViewHolder {
        TextView mName, mComment, mType;
        ImageView mImage;
        HaveMessage(View itemView) {
            super(itemView);
            mName = (TextView) itemView.findViewById(R.id.my_message_type_have_name);
            mComment = (TextView) itemView.findViewById(R.id.my_message_type_have_comment);
            mType = (TextView) itemView.findViewById(R.id.my_message_type_have_type);

            mImage = (ImageView) itemView.findViewById(R.id.my_message_type_have_image);
        }
    }
}
