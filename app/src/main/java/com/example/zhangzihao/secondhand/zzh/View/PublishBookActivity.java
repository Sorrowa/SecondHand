package com.example.zhangzihao.secondhand.zzh.View;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.zhangzihao.secondhand.Base.BaseActivity;
import com.example.zhangzihao.secondhand.R;
import com.example.zhangzihao.secondhand.zzh.Presenter.PublishBookPresenter;

import java.util.ArrayList;
import java.util.Objects;

public class PublishBookActivity extends BaseActivity
        implements BaseView<PublishBookPresenter> {

    private PublishBookPresenter presenter;

    private Toolbar toolbar;

    private EditText name;

    private Spinner type;

    private EditText introduction;

    private ArrayList<String> bookTypes = new ArrayList<>();

    private Button send;

    private Button sendImage;

    //记录当前的bookType
    private String bookType="小说";

    //存储图片的url
    private Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish_book);

        getRight();


        presenter=new PublishBookPresenter(this);

        initeView();

        initeSpinner();

        initeToolbar();

        initeEdit();

        initeButton();
    }

    /**
     * 获取文件读取权限
     */
    private void getRight() {
        //检查权限（NEED_PERMISSION）是否被授权 PackageManager.PERMISSION_GRANTED表示同意授权
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            //用户已经拒绝过一次，再次弹出权限申请对话框需要给用户一个解释
            if (ActivityCompat
                    .shouldShowRequestPermissionRationale(this, Manifest.permission
                    .WRITE_EXTERNAL_STORAGE)) {
                Toast.makeText(this, "请开通相关权限，否则无法正常使用本应用！", Toast.LENGTH_SHORT).show();
            }
            //申请权限
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}
                    ,1);

        } else {
            Toast.makeText(this, "授权成功！", Toast.LENGTH_SHORT).show();
            Log.e("zzh", "checkPermission: 已经授权！");
        }
    }

    /**
     * 提交按钮
     */
    private void initeButton() {

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showPublishDialog();
            }
        });

        //todo:设置上传图片的button

        sendImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, 111);
            }
        });
    }

    /**
     * 显示一个提示框
     * 确认是否上传
     */
    private void showPublishDialog() {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("提示");
        builder.setMessage("您确认提交吗？");
        builder.setCancelable(false);
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String bookName=name.getText().toString();
                String bookIntroduction=introduction.getText().toString();
                if (bookName.equals("")){
                    showToast("书名不能为空!!");
                    return;
                }
                presenter.publishBook(bookName,bookType,bookIntroduction,getCurrentUser()
                        ,imageUri);
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {}
        });
        builder.show();
    }

    /**
     * 初始化editText
     */
    private void initeEdit() {
        //todo:字符串识别等，如果以后有机会就做
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

        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<>(this
                , R.layout.item_selected, bookTypes);

        stringArrayAdapter.setDropDownViewResource(R.layout.item_drop);
        type.setAdapter(stringArrayAdapter);
        type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view
                    , int position, long id) {
                bookType=bookTypes.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    private void initeTypes() {
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
        toolbar = findViewById(R.id.toolbar);
        name = findViewById(R.id.book_name);
        type = findViewById(R.id.book_type);
        introduction = findViewById(R.id.book_introduction);
        send=findViewById(R.id.send_book);
        sendImage=findViewById(R.id.publish_image);
    }

    @Override
    public void bindPresenter(PublishBookPresenter publishBookPresenter) {
        presenter = publishBookPresenter;
    }

    @Override
    public void detachPresenter() {
        presenter = null;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
        detachPresenter();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

    /**
     * 显示Toast
     * @param text
     */
    public void showToast(String text){
        Toast.makeText(this,text,Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_CANCELED) {
            showToast("取消了选择图片");
            return;
        }

        try {
            imageUri = data.getData();
            Log.e("TAG", imageUri.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
