package com.example.zhangzihao.secondhand.syf.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.zhangzihao.secondhand.R;

import jp.wasabeef.glide.transformations.BlurTransformation;


public class InfoFragment extends Fragment {

    private View view;
    private ImageView head,background;
    TextView tile,name,email;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.info_fragment_layout,container,false);
        this.view = view;

        initView();
        return view;
    }

    public void initView(){
        head = (ImageView) view.findViewById(R.id.info_head);
        background = view.findViewById(R.id.info_background);
        tile = view.findViewById(R.id.info_tile);
        name = view.findViewById(R.id.info_name);
        email = view.findViewById(R.id.info_email);

        RequestOptions options = new RequestOptions()
                .placeholder(R.mipmap.waiting)
                .error(R.mipmap.default_head)
                .diskCacheStrategy(DiskCacheStrategy.NONE);

        Glide.with(this)
                .load("132.232.89.108:8081/imgs/1.png")
                .apply(options)
                .into(head);
        Glide.with(this)
                .load(R.drawable.info_background)
                .apply(options.bitmapTransform(new BlurTransformation(25, 3)))
                .into(background);

        tile.setText("个人中心");
        name.setText("宋亚峰");
        email.setText("397655952@qq.com");
    }
}
