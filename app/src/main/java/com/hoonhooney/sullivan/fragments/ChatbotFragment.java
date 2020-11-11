package com.hoonhooney.sullivan.fragments;

import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.os.Handler;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.hoonhooney.sullivan.Chat;
import com.hoonhooney.sullivan.ChatAdapter;
import com.hoonhooney.sullivan.R;
import com.hoonhooney.sullivan.VoiceTask;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

import static com.hoonhooney.sullivan.VoiceTask.VOICE_TASK;

public class ChatbotFragment extends Fragment {

    private ListView listView_chat;
    private FrameLayout btn_record;
    String [] answerList = {"안녕!", "기분 좋은 하루예요!", "저도 당신을 만나서 좋아요!","잘 이해하지 못했어요..ㅜㅜ 서비스 개선을 위해 노력중이니 이해해 주세요."};

    private ChatAdapter chatAdapter;

    private Handler mHandler;

    private Socket socket;

    private BufferedReader networkReader;
    private PrintWriter networkWriter;

    private DataOutputStream dos;
    private DataInputStream dis;

    private String ip = "192.168.0.2";            // IP 번호
    private int port = 7777;

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

        view.findViewById(R.id.btn_chatbot_record).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new VoiceTask(ChatbotFragment.this).execute();
            }
        });

        return view;
    }

    //VoiceTask 결과 받는 부분
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == getActivity().RESULT_OK && requestCode == VOICE_TASK){
            ArrayList<String> results = data
                    .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

            if (results != null){
                //구글 마이크에서 받아온 String
                String strResult = results.get(0);
                chatAdapter.add(new Chat(true, strResult));
                if(strResult.contains("안녕")){
                chatAdapter.add(new Chat(false, answerList[0]));}
                else if(strResult.contains("하루")){
                    chatAdapter.add(new Chat(false, answerList[1]));}
                else if(strResult.contains("좋아")){
                    chatAdapter.add(new Chat(false, answerList[2]));}
                else {
                    chatAdapter.add(new Chat(false, answerList[3]));}
//                try {
//                    get_Answer(strResult);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
            }
        }
    }

    public void get_Answer(final String user_chat) throws IOException {
        mHandler = new Handler();

        Thread checkUpdate = new Thread(){
            public void run(){
                try{
                    socket = new Socket(ip, port);
                    Log.w("서버 접속","서버 접속");
                } catch (IOException e) {
                    Log.w("서버 접속 실패","서버 접속 실패");
                }

                try{
                    dos = new DataOutputStream(socket.getOutputStream());
                    dis = new DataInputStream(socket.getInputStream());
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.w("버퍼","버퍼생성 잘못됨");
                }
                Log.w("버퍼","버퍼생성 잘됨");


                try{
                    dos.writeUTF(user_chat);
                    String line="";
                    while(true){
                        dos.flush();
                        //EOF Exception남.
                        line = dis.readUTF();
                        Log.w(line, line);

                        chatAdapter.add(new Chat(false, line));
                        socket.close();
                        dos.close();
                        dis.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        };
        checkUpdate.start();
    }
}