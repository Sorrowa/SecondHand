package com.example.zhangzihao.secondhand.JavaBean;

/*
    create by:loser
    date:2018/12/29 3:03
*/

public class DealBean {

    /**
     * bookId : 54
     * email : 397655952@qq.com
     * name : 再来一次
     * type : 小说
     * state : 2
     * introduction :
     * imgPath : /imgs/books/54/head_image
     * imgPath2 : null
     * imgPath3 : null
     * good : 0
     * bad : 0
     * changer : 397655952@qq.com
     * changeId : 53
     * changeBook : {"bookId":53,"email":"397655952@qq.com","name":"不行就用OK","type":"小说","state":2,"introduction":"","imgPath":"/imgs/books/default.jpg","imgPath2":null,"imgPath3":null,"good":0,"bad":0,"changer":"397655952@qq.com","changeId":null,"changeBook":null,"comments":null}
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
    private int changeId;
    private ChangeBookBean changeBook;
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

    public int getChangeId() {
        return changeId;
    }

    public void setChangeId(int changeId) {
        this.changeId = changeId;
    }

    public ChangeBookBean getChangeBook() {
        return changeBook;
    }

    public void setChangeBook(ChangeBookBean changeBook) {
        this.changeBook = changeBook;
    }

    public Object getComments() {
        return comments;
    }

    public void setComments(Object comments) {
        this.comments = comments;
    }

    public static class ChangeBookBean {
        /**
         * bookId : 53
         * email : 397655952@qq.com
         * name : 不行就用OK
         * type : 小说
         * state : 2
         * introduction :
         * imgPath : /imgs/books/default.jpg
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
