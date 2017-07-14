package com.kbeanie.runner.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kbeanie.runner.R;
import com.kbeanie.runner.entity.Order;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by vidushi on 22/6/17.
 */

public class MessagesAdapter extends RecyclerView.Adapter<MessagesAdapter.MessagesVH> {

    private Context context;
    private List<String> messages;
    public MessagesAdapter(Context context, List<String> messages){
        this.context = context;
        this.messages = messages;
    }

    @Override
    public MessagesVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_message, parent, false);
        MessagesVH holder = new MessagesVH(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MessagesVH holder, int position) {
        holder.tvMessage.setText(messages.get(position));
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public void addMessage(String message) {
        messages.add(message);
        notifyDataSetChanged();
    }

    static class MessagesVH extends RecyclerView.ViewHolder{

        @BindView(R.id.tvMessage)
        TextView tvMessage;

        public MessagesVH(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
