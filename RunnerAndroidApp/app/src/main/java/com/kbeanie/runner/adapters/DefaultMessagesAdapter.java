package com.kbeanie.runner.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kbeanie.runner.R;
import com.kbeanie.runner.data.OrdersProvider;
import com.xiaofeng.flowlayoutmanager.cache.Line;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by vidushi on 21/6/17.
 */

public class DefaultMessagesAdapter extends RecyclerView.Adapter<DefaultMessagesAdapter.DefaultMessagesVH> {

    private OnMessageClickListener listener;
    private List<String> defaultMessages;
    private Context context;

    public DefaultMessagesAdapter(Context context) {
        this.context = context;
        this.defaultMessages = OrdersProvider.getSampleDefaultMessages();
    }

    public void setOnMessageClickListener(OnMessageClickListener listener){
        this.listener = listener;
    }

    @Override
    public DefaultMessagesAdapter.DefaultMessagesVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_default_message, parent, false);
        DefaultMessagesVH holder = new DefaultMessagesVH(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(DefaultMessagesAdapter.DefaultMessagesVH holder, int position) {
        holder.tvMessage.setText(defaultMessages.get(position));
        if (position == defaultMessages.size() - 1) {
            holder.layoutOuter.setBackgroundResource(R.drawable.default_custom_message_bg);
        } else {
            holder.layoutOuter.setBackgroundResource(R.drawable.default_message_bg);
        }

        holder.bind(defaultMessages.get(position), listener, defaultMessages.size() - 1 == position);
    }

    @Override
    public int getItemCount() {
        return defaultMessages.size();
    }

    static class DefaultMessagesVH extends RecyclerView.ViewHolder {

        @BindView(R.id.tvMessage)
        TextView tvMessage;
        @BindView(R.id.layoutOuter)
        LinearLayout layoutOuter;

        public DefaultMessagesVH(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(final String message, final OnMessageClickListener listener, final boolean last){
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(last){
                        listener.onCustomMessage();
                    }else{
                        listener.onMessageClicked(message);
                    }
                }
            });
        }
    }
}
