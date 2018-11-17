package com.example.zhangzihao.secondhand.JavaBean;

/**
 * 后台的回馈消息
 * code是消息的代码
 * msg是消息的具体内容
 * 在编码阶段文档里可以找到对应关系
 */

public class Message {
    private String code;
    private String msg;

    public Message(String code,String msg){
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
