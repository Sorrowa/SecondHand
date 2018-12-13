package com.example.zhangzihao.secondhand.zzh.Presenter;

import com.example.zhangzihao.secondhand.zzh.Model.PublishModel;
import com.example.zhangzihao.secondhand.zzh.View.PublishBookActivity;

public class PublishBookPresenter implements BasePresenter<PublishBookActivity>{


    private PublishBookActivity mview;

    private PublishModel publishModel;


    public PublishBookPresenter(PublishBookActivity mview){
        this.bindView(mview);
        publishModel=new PublishModel(this);
    }

    /**
     * 获取book信息
     */
    public void publishBook(String bookName,String bookType
            ,String introduction){

        //publishModel.publishBook()
    }

    @Override
    public void detachView() {
        mview=null;
        //释放Model资源
        publishModel.detachPresenter();
        publishModel=null;
    }

    @Override
    public void bindView(PublishBookActivity view) {
        mview=view;
    }
}
