package com.fansu.yimaomiao.view.fragment.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.easemob.chat.EMChatManager;
import com.easemob.chat.EMConversation;
import com.easemob.easeui.EaseConstant;
import com.easemob.easeui.ui.EaseConversationListFragment;
import com.fansu.yimaomiao.R;
import com.fansu.yimaomiao.base.BaseFragment;
import com.fansu.yimaomiao.customview.chat.ConversationListFragment;
import com.fansu.yimaomiao.view.activity.ease.ChatActivity;

import butterknife.BindView;
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;
import cn.bingoogolapple.refreshlayout.BGARefreshViewHolder;

/**
 * Created by leo on 16/11/1.
 */

public class ChatFragment extends BaseFragment implements EaseConversationListFragment.EaseConversationListItemClickListener, BGARefreshLayout.BGARefreshLayoutDelegate {
    private ConversationListFragment conversationListFragment;
    @BindView(R.id.chat_bgarefresh)
    BGARefreshLayout mBgaRefresh;

    @Override
    protected int setRootView() {
        return R.layout.fragment_chat;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        conversationListFragment = new ConversationListFragment();
        conversationListFragment.setConversationListItemClickListener(this);
        FragmentManager fm = mActivity.getSupportFragmentManager();
        FragmentTransaction trs = fm.beginTransaction();
        trs.add(R.id.chat_framelayout, conversationListFragment);
        trs.commit();
        initListener();
        initRefreshLayout();
    }

    private void initRefreshLayout() {
        mBgaRefresh.setDelegate(this);
        BGARefreshViewHolder refreshViewHolder = new BGANormalRefreshViewHolder(mActivity, false);
        mBgaRefresh.setRefreshViewHolder(refreshViewHolder);
    }


    /**
     * 新消息提醒
     * 只接受新消息的提醒
     */
    private void initListener() {
        EMChatManager.getInstance().registerEventListener(emNotifierEvent -> {
            conversationListFragment.refresh();
        });
    }

    @Override
    public void onListItemClicked(EMConversation conversation) {
        startActivity(new Intent(mActivity, ChatActivity.class).putExtra(EaseConstant.EXTRA_USER_ID, conversation.getUserName()));
    }



    @Override
    public void onRefreshComplete() {
        mBgaRefresh.endRefreshing();
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        conversationListFragment.refresh();
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        return false;
    }
}
