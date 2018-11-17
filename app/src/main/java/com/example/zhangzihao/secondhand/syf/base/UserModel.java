package com.example.zhangzihao.secondhand.syf.base;


import com.example.zhangzihao.secondhand.JavaBean.Message;
import com.example.zhangzihao.secondhand.JavaBean.User;
import com.google.gson.Gson;

public abstract class UserModel<T> {
    protected String[] mParams;
    /**
     *设置数据请求参数
     * @param args 参数数组
     */
    public UserModel params(String... args){
        mParams = args;
        return this;
    }

    public abstract void execute(UserCallback<T> callback);
//    public void requestGetAPI(String url,UserCallback<T> callback){}
//    public void requestPostAPI(String url,UserCallback<T> callback){}

    protected Message parseLogin(String jsonData){
        Gson gson = new Gson();
        Message msg = gson.fromJson(jsonData,Message.class);
        return msg;
    }
}
