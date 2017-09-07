package com.crazy.gy;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.aspsine.irecyclerview.IRecyclerView;
import com.aspsine.irecyclerview.animation.ScaleInAnimation;
import com.crazy.gy.adapter.RecyclerviewAdapter;
import com.crazy.gy.bean.ContansData;
import com.crazy.gy.util.CommentConfig;
import com.crazy.gy.widget.CommentListView;
import com.crazy.gy.widget.ZoneHeaderView;
import com.jaydenxiao.common.baseapp.AppCache;
import com.jaydenxiao.common.commonutils.DisplayUtil;
import com.jaydenxiao.common.commonutils.KeyBordUtil;
import com.jaydenxiao.common.commonutils.LogUtils;
import com.jaydenxiao.common.commonwidget.NormalTitleBar;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements RecyclerviewAdapter.IDialogKeyBoard {
    //朋友圈头部
    ZoneHeaderView zoneHeaderView;
    @Bind(R.id.ntb)
    NormalTitleBar ntb;
    @Bind(R.id.id_editext)
    EditText idEditext;
    @Bind(R.id.id_fasong)
    ImageView idFasong;
    @Bind(R.id.id_keyboard_vg)
    LinearLayout idKeyboardVg;
    @Bind(R.id.irc)
    IRecyclerView irc;
    private RecyclerviewAdapter recyclerviewAdapter;
    private Context mContext;
    private LinearLayoutManager linearLayoutManager;
    private CommentConfig mCommentConfig;

    private int mScreenHeight;
    private int mEditTextBodyHeight;
    private int mCurrentKeyboardH;
    private int mSelectCircleItemH;
    private int mSelectCommentItemOffset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mContext = MainActivity.this;
        ntb.setTitleText("动态列表");

        irc.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (idKeyboardVg.getVisibility() == View.VISIBLE) {
                    updateEdittextBodyVisible(View.GONE,null);//滑动的时候 隐藏键盘
                }
                return false;
            }
        });


        recyclerviewAdapter = new RecyclerviewAdapter(mContext, ContansData.backDatas());
        linearLayoutManager = new LinearLayoutManager(this);
        irc.setLayoutManager(linearLayoutManager);
        recyclerviewAdapter.setKeyBoardOnClickListener(this);
        View view = View.inflate(mContext, R.layout.item_zone_header, null);
        irc.addHeaderView(view);
        irc.setAdapter(recyclerviewAdapter);

        //监听recyclerview滑动
        setViewTreeObserver();

    }

    private void setViewTreeObserver() {
        final ViewTreeObserver swipeRefreshLayoutVTO = irc.getViewTreeObserver();
        swipeRefreshLayoutVTO.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Rect r = new Rect();
                irc.getWindowVisibleDisplayFrame(r);
                int statusBarH = getStatusBarHeight();//状态栏高度
                int screenH = irc.getRootView().getHeight();
                if (r.top != statusBarH) {
                    //在这个demo中r.top代表的是状态栏高度，在沉浸式状态栏时r.top＝0，通过getStatusBarHeight获取状态栏高度
                    r.top = statusBarH;
                }
                int keyboardH = screenH - (r.bottom - r.top);
                LogUtils.logd("screenH＝ " + screenH + " &keyboardH = " + keyboardH + " " + "&r.bottom=" + r.bottom + " &top=" + r.top + " &statusBarH=" + statusBarH);
                if (keyboardH == mCurrentKeyboardH) {//有变化时才处理，否则会陷入死循环
                    return;
                }
                mCurrentKeyboardH = keyboardH;
                mScreenHeight = screenH;//应用屏幕的高度
                mEditTextBodyHeight = idKeyboardVg.getHeight();

                //偏移listview
                if (irc != null && mCommentConfig != null) {
                    int index = mCommentConfig.circlePosition + irc.getHeaderContainer().getChildCount() +1;
                    linearLayoutManager.scrollToPositionWithOffset(index, getListviewOffset(mCommentConfig));
                }
            }
        });
    }

    private int getListviewOffset(CommentConfig commentConfig) {
        if (commentConfig == null)
            return 0;
        //这里如果你的listview上面还有其它占高度的控件，则需要减去该控件高度，listview的headview除外。
        int listviewOffset = mScreenHeight - mSelectCircleItemH - mCurrentKeyboardH - mEditTextBodyHeight - ntb.getMeasuredHeight();
        if (commentConfig.commentType == CommentConfig.Type.REPLY) {
            //回复评论的情况
            listviewOffset = listviewOffset + mSelectCommentItemOffset - ntb.getMeasuredHeight();
        }else if (commentConfig.commentType == CommentConfig.Type.PUBLIC){
            listviewOffset = listviewOffset  - ntb.getMeasuredHeight();
        }
        return listviewOffset;
    }

    private int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    private void updateEdittextBodyVisible(int visibility, CommentConfig commentConfig) {
        mCommentConfig = commentConfig;
        idKeyboardVg.setVisibility(visibility);//LinearLayout
        measureCircleItemHighAndCommentItemOffset(commentConfig);
        if (commentConfig != null && CommentConfig.Type.REPLY.equals(commentConfig.getCommentType())) {
            idEditext.setHint("回复" + commentConfig.getName() + ":");
        } else {
            idEditext.setHint("说点什么吧");
        }
        if (View.VISIBLE == visibility) {
            //弹出键盘
            idEditext.requestFocus();
            KeyBordUtil.showSoftKeyboard(idEditext);
        } else if (View.GONE == visibility) {
            //隐藏键盘
            KeyBordUtil.hideSoftKeyboard(idEditext);
        }
    }



    private void measureCircleItemHighAndCommentItemOffset(CommentConfig commentConfig) {
        if (commentConfig == null)
            return;
        int headViewCount = irc.getHeaderContainer().getChildCount();//IRecyclerView
        //当前选中的view
        int selectPostion = commentConfig.circlePosition + headViewCount + 1;//public int circlePosition;
        View selectCircleItem = linearLayoutManager.findViewByPosition(selectPostion);//  private LinearLayoutManager linearLayoutManager;

        if (selectCircleItem != null) {
            mSelectCircleItemH = selectCircleItem.getHeight() - DisplayUtil.dip2px(48);//    private int mSelectCircleItemH;
            //获取评论view,计算出该view距离所属动态底部的距离
            if (commentConfig.commentType == CommentConfig.Type.REPLY) {
                //回复评论的情况
                CommentListView commentLv = (CommentListView) selectCircleItem.findViewById(R.id.commentList);
                if (commentLv != null) {
                     //找到要回复的评论view,计算出该view距离所属动态底部的距离
                    View selectCommentItem = commentLv.getChildAt(commentConfig.commentPosition);
                    if (selectCommentItem != null) {
                        //选择的commentItem距选择的CircleItem底部的距离
                        mSelectCommentItemOffset = 0;
                        View parentView = selectCommentItem;
                        do {
                            int subItemBottom = parentView.getBottom();
                            parentView = (View) parentView.getParent();
                            if (parentView != null) {
                                mSelectCommentItemOffset += (parentView.getHeight() - subItemBottom);
                            }
                        }
                        while (parentView != null && parentView != selectCircleItem);
                    }
                }
            }
        }

    }

    @Override
    public void keyBoardOnClickListener(CommentConfig commentConfig) {
        //执行键盘弹出
        if (idKeyboardVg.getVisibility() == View.VISIBLE) {
            updateEdittextBodyVisible(View.GONE,commentConfig);
        } else if (idKeyboardVg.getVisibility() == View.GONE) {
            updateEdittextBodyVisible(View.VISIBLE,commentConfig);
        }
    }
}
