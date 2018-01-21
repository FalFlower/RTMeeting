package com.example.dell.rtmeeting.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.dell.rtmeeting.R;
import com.example.dell.rtmeeting.activity.MainActivity;
import com.example.dell.rtmeeting.activity.UserDataActivity;
import com.example.dell.rtmeeting.adapter.UserAdapter;
import com.example.dell.rtmeeting.customClass.User;

import java.net.IDN;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by dell on 2018/1/19.
 */

public class TabListFragment extends Fragment implements AdapterView.OnItemClickListener {

    private static final String EXTRA_CONTENT = "content";
    private ListView mContentLv;



    public static TabListFragment newInstance(String content,int id) {
        Bundle arguments = new Bundle();
        arguments.putString(EXTRA_CONTENT, content);
        TabListFragment tabListFragment;
//        int clock=0;
//        switch (id) {
//            case 0:
//                break;
//            case 1:
//                clock=1;
//                break;
//            case 2:
//                clock=2;
//                break;
//            default:
//                break;
//        }
        tabListFragment = new TabListFragment();
        tabListFragment.setArguments(arguments);
        return tabListFragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View contentView = inflater.inflate(R.layout.list, null);

        mContentLv = (ListView) contentView.findViewById(R.id.lv_content);
        mContentLv.setOnItemClickListener(this);
        ViewCompat.setNestedScrollingEnabled(mContentLv, true);
        mContentLv.setAdapter(new ContentAdapter());

        return contentView;
    }



    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }

    private class ContentAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return 10;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.message_list, null);
//            ImageView coverIv = (ImageView) contentView.findViewById(R.id.iv_cover);
//            coverIv.setImageResource(R.drawable.ic_image);
            contentView.findViewById(R.id.cv_content).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(v.getContext(), UserDataActivity.class);
                    intent.putExtra(UserDataActivity.DATA_NAME,"zhangtian");
                    intent.putExtra(UserDataActivity.DATA_IMAGE_ID,R.drawable.ic_image0);
                    getActivity().startActivity(intent);
                    Toast.makeText(v.getContext(),"haha",Toast.LENGTH_SHORT).show();
                }
            });
            return contentView;
        }
    }

}
