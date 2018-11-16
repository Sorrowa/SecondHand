package com.example.zhangzihao.secondhand.MFragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.zhangzihao.secondhand.Adapter.MyAdapterForMainRecycleView;
import com.example.zhangzihao.secondhand.JavaBean.Book;
import com.example.zhangzihao.secondhand.R;
import com.example.zhangzihao.secondhand.View.MainActivity;

import java.util.ArrayList;

/**
 * 用作主界面显示。必须调用setInfo()
 */
public class MainFragment extends Fragment {


    private static MainActivity context;
    private static MainFragmentInterface tool;

    //界面元素
    private RecyclerView mrecyclerView;
    private EditText meditText;
    private Button button;
    private View view;
    private MyAdapterForMainRecycleView reAdapter;

    private ArrayList<Book> books = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.main_fragment_layout, container
                , false);

        this.view = view;
        mrecyclerView = view.findViewById(R.id.booklist);
        meditText = view.findViewById(R.id.search_text);
        button = view.findViewById(R.id.button_search);

        //界面初始化
        initOtherView();
        initRecycleView();

        return view;
    }

    /**
     * 初始化EditView，button等
     */
    private void initOtherView() {
        //todo:button
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                books.addAll(tool.seekForBookInfo());
                //可以在子线程中更新线程
                mrecyclerView.invalidate();
                view.postInvalidate();
            }
        });
        //todo:EditText
    }

    /**
     * 初始化RecycleView，用作显示图片列表
     */
    private void initRecycleView() {

        mrecyclerView.setLayoutManager(new LinearLayoutManager(context));
        //获取book展示列表
        books = context.getBookInfo();

        reAdapter = new MyAdapterForMainRecycleView(context, books);
        mrecyclerView.setAdapter(reAdapter);
    }


    /**
     * 设置所有的参数
     *
     * @param tool 数据获取接口
     */
    public static void setInfo(MainFragmentInterface tool, Context context) {

        MainFragment.tool = tool;
        //直接获取上下文,可以用它调用活动中的接口（注意强制类型转换）
        MainFragment.context = (MainActivity) context;
    }


    public interface MainFragmentInterface {
        /**
         * 搜索图书网络信息
         *
         * @return 返回获取（已经被处理好）的书籍信息
         */
        ArrayList<Book> seekForBookInfo();

    }
}
