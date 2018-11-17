package com.example.zhangzihao.secondhand.syf.base;

import com.example.zhangzihao.secondhand.JavaBean.User;

public class DataModel {
    public static UserModel request(Class clazz) {

        UserModel model = null;

        try {
            model = (UserModel) clazz.newInstance();//映射
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return model;
    }
}
