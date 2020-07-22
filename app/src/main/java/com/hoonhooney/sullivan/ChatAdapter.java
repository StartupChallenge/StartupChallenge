package com.hoonhooney.sullivan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class ChatAdapter extends ArrayAdapter {

    List<Chat> chatList = new ArrayList<>();

    private LinearLayout linearLayout_chatBox;
    private TextView textView_chat, textView_time;

    public ChatAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

    public void add(Chat chat){
        chatList.add(chat);
        notifyDataSetChanged();
        super.add(chat);
    }

    @Override
    public int getCount(){
        return chatList.size();
    }

    @Override
    public Chat getItem(int index){
        return chatList.get(index);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View row = convertView;
        if (row == null){
            LayoutInflater inflater = (LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.view_chatbox, parent, false);
        }

        Chat message = (Chat) chatList.get(position);

        if (message.isUser()){
            linearLayout_chatBox = row.findViewById(R.id.linearLayout_chat_user);
            textView_chat = row.findViewById(R.id.textView_chat_user);
            textView_time = row.findViewById(R.id.textView_time_user);
        } else{
            linearLayout_chatBox = row.findViewById(R.id.linearLayout_chat_bot);
            textView_chat = row.findViewById(R.id.textView_chat_bot);
            textView_time = row.findViewById(R.id.textView_time_bot);
        }

        textView_chat.setText(message.getMessage());
        textView_time.setText(message.getTime());

        return row;
    }
}
