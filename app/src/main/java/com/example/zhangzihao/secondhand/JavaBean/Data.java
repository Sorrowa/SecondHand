package com.example.zhangzihao.secondhand.JavaBean;

/*
    create by:loser
    date:2018/12/27 19:45
*/

import java.util.List;

public class Data {

    /**
     * bookId : 4
     * email : 397655952@qq.com
     * name : 了不起的盖茨比
     * type : 文学/美国
     * state : 1
     * introduction : 这家伙很懒什么都没有说
     * imgPath : /imgs/books/4/5.png
     * imgPath2 : null
     * imgPath3 : null
     * good : 0
     * bad : 0
     * changer : null
     * comments : [{"commentId":13,"writerEmail":"1198375223@qq.com","bookId":4,"content":"测试","date":"2018-12-28 10:00:27","good":0,"bad":0,"user":{"userId":null,"name":"admin","email":null,"pwd":null,"headPath":"/imgs/heads/default.png","state":null,"grade":0,"signature":null,"sex":null}}]
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
    private Object changer;
    private List<CommentsBean> comments;

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

    public Object getChanger() {
        return changer;
    }

    public void setChanger(Object changer) {
        this.changer = changer;
    }

    public List<CommentsBean> getComments() {
        return comments;
    }

    public void setComments(List<CommentsBean> comments) {
        this.comments = comments;
    }

    public static class CommentsBean {
        /**
         * commentId : 13
         * writerEmail : 1198375223@qq.com
         * bookId : 4
         * content : 测试
         * date : 2018-12-28 10:00:27
         * good : 0
         * bad : 0
         * user : {"userId":null,"name":"admin","email":null,"pwd":null,"headPath":"/imgs/heads/default.png","state":null,"grade":0,"signature":null,"sex":null}
         */

        private int commentId;
        private String writerEmail;
        private int bookId;
        private String content;
        private String date;
        private int good;
        private int bad;
        private UserBean user;

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

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public static class UserBean {
            /**
             * userId : null
             * name : admin
             * email : null
             * pwd : null
             * headPath : /imgs/heads/default.png
             * state : null
             * grade : 0.0
             * signature : null
             * sex : null
             */

            private Object userId;
            private String name;
            private Object email;
            private Object pwd;
            private String headPath;
            private Object state;
            private double grade;
            private Object signature;
            private Object sex;

            public Object getUserId() {
                return userId;
            }

            public void setUserId(Object userId) {
                this.userId = userId;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public Object getEmail() {
                return email;
            }

            public void setEmail(Object email) {
                this.email = email;
            }

            public Object getPwd() {
                return pwd;
            }

            public void setPwd(Object pwd) {
                this.pwd = pwd;
            }

            public String getHeadPath() {
                return headPath;
            }

            public void setHeadPath(String headPath) {
                this.headPath = headPath;
            }

            public Object getState() {
                return state;
            }

            public void setState(Object state) {
                this.state = state;
            }

            public double getGrade() {
                return grade;
            }

            public void setGrade(double grade) {
                this.grade = grade;
            }

            public Object getSignature() {
                return signature;
            }

            public void setSignature(Object signature) {
                this.signature = signature;
            }

            public Object getSex() {
                return sex;
            }

            public void setSex(Object sex) {
                this.sex = sex;
            }
        }
    }
}
