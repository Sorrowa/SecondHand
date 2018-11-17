package com.example.zhangzihao.secondhand.zzh.View;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.zhangzihao.secondhand.R;

public class FlashActivity extends AppCompatActivity implements View.OnClickListener
        ,BaseView{




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
}
