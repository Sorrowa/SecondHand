package com.example.zhangzihao.secondhand.syf.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.zhangzihao.secondhand.Base.BaseActivity;
import com.example.zhangzihao.secondhand.JavaBean.User;
import com.example.zhangzihao.secondhand.R;
import com.example.zhangzihao.secondhand.syf.base.UserFragment;
import com.example.zhangzihao.secondhand.syf.login.LoginActivity;
import com.example.zhangzihao.secondhand.syf.modify.ModifyInfoActivity;
import com.example.zhangzihao.secondhand.syf.presenter.InfoPresenter;
import com.example.zhangzihao.secondhand.syf.view.InfoView;

import jp.wasabeef.glide.transformations.BlurTransformation;

import static com.example.zhangzihao.secondhand.Base.URL.IMGS;


public class InfoFragment extends UserFragment implements InfoView {

    private InfoPresenter presenter;
    private View view;
    private ImageView head,background;
    private MyHandler myHandler;
    TextView tv_tile,tv_name,tv_email;
    Toolbar toolbar;
    ImageView settings;
    Button goto_login;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new InfoPresenter();
        presenter.attachView(this);
        myHandler = new MyHandler();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.info_fragment_layout,container,false);
        this.view = view;
        initView();
        return view;
    }

    @Override
    public void onResume() {
        String email = ((BaseActivity) getActivity()).getCurrentUser();
        if (email != null){
            presenter.getData(email);
        }else{
            initUI();
        }
        super.onResume();
    }

    public void initView(){
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
    }

    public void initUI(){
        RequestOptions options = new RequestOptions()
                .placeholder(R.mipmap.waiting)
                .error(R.mipmap.default_head)
                .diskCacheStrategy(DiskCacheStrategy.NONE);

        Glide.with(this)
                .load(R.drawable.info_background)
                .apply(options.bitmapTransform(new BlurTransformation(20, 3)))
                .into(background);

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
    }
    public void initUI(User user){
        RequestOptions options = new RequestOptions()
                .placeholder(R.mipmap.waiting)
                .error(R.mipmap.default_head)
                .diskCacheStrategy(DiskCacheStrategy.NONE);
        head.setVisibility(View.VISIBLE);
        settings.setVisibility(View.VISIBLE);
        Glide.with(this)
                .load(IMGS + user.getHeadPath())
                .apply(options)
                .into(head);
        goto_login.setVisibility(View.GONE);
        tv_name.setText(user.getName());
        tv_email.setText(user.getEmail());
    }


    @Override
    public void initInfo(User user) {
        Message msg = new Message();
        Bundle bundle = new Bundle();
        bundle.putSerializable("user",user);
        msg.setData(bundle);
        myHandler.sendMessage(msg);
    }

    class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg){
            Bundle bundle = msg.getData();
            User user = (User) bundle.getSerializable("user");
            initUI(user);
        }
    }
}
