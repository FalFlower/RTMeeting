package com.example.dell.rtmeeting.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.rtmeeting.R;
import com.example.dell.rtmeeting.customClass.User;
import com.example.dell.rtmeeting.adapter.UserAdapter;
import com.example.dell.rtmeeting.fragment.TabListFragment;
import com.example.dell.rtmeeting.utils.CommonUtil;
import com.example.dell.rtmeeting.zxing.encoding.EncodingHandler;
import com.example.dell.rtmeeting.zxing.view.zxing.activity.CaptureActivity;
import com.google.zxing.WriterException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {


    private FloatingActionButton floatingActionButton;
    private NavigationView navigationView;
    private DrawerLayout mDrawerLayout;//滑动菜单
    private ActionBarDrawerToggle drawerToggle;// v7兼容包提供一个给侧滑控件显示拖动状态的控件
    private SwipeRefreshLayout swipeRefreshLayout;

    private ViewPager mContentVp;
    private TabLayout tablayout;
    private ContentPagerAdapter contentAdapter;

    private List<String>tabIndicators;
    private List<Fragment> tabFragments;
//    private tabActivity.ContentPagerAdapter contentAdapter;

    //打开扫描界面请求码
    private final int REQUEST_CODE=0x01;
    //扫描成功返回码
    private final int RESULT_CODE=0xA1;
    //打开生成二维码请求码
    private final String MESS="message";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tablayout=(TabLayout)findViewById(R.id.tablayout);
        mContentVp=(ViewPager)findViewById(R.id.viewpager);

        initToobar();//初始化Toolbar
        initDrawer();//初始化侧边栏
        initFloatingActionButton();//初始化悬浮按键
        initContent();//初始化内容
        initTab();//初始化Tab
        initSwipeRefresh();
    }

    private void initSwipeRefresh() {
        swipeRefreshLayout=(SwipeRefreshLayout)findViewById(R.id.swiperefresh);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
            }
        });
    }

    private void refresh() {
         new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        initContent();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                });
            }
        }).start();
    }

    private void initContent() {
        int who=0;
        tabIndicators=new ArrayList<>();
        tabIndicators.add("消息");
        tabIndicators.add("好友");
        tabIndicators.add("会议");

        tabFragments=new ArrayList<>();

        for (String s : tabIndicators) {
            tabFragments.add(TabListFragment.newInstance(s,who));
            who++;
        }

        contentAdapter=new ContentPagerAdapter(getSupportFragmentManager());
        mContentVp.setAdapter(contentAdapter);
    }

    private void initTab() {
        tablayout.setTabMode(TabLayout.MODE_FIXED);//水平铺满
//        tablayout.setTabTextColors();
//        tablayout.setSelectedTabIndicatorColor();
        ViewCompat.setElevation(tablayout,10);
        tablayout.setupWithViewPager(mContentVp);
    }


    private void initToobar() {
        Toolbar toolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }
    }

    private void initDrawer() {
        mDrawerLayout=(DrawerLayout)findViewById(R.id.drawer_layout);
        navigationView=(NavigationView)findViewById(R.id.nav_view) ;
        drawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.app_name, R.string.app_name);
        mDrawerLayout.setDrawerListener(drawerToggle);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_qr:
                        Toast.makeText(MainActivity.this,"Ok",Toast.LENGTH_SHORT).show();
//                        mDrawerLayout.closeDrawers();
//                        跳转页面显示二维码
                        String myMessage=" ID:FFSnow \n Email:zt847269989@outlook.com";//todo 先hardcode
                        Intent intent=new Intent(MainActivity.this,MyQrCode.class);
                        intent.putExtra(MESS,myMessage);
                        startActivity(intent);
                        break;
                    case R.id.nav_settings:
                        Toast.makeText(MainActivity.this,"Ok",Toast.LENGTH_SHORT).show();
                        mDrawerLayout.closeDrawers();
                        break;
                    case R.id.nav_local:
                        Toast.makeText(MainActivity.this,"Ok",Toast.LENGTH_SHORT).show();
                        mDrawerLayout.closeDrawers();
                        break;
                    default:
                        break;
                }
                return true;
            }
        });

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initFloatingActionButton() {
        floatingActionButton=(FloatingActionButton)findViewById(R.id.floatingbutton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view,"Data deleted",Snackbar.LENGTH_SHORT).setAction("Undo", new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//
//                        Toast.makeText(MainActivity.this,"Ok",Toast.LENGTH_SHORT).show();
//                    }
//                }).show();
                scanQRCode();
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return true;
    }

//ActionBarDrawerToggle
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }
    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if (drawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.settings:
//                startActivity(new Intent(this, tabActivity.class));
                Toast.makeText(this,"Ok",Toast.LENGTH_SHORT).show();
                break;

            default:
                    break;
        }
        return true;
    }


    private void scanQRCode(){
        //在授权前提下，开启二维码扫描
        if(CommonUtil.isCameraCanUse()){
        Intent intent=new Intent(MainActivity.this, com.example.dell.rtmeeting.zxing.activity.CaptureActivity.class);
        startActivityForResult(intent,REQUEST_CODE);
    }else{
        Toast.makeText(MainActivity.this, "请开启本应用的摄像头使用权限！", Toast.LENGTH_SHORT).show();
    }
}

    class ContentPagerAdapter extends FragmentPagerAdapter {

        public ContentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return tabFragments.get(position);
        }

        @Override
        public int getCount() {
            return tabIndicators.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabIndicators.get(position);
        }

    }

}
