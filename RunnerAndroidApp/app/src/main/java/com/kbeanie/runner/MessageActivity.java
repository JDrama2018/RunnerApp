package com.kbeanie.runner;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.kbeanie.runner.adapters.DefaultMessagesAdapter;
import com.kbeanie.runner.adapters.MessagesAdapter;
import com.kbeanie.runner.adapters.OnMessageClickListener;
import com.kbeanie.runner.data.OrdersProvider;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by vidushi on 22/6/17.
 */

public class MessageActivity extends RunnerActivity implements OnMessageClickListener{
    private final static String TAG = MessageActivity.class.getSimpleName();
    @BindView(R.id.rvDefaultMessages)
    RecyclerView rvDefaultMessages;
    @BindView(R.id.layoutDefaultMessages)
    LinearLayout layoutDefaultMessages;
    @BindView(R.id.layoutMessages)
    LinearLayout layoutMessages;
    @BindView(R.id.rvMessages)
    RecyclerView rvMessages;
    @BindView(R.id.layoutEnterMessage)
    LinearLayout layoutEnterMessage;
    @BindView(R.id.etMessage)
    EditText etMessage;
    @BindView(R.id.btnSendMessage)
    ImageView ivSendMessage;

    private MessagesAdapter messagesAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);

        GridLayoutManager glm = new GridLayoutManager(this, 2);
        rvDefaultMessages.setLayoutManager(glm);
        DefaultMessagesAdapter adapter = new DefaultMessagesAdapter(this);
        adapter.setOnMessageClickListener(this);
        rvDefaultMessages.setAdapter(adapter);

        LinearLayoutManager lmMessages = new LinearLayoutManager(this);
        rvMessages.setLayoutManager(lmMessages);
        messagesAdapter = new MessagesAdapter(this, OrdersProvider.getSampleMessages());
        rvMessages.setAdapter(messagesAdapter);
    }

    @OnClick(R.id.ivClose)
    public void onCloseClick(){
        finish();
    }

    @Override
    public void onMessageClicked(String message) {
        Log.i(TAG, "Default Message: " + message);
        messagesAdapter.addMessage(message);
        showNotification(message);
    }

    @Override
    public void onCustomMessage() {
        Log.i(TAG, "Send Custom Message");
        rvDefaultMessages.setVisibility(View.GONE);
        layoutEnterMessage.setVisibility(View.VISIBLE);
        etMessage.requestFocus();
    }

    @OnClick(R.id.btnSendMessage)
    public void onSendMessage(){
        String message = etMessage.getText().toString();
        messagesAdapter.addMessage(message);
        showNotification(message);
        rvDefaultMessages.setVisibility(View.VISIBLE);
        layoutEnterMessage.setVisibility(View.GONE);
        etMessage.setText("");
    }
}
