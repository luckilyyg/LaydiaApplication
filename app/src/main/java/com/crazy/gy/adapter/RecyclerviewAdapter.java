package com.crazy.gy.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.DynamicDrawableSpan;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.crazy.gy.R;
import com.crazy.gy.bean.CirContents;
import com.crazy.gy.bean.Comment;
import com.crazy.gy.bean.Favort;
import com.crazy.gy.interfaces.ISpanClick;
import com.crazy.gy.util.CircleMovementMethod;
import com.crazy.gy.util.CommentConfig;
import com.crazy.gy.util.NameClickable;
import com.crazy.gy.util.ToastUtil;
import com.crazy.gy.view.FavortListView;
import com.crazy.gy.view.MyListView;
import com.crazy.gy.widget.CommentListView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jaydenxiao.common.baseapp.AppCache;
import com.ms.square.android.expandabletextview.ExpandableTextView;

import java.util.List;

/**
 * 作者：Administrator
 * 时间：2017/8/18
 * 功能：
 */
public class RecyclerviewAdapter extends RecyclerView.Adapter {
    private Context context;// 上下文
    private List<CirContents> list;// 数据集合
    private SpannableStringBuilder builder;
    CommentAdapter commentAdapter;
    com.crazy.gy.bean.CommentAdapter commentAdapters;
    IDialogKeyBoard mIDialogKeyBoard;
    IRefreshData mIRefreshData;

    public interface IRefreshData {
        void refreshDataOnClickListener();
    }

    public void setRefreshData(IRefreshData mIRefreshData) {
        this.mIRefreshData = mIRefreshData;
    }

    public interface IDialogKeyBoard {
        void keyBoardOnClickListener(CommentConfig commentconfig);
    }

    public void setKeyBoardOnClickListener(IDialogKeyBoard mIDialogKeyBoard) {
        this.mIDialogKeyBoard = mIDialogKeyBoard;
    }

    public RecyclerviewAdapter(Context context, List<CirContents> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_circle_list, parent, false);
        //以下一步：负责实例化传入的待填充的view的容器
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final MyViewHolder myViewHolder = (MyViewHolder) holder;
        CirContents cirContents = list.get(position);
//        myViewHolder.mImg.setImageURI(list.get(position).getsImg());
        myViewHolder.mImgs.setImageResource(R.mipmap.ic_launcher);
        myViewHolder.mName.setText(list.get(position).getsName());
        myViewHolder.mTake.setText("发单:" + list.get(position).getSendNum() + " " + "接单:" + list.get(position).getAcceptNum());
        myViewHolder.tv_expandcontent.setText(list.get(position).getsContent());
        myViewHolder.mFarfrom.setText(list.get(position).getFarNum() + "");
        myViewHolder.mTime.setText(list.get(position).getsTime());
        commentAdapters = new com.crazy.gy.bean.CommentAdapter(context);
        myViewHolder.commentListView.setAdapter(commentAdapters);

        final List<Favort> favortList = cirContents.getGoodjobs();
        final List<Comment> commentList = cirContents.getReplys();
        final boolean hasFavort = cirContents.getGoodjobs().size() > 0 ? true : false;//点赞的数量
        final boolean hasComment = cirContents.getReplys().size() > 0 ? true : false;//评论的数量
        myViewHolder.delete_lin.setVisibility(hasFavort && hasComment ? View.VISIBLE : View.GONE);
        if (hasFavort || hasComment) {
            myViewHolder.bodyLayout.setVisibility(View.VISIBLE);
            if (hasFavort) {
                myViewHolder.favortListView.setVisibility(View.VISIBLE);
                myViewHolder.favortListView.setSpanClickListener(new ISpanClick() {
                    @Override
                    public void onClick(int position) {
                        ToastUtil.show(context, "----拿到userId，点击点赞人进行操作----" + position);
                    }
                });
                defineData(favortList, myViewHolder.favortListView);
            } else {
                myViewHolder.favortListView.setVisibility(View.GONE);
            }

            if (hasComment) {
                myViewHolder.commentListView.setVisibility(View.VISIBLE);
                commentAdapters.setDatas(commentList);
                commentAdapters.notifyDataSetChanged();
                myViewHolder.commentListView.setOnItemClick(new CommentListView.OnItemClickListener() {
                    @Override
                    public void onItemClick(int commentPosition) {
                        Comment comment = commentList.get(commentPosition);
                        if (AppCache.getInstance().getUserId().equals(comment.getUserId())) {//复制或者删除自己的评论
//                            CommentDialog dialog = new CommentDialog(mContext, mPresenter, commentItem, position,commentPosition);
//                            dialog.show();
                        } else {//回复别人的评论
                            CommentConfig config = new CommentConfig();
                            config.circlePosition = position;
                            config.commentPosition = commentPosition;
                            config.commentType = CommentConfig.Type.REPLY;
//                                config.setPublishId(circleItem.getId());
//                                config.setPublishUserId(circleItem.getUserId());//动态人userid
//                                config.setId(commentItem.getUserId());//评论人userid
//                                config.setName(commentItem.getUserNickname());//评论人nickname
                            mIDialogKeyBoard.keyBoardOnClickListener(config);

                        }
                    }
                });

            } else {
                myViewHolder.commentListView.setVisibility(View.GONE);
            }
        } else {
            myViewHolder.bodyLayout.setVisibility(View.GONE);
        }

