package com.example.zhangzihao.secondhand.zzh.View;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.zhangzihao.secondhand.Base.BaseActivity;
import com.example.zhangzihao.secondhand.MainActivity;
import com.example.zhangzihao.secondhand.R;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.example.zhangzihao.secondhand.Base.URL.USER_IS_LOGIN;

public class FlashActivity extends BaseActivity implements View.OnClickListener
        {




    //用于跳转的按钮
    private Button buttonToChange;

    private Handler mhandler;

    //剩余的时间
    private int time = 3000;

    private Context context=this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash);


        isLogin(USER_IS_LOGIN);
        buttonToChange = findViewById(R.id.button);

        buttonToChange.setOnClickListener(this);

        //实现倒计时机制
        mhandler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {

                //todo:继续下一次计时
                int lastTime=msg.what;
                lastTime = lastTime - 1000;
                if (0 == lastTime) {
                    Intent intent = new Intent(context
                            ,MainActivity.class);
                    context.startActivity(intent);
                    finish();
                    return true;
                }else {
                    Message message=new Message();
                    message.what=lastTime;
                    mhandler.sendMessageDelayed(message,1000);
                    final int finalLastTime = lastTime;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            buttonToChange.setText((finalLastTime /1000)+"s后自动跳转");
                        }
                    });
                    return true;
                }
            }
        });

        //启动计时
        Message message=new Message();
        message.what=time;
        mhandler.sendMessageDelayed(message,1000);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
        }
    }

    public void isLogin(String url){
        OkHttpClient okHttpClient  = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10,TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .build();
        Request request;
        String session = getCurrentSession();
        if (session != null){
            request = new Request.Builder()
                    .url(url)
                    .addHeader("cookie",session)
                    .build();
        }else {
            request = new Request.Builder()
                    .url(url)
                    .build();
        }
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                //网络有问题的话在主活动处理，这里不管
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String data = response.body().string();
                Log.d("FlashActivity", "onResponse: "+data);
                Gson gson = new Gson();
                com.example.zhangzihao.secondhand.JavaBean.Message message = gson.fromJson(data, com.example.zhangzihao.secondhand.JavaBean.Message.class);
                if (message.getCode().equals("100")){
                    //session过期或者还未登录,pref为null表示未登录不用管
                    SharedPreferences pref = getSharedPreferences("user_data", MODE_PRIVATE );
                    if (pref != null){
                        //登录过期将本地信息洗掉
                        pref.edit().clear().commit();
                        }
                }
            }
        });
    }
}
