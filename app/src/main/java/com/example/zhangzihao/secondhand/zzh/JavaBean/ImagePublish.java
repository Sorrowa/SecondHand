package com.example.zhangzihao.secondhand.zzh.JavaBean;

import java.io.File;

public class ImagePublish {
    private String bookID;
    private File file;

    public ImagePublish(String bookID,File file){
        this.bookID=bookID;
        this.file=file;
    }

    public String getBookID() {
        return bookID;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
}