        //点赞的点击事件
        myViewHolder.btn_praise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //判断当前favortListView是否为空
                if (hasFavort) {
                    defineData(favortList, myViewHolder.favortListView);
                    favortList.add(new Favort("赵老六", "000"));
                    builder.append(",");
                    builder.append(setClickableSpan("赵老六", favortList.size() - 1, myViewHolder.favortListView));
                    myViewHolder.favortListView.setText(builder);
                } else {
                    myViewHolder.bodyLayout.setVisibility(View.VISIBLE);
                    myViewHolder.favortListView.setVisibility(View.VISIBLE);
                    favortList.clear();
                    favortList.add(new Favort("小丑", "666"));
                    defineData(favortList, myViewHolder.favortListView);
                    mIRefreshData.refreshDataOnClickListener();
                }

            }
        });
        myViewHolder.btn_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //弹出键盘
                comment(position);
            }
        });
    }

    private void comment(int positions) {
        CommentConfig config = new CommentConfig();
        config.circlePosition = positions;
        config.commentType = CommentConfig.Type.PUBLIC;
        mIDialogKeyBoard.keyBoardOnClickListener(config);
    }

    private void defineData(List<Favort> datas, FavortListView v) {
        if (v == null) {
            Log.e("执行的代码为空", "listview is null, please bindListView first...");
        }
        builder = new SpannableStringBuilder();
        if (datas != null && datas.size() > 0) {
            //添加点赞图标
            builder.append(setImageSpan());
            //builder.append("  ");
            Favort item = null;
            for (int i = 0; i < datas.size(); i++) {
                item = datas.get(i);
                if (item != null) {
//                    builder.append(item.getsName());//添加点赞的名字列表
                    builder.append(setClickableSpan(item.getsName(), i, v));//添加点赞的名字列表
                    if (i != datas.size() - 1) {
                        builder.append(", ");
                    }
                }
            }
        }
        v.setText(builder);
        v.setMovementMethod(new CircleMovementMethod(R.color.circle_name_selector_color));//开始响应点击事件
    }

    private SpannableString setImageSpan() {
        String text = "  ";
        SpannableString imgSpanText = new SpannableString(text);
        imgSpanText.setSpan(new ImageSpan(context, R.drawable.dianzansmal, DynamicDrawableSpan.ALIGN_BASELINE),
                0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return imgSpanText;
    }

    private SpannableString setClickableSpan(String textStr, int position, FavortListView v) {
        SpannableString subjectSpanText = new SpannableString(textStr);
        subjectSpanText.setSpan(new NameClickable(v.getSpanClickListener(), position), 0, subjectSpanText.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return subjectSpanText;//这个是获取点击事件

    }

    @Override
    public int getItemCount() {
        if (list != null) {
            return list.size();
        } else {
            return 0;
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        //        public TextView textViewSetting;/*根据需要更改*///初始化细分布局中的元素
        public SimpleDraweeView mImg;
        public TextView mName, mTake, mTime, mFarfrom;
        public LinearLayout bodyLayout;
        public FavortListView favortListView;
        public TextView btn_praise, btn_comment;
        public ExpandableTextView tv_expandcontent;
        public TextView tv_expand;
        public View delete_lin;
        public CommentListView commentListView;
        //        public ListView comment_listview;
//        public MyListView myListView;
        public ImageView mImgs;
//        public RecyclerView myRecyclerview;


        public MyViewHolder(View itemView) {
            super(itemView);//这里的itemView指的是细分布局，即需要进行重复构建的布局
//            mImg = (SimpleDraweeView) itemView.findViewById(R.id.headIv);/*根据需要更改*///布局中的TextView需要填写
            mImgs = (ImageView) itemView.findViewById(R.id.headIv_image);
            mName = (TextView) itemView.findViewById(R.id.nameTv);
            mTake = (TextView) itemView.findViewById(R.id.tv_order_situation);
            tv_expandcontent = (ExpandableTextView) itemView.findViewById(R.id.urlTipTv);
            mTime = (TextView) itemView.findViewById(R.id.timeTv);
            mFarfrom = (TextView) itemView.findViewById(R.id.tv_address_or_distance);
            bodyLayout = (LinearLayout) itemView.findViewById(R.id.digCommentBody);
            favortListView = (FavortListView) itemView.findViewById(R.id.favortListTvv);
            btn_comment = (TextView) itemView.findViewById(R.id.commentBtn);
            btn_praise = (TextView) itemView.findViewById(R.id.favortBtn);
            tv_expand = (TextView) itemView.findViewById(R.id.expandable_text);
            delete_lin = itemView.findViewById(R.id.lin_dig);
            commentListView = (CommentListView) itemView.findViewById(R.id.commentList);
//            myListView = (MyListView) itemView.findViewById(R.id.commentList);
        }
    }
}
