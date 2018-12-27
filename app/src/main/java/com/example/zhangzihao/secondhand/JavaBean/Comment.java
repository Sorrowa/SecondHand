package com.example.zhangzihao.secondhand.JavaBean;

/*
    create by:loser
    date:2018/12/26 21:34
*/

import java.util.Date;

public class Comment {
    private int commentId;
    private String writerEmail;
    private Integer bookId;
    private String content;
    private Date date;
    private int good;
    private int bad;
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getCommentId() {
        return commentId;
    }

    public String getWriterEmail() {
        return writerEmail;
    }



    public String getContent() {
        return content;
    }

    public Date getDate() {
        return date;
    }

    public int getGood() {
        return good;
    }

    public int getBad() {
        return bad;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public void setWriterEmail(String writerEmail) {
        this.writerEmail = writerEmail;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setGood(int good) {
        this.good = good;
    }

    public void setBad(int bad) {
        this.bad = bad;
    }
}
