package com.example.zhangzihao.secondhand.JavaBean;

public class User {
    private Integer userId;
    private String name;
    private String email;
    private String pwd;
    private String headPath;
    private Integer state;
    private float grade;
    private String signature;
    private String sex;

    public Integer getId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPwd() {
        return pwd;
    }

    public String getPath() {
        return headPath;
    }

    public Integer getState() {
        return state;
    }

    public float getGrade() {
        return grade;
    }

    public String getSignature() {
        return signature;
    }

    public String getSex() {
        return sex;
    }

    public void setId(Integer id) {
        this.userId = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public void setPath(String path) {
        this.headPath = path;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public void setGrade(float grade) {
        this.grade = grade;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
