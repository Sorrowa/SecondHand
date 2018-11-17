package com.example.zhangzihao.secondhand.syf.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ActionMenuView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.zhangzihao.secondhand.Base.BaseActivity;
import com.example.zhangzihao.secondhand.R;
import com.example.zhangzihao.secondhand.syf.login.LoginActivity;
import com.example.zhangzihao.secondhand.syf.modify.ModifyInfoActivity;
import com.example.zhangzihao.secondhand.syf.view.LoginView;

import jp.wasabeef.glide.transformations.BlurTransformation;

import static com.example.zhangzihao.secondhand.Base.URL.IMGS;


public class InfoFragment extends Fragment {

    private View view;
    private ImageView head,background;
    TextView tv_tile,tv_name,tv_email;
    Toolbar toolbar;
    private ActionMenuView mAcitionMenuView;
    ImageView settings;
    Button goto_login;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.info_fragment_layout,container,false);
        this.view = view;
        //initView();
        return view;
    }

    public void initView(){
        RequestOptions options = new RequestOptions()
                .placeholder(R.mipmap.waiting)
                .error(R.mipmap.default_head)
                .diskCacheStrategy(DiskCacheStrategy.NONE);

        toolbar = view.findViewById(R.id.info_toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        settings = view.findViewById(R.id.info_settings);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),ModifyInfoActivity.class));
            }
        });
        head = (ImageView) view.findViewById(R.id.info_head);
        background = view.findViewById(R.id.info_background);
        goto_login = view.findViewById(R.id.goto_login);
        tv_tile = view.findViewById(R.id.info_tile);
        tv_name = view.findViewById(R.id.info_name);
        tv_email = view.findViewById(R.id.info_email);



        Glide.with(this)
                .load(R.drawable.info_background)
                .apply(options.bitmapTransform(new BlurTransformation(20, 3)))
                .into(background);

        String email = ((BaseActivity) getActivity()).getCurrentUser();
        if (email == null){
            head.setVisibility(View.GONE);
            settings.setVisibility(View.GONE);
            tv_tile.setText("个人中心");
            tv_name.setText("");
            tv_email.setText("");
            goto_login.setVisibility(View.VISIBLE);
            goto_login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(getActivity(),LoginActivity.class));
                }
            });
        }else {
            head.setVisibility(View.VISIBLE);
            settings.setVisibility(View.VISIBLE);
            Glide.with(this)
                    .load(IMGS + "/imgs/1.png")
                    .apply(options)
                    .into(head);
            goto_login.setVisibility(View.GONE);
            tv_tile.setText("个人中心");
            tv_name.setText("宋亚峰");
            tv_email.setText("397655952@qq.com");
        }
    }



    @Override
    public void onResume() {
        super.onResume();
        initView();
    }
}
