package com.example.zhangzihao.secondhand.JavaBean;

import android.os.Parcel;
import android.os.Parcelable;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class Book implements Parcelable {
    private Integer bookId;
    private String email;//拥有者邮箱
    private String name;//图书名字
    private String type;//图书类型
    private int state;//图书状态
    private String introduction;//简介
    private Date date;//发布日期
    private String imgPath;
    private String imgPath2;
    private String imgPath3;
    private int good;//喜欢收藏数
    private int bad;//不喜欢数
    private String changer;

    protected Book(Parcel in) {
        if (in.readByte() == 0) {
            bookId = null;
        } else {
            bookId = in.readInt();
        }
        email = in.readString();
        name = in.readString();
        type = in.readString();
        state = in.readInt();
        introduction = in.readString();
        imgPath = in.readString();
        imgPath2 = in.readString();
        imgPath3 = in.readString();
        good = in.readInt();
        bad = in.readInt();
        changer = in.readString();
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    public String getChanger() {
        return changer;
    }

    public void setChanger(String changer) {
        this.changer = changer;
    }

    //    private Timestamp udDate;//修改时间
    private List<Comment> comments;


    public List<Comment> getComments() {

        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private User user;

    public Book() {}


    public Integer getBookId() {
        return bookId;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getState() {
        return state;
    }

    public String getIntroduction() {
        return introduction;
    }

    public Date getDate() {
        return date;
    }

    public String getImgPath() {
        return imgPath;
    }

    public String getImgPath2() {
        return imgPath2;
    }

    public String getImgPath3() {
        return imgPath3;
    }

    public int getGood() {
        return good;
    }

    public int getBad() {
        return bad;
    }

    //public Timestamp getUdDate() {
//        return udDate;
//    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setState(int state) {
        this.state = state;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public void setImgPath3(String imgPath3) {
        this.imgPath3 = imgPath3;
    }

    public void setGood(int good) {
        this.good = good;
    }

    public void setBad(int bad) {
        this.bad = bad;
    }

//    public void setUdDate(Timestamp udDate) {
//        this.udDate = udDate;
//    }

    public void setImgPath2(String imgPath2) {
        this.imgPath2 = imgPath2;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (bookId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(bookId);
        }
        dest.writeString(email);
        dest.writeString(name);
        dest.writeString(type);
        dest.writeInt(state);
        dest.writeString(introduction);
        dest.writeString(imgPath);
        dest.writeString(imgPath2);
        dest.writeString(imgPath3);
        dest.writeInt(good);
        dest.writeInt(bad);
        dest.writeString(changer);
    }
}

