package com.hoonhooney.sullivan.fragments;

import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.hoonhooney.sullivan.Chat;
import com.hoonhooney.sullivan.ChatAdapter;
import com.hoonhooney.sullivan.R;

public class ChatbotFragment extends Fragment {

    private ListView listView_chat;
    private FrameLayout btn_record;

    private ChatAdapter chatAdapter;

    public ChatbotFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_chatbot, container, false);

        listView_chat = view.findViewById(R.id.listView_chat);
        btn_record = view.findViewById(R.id.btn_chatbot_record);

        chatAdapter = new ChatAdapter(getContext(), R.layout.view_chatbox);
        listView_chat.setAdapter(chatAdapter);
        listView_chat.setTranscriptMode(ListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);

        chatAdapter.registerDataSetObserver(new DataSetObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                listView_chat.setSelection(chatAdapter.getCount()-1);
            }
        });

        chatAdapter.add(new Chat(false, getString(R.string.chatbot_greeting)));

        return view;
    }
}