package com.example.dell.rtmeeting.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.dell.rtmeeting.R;
import com.example.dell.rtmeeting.zxing.encoding.EncodingHandler;
import com.google.zxing.WriterException;

/**
 * Created by JiangJun on 2018/1/23.
 */

public class MyQrCode extends AppCompatActivity {

    private ImageView mImageView;
    private final String MESS="message";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_qr_code);

        mImageView=(ImageView)findViewById(R.id.image_view);

        Intent intent=getIntent();
        String mMessage=intent.getStringExtra(MESS);
        //生成二维码
        try {
            Bitmap mBitmap= EncodingHandler.createQRCode(mMessage,500);
            mImageView.setImageBitmap(mBitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }
}
