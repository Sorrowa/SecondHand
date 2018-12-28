package com.example.zhangzihao.secondhand.JavaBean;

/*
    create by:loser
    date:2018/12/29 3:21
*/

public class MessageBean {

    /**
     * commentId : 2
     * writerEmail : 397655952@qq.com
     * bookId : 1
     * content : 好啊好啊！
     * date : 2018-11-21 12:21:22
     * good : 0
     * bad : 0
     * user : null
     * book : {"bookId":1,"email":"397655952@qq.com","name":"三国志","type":"历史","state":3,"introduction":"这家伙很懒什么都没有说","imgPath":"/imgs/books/1/a1c369817a5ed62b27e756378abe3124.jpg","imgPath2":null,"imgPath3":null,"good":0,"bad":0,"changer":"397655952@qq.com","changeId":null,"changeBook":null,"comments":null}
     */

    private int commentId;
    private String writerEmail;
    private int bookId;
    private String content;
    private String date;
    private int good;
    private int bad;
    private Object user;
    private BookBean book;

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public String getWriterEmail() {
        return writerEmail;
    }

    public void setWriterEmail(String writerEmail) {
        this.writerEmail = writerEmail;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getGood() {
        return good;
    }

    public void setGood(int good) {
        this.good = good;
    }

    public int getBad() {
        return bad;
    }

    public void setBad(int bad) {
        this.bad = bad;
    }

    public Object getUser() {
        return user;
    }

    public void setUser(Object user) {
        this.user = user;
    }

    public BookBean getBook() {
        return book;
    }

    public void setBook(BookBean book) {
        this.book = book;
    }

    public static class BookBean {
        /**
         * bookId : 1
         * email : 397655952@qq.com
         * name : 三国志
         * type : 历史
         * state : 3
         * introduction : 这家伙很懒什么都没有说
         * imgPath : /imgs/books/1/a1c369817a5ed62b27e756378abe3124.jpg
         * imgPath2 : null
         * imgPath3 : null
         * good : 0
         * bad : 0
         * changer : 397655952@qq.com
         * changeId : null
         * changeBook : null
         * comments : null
         */

        private int bookId;
        private String email;
        private String name;
        private String type;
        private int state;
        private String introduction;
        private String imgPath;
        private Object imgPath2;
        private Object imgPath3;
        private int good;
        private int bad;
        private String changer;
        private Object changeId;
        private Object changeBook;
        private Object comments;

        public int getBookId() {
            return bookId;
        }

        public void setBookId(int bookId) {
            this.bookId = bookId;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public String getIntroduction() {
            return introduction;
        }

        public void setIntroduction(String introduction) {
            this.introduction = introduction;
        }

        public String getImgPath() {
            return imgPath;
        }

        public void setImgPath(String imgPath) {
            this.imgPath = imgPath;
        }

        public Object getImgPath2() {
            return imgPath2;
        }

        public void setImgPath2(Object imgPath2) {
            this.imgPath2 = imgPath2;
        }

        public Object getImgPath3() {
            return imgPath3;
        }

        public void setImgPath3(Object imgPath3) {
            this.imgPath3 = imgPath3;
        }

        public int getGood() {
            return good;
        }

        public void setGood(int good) {
            this.good = good;
        }

        public int getBad() {
            return bad;
        }

        public void setBad(int bad) {
            this.bad = bad;
        }

        public String getChanger() {
            return changer;
        }

        public void setChanger(String changer) {
            this.changer = changer;
        }

        public Object getChangeId() {
            return changeId;
        }

        public void setChangeId(Object changeId) {
            this.changeId = changeId;
        }

        public Object getChangeBook() {
            return changeBook;
        }

        public void setChangeBook(Object changeBook) {
            this.changeBook = changeBook;
        }

        public Object getComments() {
            return comments;
        }

        public void setComments(Object comments) {
            this.comments = comments;
        }
    }
}
