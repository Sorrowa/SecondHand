package com.example.zhangzihao.secondhand.Base;

public class URL {

    public static String IMGS = "http://132.232.89.108:8080";//获取图片

    public static String ROOT = "http://132.232.89.108:8081";

    public static String USER = ROOT + "/user";
    public static String USER_IS_LOGIN = USER + "/is_login";
    public static String USER_LOGIN = USER + "/login";
    public static String USER_LOGOUT = USER + "/logout";
    public static String USER_SIGN = USER + "/signUp";
    public static String USER_SELECT_BY_EMAIL = USER + "/selectByEmail";
    public static String USER_MODIFY = USER + "/updateInfo";
    public static String USER_RESET = USER + "/reset";

    public static String BOOK = ROOT + "/book/";
    public static String BOOK_PUBLISH = BOOK + "/publish";//发布图书，POST方法，参数类型是"application/json"

    /**
     * select查询都是GET方法，最后形成的url形如http://132.232.89.108:8081/book/selectByEmail?email=397655952@qq.com
     */
    public static String BOOK_SELECT_BY_EMAIL = BOOK + "/selectByEmail";//参数为email
    public static String BOOK_SELECT_BY_NAME = BOOK + "/selectByName";//参数为name
    public static String BOOK_SELECT_BY_TYPE = BOOK + "/selectByType";//参数为type
    public static String BOOK_SELECT_ALL = BOOK + "/selectAll";//不需要GET参数
    public static String BOOK_SELECT_BY_ID = BOOK + "/selectWithComments"; //参数为bookId

    /**
     * 上传图片用POST方法，参数类型是form-data
     * 两个键值对"file:图片"、"bookId:图书的id"
     */
    public static String UPLOAD = ROOT + "/upload";
    public static String UPLOAD_BOOKS = UPLOAD + "/books";
    public static String UPLOAD_HEADS = UPLOAD + "/heads";

    public static String COMMENT = ROOT + "/comment/";

}
