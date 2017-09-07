package com.crazy.gy.adapter;

import android.content.Context;


import com.crazy.gy.R;
import com.crazy.gy.bean.Comment;
import com.crazy.gy.view.ViewHolder;

import java.util.List;

/**
 * 作者：Administrator
 * 时间：2017/8/10
 * 功能：
 */
public class CommentAdapter extends CommonAdapter<Comment> {


    public CommentAdapter(Context context, int layoutId, List<Comment> datas) {
        super(context, layoutId, datas);
    }

    @Override
    public void convert(ViewHolder holder, Comment comment) {
        holder.setText(R.id.id_tv_name,comment.getsUserName());
        holder.setText(R.id.id_tv_comment,comment.getsContent());
    }
}
