package com.example.dell.rtmeeting.activity;

import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dell.rtmeeting.R;

public class UserDataActivity extends AppCompatActivity {

    public static final String DATA_NAME="Data_name";
    public static final String DATA_IMAGE_ID="Data_image_id";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_data);

        Intent intent=getIntent();
        String userName=intent.getStringExtra(DATA_NAME);
        int userImageId=intent.getIntExtra(DATA_IMAGE_ID,0);
        Toolbar toolbar=(Toolbar)findViewById(R.id.userdata_toolbar);
        CollapsingToolbarLayout collapsingToolbarLayout=(CollapsingToolbarLayout)findViewById(R.id.collpapsing_toolbar);
        ImageView imageView=(ImageView)findViewById(R.id.data_image);

        TextView contentText=(TextView)findViewById(R.id.data_text);
        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        collapsingToolbarLayout.setTitle(userName);
        Glide.with(this).load(userImageId).into(imageView);
        String dataContent="Hahahahahahhahahahhahahahahhahhahahahahahahhahahahhahahahhahahahahhahahahahhahahahahahahahahah";
        contentText.setText(dataContent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }
}
