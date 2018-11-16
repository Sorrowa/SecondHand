package com.example.zhangzihao.secondhand.JavaBean;

import android.graphics.drawable.Drawable;

/**
 * 书籍类别，作为之后所有书籍的参考
 */
public class Book {
    private int bookId;
    private String bookName;
    private String bookType;
    private String bookWriter;
    //存储图书
    private String bookURL;

    public Book(int bookId,String bookName,String bookType,String bookWriter,
                String bookURL){
        this.bookId=bookId;
        this.bookName=bookName;
        this.bookType=bookType;
        this.bookWriter=bookWriter;
        this.bookURL=bookURL;
    }

    public String getBookWriter() {
        return bookWriter;
    }

    public void setBookWriter(String bookWriter) {
        this.bookWriter = bookWriter;
    }

    public String getBookType() {
        return bookType;
    }

    public void setBookType(String bookType) {
        this.bookType = bookType;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookURL() {
        return bookURL;
    }

    public void setBookURL(String bookURL) {
        this.bookURL = bookURL;
    }
}
