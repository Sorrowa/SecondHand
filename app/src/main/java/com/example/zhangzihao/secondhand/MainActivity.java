package com.example.zhangzihao.secondhand;

import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.zhangzihao.secondhand.Base.BaseActivity;
import com.example.zhangzihao.secondhand.dyx.presenter.DealPresenter;
import com.example.zhangzihao.secondhand.dyx.view.DealFragment;
import com.example.zhangzihao.secondhand.syf.fragment.InfoFragment;
import com.example.zhangzihao.secondhand.zzh.Adapter.MainViewPagerAdapter;
import com.example.zhangzihao.secondhand.zzh.MFragment.MainFragment;
import com.example.zhangzihao.secondhand.JavaBean.Book;
import com.example.zhangzihao.secondhand.zzh.Presenter.BasePresenter;
import com.example.zhangzihao.secondhand.zzh.Presenter.MainFragmentPresenter;
import com.example.zhangzihao.secondhand.zzh.View.BaseView;

import java.util.ArrayList;

/**
 * 主活动o(￣ヘ￣o#)
 */
public class MainActivity extends BaseActivity implements MainFragment.MainFragmentInterface
        ,BaseView {


    private TabLayout mtabLayout;

    private ViewPager viewPager;
    //存储fragment信息
    private ArrayList<Fragment>fragments=new ArrayList<>();

    //第一个fragment的界面
    private MainFragmentPresenter mainFragmentPresenter;

    MainFragment mainFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getCurrentUser();

        initViewPager();

        initViewPointer();
        initTab();
        //todo:other view init

        //todo:创建BasePresenter接口
        //todo:创建数据处理类，接受以上接口
    }

    /**
     * 初始化viewPager
     */
    private void initViewPager() {
        viewPager=findViewById(R.id.viewPager);
        //todo:绑定具体的fragment到fragments中
        //主界面fragment
        MainFragment.setInfo(this,this);
        mainFragmentPresenter=new MainFragmentPresenter(this);
        mainFragment=new MainFragment();
        fragments.add(mainFragment);
        //todo:之后的碎片添加都做在这！！邓衍翔你看到了吗！！

        //个人信息界面fragment
        InfoFragment infoFragment = new InfoFragment();
        fragments.add(infoFragment);

        DealFragment dealFragment = new DealFragment();
        fragments.add(dealFragment);

        //以上
        MainViewPagerAdapter adapter=new MainViewPagerAdapter(getSupportFragmentManager()
                ,fragments);
        viewPager.setAdapter(adapter);
    }

    /**
     * 所有关于tablayout的初始化工作都在此方法中
     * 初始化点击事件
     */
    private void initTab() {

        //todo:设置viewPager和tablayout的关联
        mtabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //todo:切换viewPager
                //第二个参数为平滑滚动
                viewPager.setCurrentItem(tab.getPosition(),true);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}

            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });




        //todo:ViewPager滑动绑定tabLayout
        viewPager.addOnPageChangeListener(new
                TabLayout.TabLayoutOnPageChangeListener(mtabLayout));
    }

    /**
     * 初始化布局对象
     */
    private void initViewPointer() {
        mtabLayout=findViewById(R.id.tab_main);
    }


    /**
     * 提供给fragment获取book列表
     * @return books
     */
    public ArrayList<Book> getBookInfo(){
        //todo:presenter接口调用
        return mainFragmentPresenter.getBookInfo();
    }

    /**
     * 提供给MainFragment，用来获取搜索的book列表
     * 参数并没有加进去
     * @return 获取的图书列表
     */
    @Override
    public ArrayList<Book> seekForBookInfo(String content) {
        //todo:presenter接口调用 !!注意参数还没加呢!!
        return mainFragmentPresenter.seekForBookInfo(content);
    }

    /**
     * 获取BookList的信息
     * @param content 类型
     */
    @Override
    public void seekForBookType(String content) {

        mainFragmentPresenter.seekForBookType(content);
        return ;
    }


    /**
     * 给mainFragment设置Book
     * @param books
     */
    public void setBookList(final ArrayList<Book> books){
        //todo:
        Handler handler=new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                mainFragment.setBookList(books);
            }
        });
    }

    //忽视下面这方法
    @Override
    public void bindPresenter(BasePresenter basePresenter) { }

    @Override
    public void detachPresenter() { }
}
