package com.example.zhangzihao.secondhand.zzh.MFragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.example.zhangzihao.secondhand.zzh.Adapter.MyAdapterForMainRecycleView;
import com.example.zhangzihao.secondhand.JavaBean.Book;
import com.example.zhangzihao.secondhand.R;
import com.example.zhangzihao.secondhand.MainActivity;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomButtons.SimpleCircleButton;
import com.nightonke.boommenu.BoomMenuButton;

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
    private BoomMenuButton typeButton;
    private MyAdapterForMainRecycleView reAdapter;

    private ArrayList<Book> books = new ArrayList<>();

    private ArrayList<String> bookTypes=new ArrayList<>();
    private ArrayList<Integer> bookImage=new ArrayList<>();


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

        initTypeButton();


        return view;
    }

    /**
     * 初始化类型选择按钮
     */
    private void initTypeButton() {
        initBookType();

        typeButton=view.findViewById(R.id.typeButton);

        for (int i=0;i<typeButton.getPiecePlaceEnum().pieceNumber();i++){
            final int a=i;
            SimpleCircleButton.Builder builder=new SimpleCircleButton.Builder()
                    .listener(new OnBMClickListener() {
                        @Override
                        public void onBoomButtonClick(int index) {
                            //todo:获取当前类型的图书信息
                            context.seekForBookType(bookTypes.get(a));
                        }
                    })
                    .normalImageRes(bookImage.get(a));
            typeButton.addBuilder(builder);
        }

    }

    /**
     * 初始化book类型list
     */
    private void initBookType() {
        bookTypes.add("小说");
        bookImage.add(R.mipmap.xiaoshuo);
        bookTypes.add("心理");
        bookImage.add(R.mipmap.heart);
        bookTypes.add("艺术");
        bookImage.add(R.mipmap.art);
        bookTypes.add("地理");
        bookImage.add(R.mipmap.location);
        bookTypes.add("体育");
        bookImage.add(R.mipmap.tiyu);
        bookTypes.add("管理");
        bookImage.add(R.mipmap.manage);
        bookTypes.add("经济");
        bookImage.add(R.mipmap.money);
        bookTypes.add("历史");
        bookImage.add(R.mipmap.history);
        bookTypes.add("计算机");
        bookImage.add(R.mipmap.computer);
    }

    /**
     * 初始化EditView，button等
     */
    @SuppressLint("ClickableViewAccessibility")
    private void initOtherView() {
        //todo:button


        button.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        String content=getEdit();
                        tool.seekForBookInfo(content);
                        InputMethodManager imm = (InputMethodManager) context
                                .getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(context
                                        .getWindow()
                                        .getDecorView()
                                        .getWindowToken(),
                                0);
                        meditText.clearFocus();
                        //todo:设置button图片
                        break;
                    case MotionEvent.ACTION_UP:
                        //todo:重新设置button图片
                        break;
                }
                return true;
            }
        });
        //todo:EditText
//        meditText.
    }

    @Override
    public void onResume() {
        super.onResume();
        InputMethodManager imm = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(context
                        .getWindow()
                        .getDecorView()
                        .getWindowToken(),
                0);
        //meditText.clearFocus();
    }

    /**
     * 获取EditText的输入内容，(并对其进行类型判断)
     * @return 处理完成的结果
     */
    private String getEdit(){
        String content=meditText.getText().toString();
        return content;
    }

    /**
     * 初始化RecycleView，用作显示图片列表
     */
    private void initRecycleView() {

//        mrecyclerView.setLayoutManager(new LinearLayoutManager(context));
        //获取book展示列表
        mrecyclerView.setLayoutManager(new GridLayoutManager(context,2));
        books = context.getBookInfo();
        //Log.d("zzh","book ="+books.get(1));
        reAdapter = new MyAdapterForMainRecycleView(context, books);
        mrecyclerView.setAdapter(reAdapter);
        reAdapter.notifyDataSetChanged();
    }


    /**
     * 设置所有的参数
     * @param tool 数据获取接口
     */
    public static void setInfo(MainFragmentInterface tool, Context context) {

        MainFragment.tool = tool;
        //直接获取上下文,可以用它调用活动中的接口（注意强制类型转换）
        MainFragment.context = (MainActivity) context;
    }

    /**
     * 更新book显示
     * @param books
     */
    public void setBookList(ArrayList<Book> books){
        if (null==books){
            this.books.clear();
        }else {
            this.books.clear();
            this.books.addAll(books);
        }
        reAdapter.notifyDataSetChanged();
//        view.postInvalidate();
    }

    public interface MainFragmentInterface {
        /**
         * 搜索图书网络信息
         * @return 返回获取（已经被处理好）的书籍信息
         */
        ArrayList<Book> seekForBookInfo(String content);

        void seekForBookType(String content);

    }
}
