package com.example.zhangzihao.secondhand.zzh.Model;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;

public class Tool {
    /**
     * 将图片的uri转发为ImagePath
     * @param uri 图书uri
     * @return ImagePath
     */
    public static String doForImageUrl(Uri uri, Context context){
        String imagePath=null;
        if(DocumentsContract.isDocumentUri(context, uri)){
            //如果是document类型的Uri,则通过document id处理
            String docId = DocumentsContract.getDocumentId(uri);
            if("com.android.providers.media.document".equals(uri.getAuthority())){
                String id = docId.split(":")[1];//解析出数字格式的id
                String selection = MediaStore.Images.Media._ID + "=" + id;
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        selection,context);
                return imagePath;
            }else if("com.android.providers.downloads.documents".equals
                    (uri.getAuthority())){
                Uri contentUri = ContentUris.withAppendedId(Uri.parse(
                        "content://downloads/public_downloads"), Long.valueOf(docId));
                imagePath = getImagePath(contentUri,null,context);
                return imagePath;
            }
        }else if("content".equalsIgnoreCase(uri.getScheme())){
            //如果是content类型的Uri，则使用普通方法处理
            imagePath = getImagePath(uri,null,context);
            return imagePath;
        }else if("file".equalsIgnoreCase(uri.getScheme())){
            //如果是file类型的Uri，直接获取图片路径即可
            imagePath = uri.getPath();
        }
        return imagePath;
    }

    public static String getImagePath(Uri uri, String selection,Context context){
        String path = null;
        //通过uri和selection来获取真实的图片路径
        Cursor cursor = context.getContentResolver().query(uri
                , null, selection
                , null, null);
        if(cursor != null){
            if(cursor.moveToFirst()){
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.
                        Media.DATA));
            }
            cursor.close();
        }
        return path;
    }
}
