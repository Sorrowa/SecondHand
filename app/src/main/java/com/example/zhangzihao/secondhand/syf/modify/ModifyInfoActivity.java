package com.example.zhangzihao.secondhand.syf.modify;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.ContentUris;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.signature.ObjectKey;
import com.example.zhangzihao.secondhand.JavaBean.User;
import com.example.zhangzihao.secondhand.R;
import com.example.zhangzihao.secondhand.syf.base.UserActivity;
import com.example.zhangzihao.secondhand.syf.presenter.ModifyPresenter;
import com.example.zhangzihao.secondhand.syf.view.ModifyView;

import java.io.File;
import static com.example.zhangzihao.secondhand.Base.URL.IMGS;


public class ModifyInfoActivity extends UserActivity implements ModifyView ,View.OnClickListener {
    private static final String TAG = "ModifyInfoActivity";
    public static final int CHOOSE_PHOTO=1;
    MyHandler myHandler;
    LinearLayout logout,modify_name,modify_signature;
    RatingBar ratingBar;
    RelativeLayout back;
    ImageView head;
    TextView change_head,name,signature;
    ModifyPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_info);
        presenter = new ModifyPresenter();
        presenter.attachView(this);
        myHandler = new MyHandler();
        initView();
    }

    @Override
    protected void onResume() {
        String email = getCurrentUser();
        String session = getCurrentSession();
        if (email != null){
            presenter.getUser(email,session);
        }
        super.onResume();
    }

    private void initView() {
        back = findViewById(R.id.modify_toolbar_back);
        logout = findViewById(R.id.logout);
        modify_name = findViewById(R.id.modify_name);
        modify_signature = findViewById(R.id.modify_signature);
        change_head = findViewById(R.id.change_head);
        name = findViewById(R.id.modify_tv_name);
        signature = findViewById(R.id.modify_tv_signature);
        ratingBar = findViewById(R.id.rating_bar);
        head = findViewById(R.id.modify_head);

        back.setOnClickListener(this);
        logout.setOnClickListener(this);
        change_head.setOnClickListener(this);
        modify_name.setOnClickListener(this);
        modify_signature.setOnClickListener(this);

    }

    private void initUI(User user){
        RequestOptions options = new RequestOptions()
                .placeholder(R.mipmap.waiting)
                .error(R.mipmap.default_head)
                .signature(new ObjectKey(System.currentTimeMillis()))
                .diskCacheStrategy(DiskCacheStrategy.NONE);
        Glide.with(this)
                .load(IMGS + user.getHeadPath())
                .apply(options)
                .into(head);
        name.setText(user.getName());
        signature.setText(user.getSignature());
        ratingBar.setRating(user.getGrade());
    }

    @Override
    public void modifyUI(User user) {
        Message msg = new Message();
        Bundle bundle = new Bundle();
        bundle.putSerializable("user",user);
        msg.setData(bundle);
        myHandler.sendMessage(msg);
    }

    @Override
    public void showResult(com.example.zhangzihao.secondhand.JavaBean.Message msg) {
        Looper.prepare();
        showToast(msg.getMsg());
        presenter.getUser(getCurrentUser(),getCurrentSession());
        Looper.loop();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.logout:
                SharedPreferences pref_clean = getSharedPreferences("user_data", MODE_PRIVATE );
                pref_clean.edit().clear().commit();
                finish();
                break;
            case R.id.modify_toolbar_back:
                finish();
                break;
            case R.id.change_head:
                if (ContextCompat.checkSelfPermission(ModifyInfoActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(ModifyInfoActivity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
                }else{
                    openAlbum();
                }
                break;
            case R.id.modify_name:
                modifyItem("昵称",1);
                break;
            case R.id.modify_signature:
                modifyItem("签名",2);
                break;
            default:
                break;
        }
    }

    private void modifyItem(final String name, final int choice){
        AlertDialog.Builder builder = new AlertDialog.Builder(ModifyInfoActivity.this);
        builder.setTitle("请输入新的"+name);    //设置对话框标题
        final EditText edit = new EditText(ModifyInfoActivity.this);
        builder.setView(edit);
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (edit.getText().toString().equals("")){
                    showToast("内容不能为空！");
                }else{
                    User user = new User();
                    user.setEmail(getCurrentUser());
                    if (choice == 1){
                        user.setName(edit.getText().toString());
                        Log.d(TAG, "onClick: "+user.getName());
                    }else if(choice == 2){
                        user.setSignature(edit.getText().toString());
                        Log.d(TAG, "onClick: "+user.getSignature());
                    }
                    presenter.commitModify(user,getCurrentSession());
                }
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                showToast("您取消了修改！");
            }
        });
        builder.setCancelable(true);    //设置按钮是否可以按返回键取消,false则不可以取消
        AlertDialog dialog = builder.create();  //创建对话框
        dialog.setCanceledOnTouchOutside(true); //设置弹出框失去焦点是否隐藏,即点击屏蔽其它地方是否隐藏
        dialog.show();
    }

    private void openAlbum() {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent,CHOOSE_PHOTO);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    openAlbum();
                }else{
                    Toast toast = Toast.makeText(ModifyInfoActivity.this, "您拒绝了访问权限！", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case CHOOSE_PHOTO:
                handleImage(data);
                break;
            default:
                break;
        }
    }

    @TargetApi(19)//4.4以上才能调用，由于4.4以下的机器很少所以没有考虑进来
    private void handleImage(Intent data) {
        String imagePath = null;
        if (data==null){
            return;//若用户未从相册中选图片则什么都不做，否则上传头像并保存到后台
        }else {
            Uri uri = data.getData();
            Glide.with(this)
                    .load(uri)
                    .into(head);
            if (DocumentsContract.isDocumentUri(this, uri)) {
                String docId = DocumentsContract.getDocumentId(uri);
                if ("com.android.providers.media.documents".equals(uri.getAuthority())) {
                    String id = docId.split(":")[1];
                    String selection = MediaStore.Images.Media._ID + "=" + id;
                    imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
                } else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
                    Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(docId));
                    imagePath = getImagePath(contentUri, null);
                }
            } else if ("content".equalsIgnoreCase(uri.getScheme())) {
                imagePath = getImagePath(uri, null);
            } else if ("file".equalsIgnoreCase(uri.getScheme())) {
                imagePath = uri.getPath();
            }
            //得到图片本地路径后，先删除原有头像，再上传
            File file = new File(imagePath);
            presenter.change_head(file,getCurrentUser(),getCurrentSession());
        }
    }

    private String getImagePath(Uri uri,String selection){
        String path=null;
        Cursor cursor=getContentResolver().query(uri,null,selection,null,null);
        if(cursor!=null){
            if(cursor.moveToFirst()){
                path=cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }

    class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg){
            Bundle bundle = msg.getData();
            User user = (User) bundle.getSerializable("user");
            initUI(user);
        }
    }


}
