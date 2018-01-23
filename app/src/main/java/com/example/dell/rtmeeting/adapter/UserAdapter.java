package com.example.dell.rtmeeting.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dell.rtmeeting.R;
import com.example.dell.rtmeeting.customClass.User;

import java.util.List;

/**
 * Created by dell on 2018/1/16.
 */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder>{

    private Context mContext;
    private List<User> mUserList;

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView userImage;
        TextView userName;
        TextView userMail;
        public ViewHolder(View view) {
            super(view);
            cardView=(CardView)view;
            userImage=(ImageView)view.findViewById(R.id.userImage);
            userName=(TextView)view.findViewById(R.id.userName);
            userMail=(TextView)view.findViewById(R.id.userMail);
        }
    }

    public UserAdapter(List<User> mUserList) {
        this.mUserList = mUserList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext=parent.getContext();
        }
        View view= LayoutInflater.from(mContext).inflate(R.layout.userslist,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        User user=mUserList.get(position);
        holder.userName.setText(user.getName());
        holder.userMail.setText(user.getMail());
        Glide.with(mContext).load(user.getImageId()).into(holder.userImage);
    }

    @Override
    public int getItemCount() {
        return mUserList.size();
    }


}
