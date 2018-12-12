package com.example.zhangzihao.secondhand.zzh.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.zhangzihao.secondhand.R;
import com.example.zhangzihao.secondhand.zzh.Presenter.PublishBookPresenter;

import java.util.ArrayList;
import java.util.Objects;

public class PublishBookActivity extends AppCompatActivity
        implements BaseView<PublishBookPresenter>{

    private PublishBookPresenter presenter;

    private Toolbar toolbar;

    private EditText name;

    private Spinner type;

    private EditText introduction;

    private ArrayList<String> bookTypes=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish_book);



        initeView();

        initeSpinner();

        initeToolbar();
    }

    /**
     * 初始化toolbar
     */
    private void initeToolbar() {
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



    }

    /**
     * 初始化Spinner
     */
    private void initeSpinner() {
        initeTypes();

        ArrayAdapter<String> stringArrayAdapter=new ArrayAdapter<>(this
                ,R.layout.item_selected,bookTypes);

        stringArrayAdapter.setDropDownViewResource(R.layout.item_drop);

        type.setAdapter(stringArrayAdapter);
    }

    private void initeTypes(){
        bookTypes.add("小说");
        bookTypes.add("心理");
        bookTypes.add("艺术");
        bookTypes.add("地理");
        bookTypes.add("体育");
        bookTypes.add("管理");
        bookTypes.add("经济");
        bookTypes.add("历史");
        bookTypes.add("计算机");
    }

    /**
     * 初始化布局元素
     */
    private void initeView() {
        toolbar=findViewById(R.id.toolbar);
        name=findViewById(R.id.book_name);
        type=findViewById(R.id.book_type);
        introduction=findViewById(R.id.book_introduction);
    }

    @Override
    public void bindPresenter(PublishBookPresenter publishBookPresenter) {
        presenter=publishBookPresenter;
    }

    @Override
    public void detachPresenter() {
        presenter=null;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        detachPresenter();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}
