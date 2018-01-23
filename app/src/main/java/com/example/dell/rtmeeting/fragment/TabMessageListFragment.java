package com.example.dell.rtmeeting.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.dell.rtmeeting.R;

/**
 * Created by dell on 2018/1/20.
 */

public class TabMessageListFragment extends Fragment implements AdapterView.OnItemClickListener {

    private static final String EXTRA_CONTENT = "content";
    private ListView mContentLv;

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
                    Toast.makeText(v.getContext(),"haha",Toast.LENGTH_SHORT).show();
                }
            });
            return contentView;
        }
    }

}

