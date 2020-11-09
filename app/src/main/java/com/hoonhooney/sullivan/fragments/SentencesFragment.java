package com.hoonhooney.sullivan.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;

import androidx.fragment.app.Fragment;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import com.hoonhooney.sullivan.R;

public class SentencesFragment extends Fragment
        implements View.OnClickListener {
    public EditText inputText;
    public TextView outputText;
    public Button send_btn;

    private String html = "";
    private Handler mHandler;

    private Socket socket;

    private BufferedReader networkReader;
    private PrintWriter networkWriter;

    private DataOutputStream dos;
    private DataInputStream dis;

    private String ip = "192.168.0.2";            // IP 번호
    private int port = 9999;

    public SentencesFragment() {
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
        View view = inflater.inflate(R.layout.fragment_sentences, container, false);

        inputText = view.findViewById(R.id.editText_sentence_practice);
        outputText = view.findViewById(R.id.textView_sentence_pronunciation);

        send_btn = view.findViewById(R.id.send_button);
        send_btn.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view){
        switch(view.getId()){
            case R.id.send_button:
                Log.w("클릭한거 먹힘","클릭된거 인식함");
                try {
                    get_phenome();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    public void get_phenome() throws IOException {
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
                    dos.writeUTF(inputText.getText().toString());
                    String line="";
                    while(true){
                        dos.flush();
                        line = dis.readUTF();
                        Log.w(line, line);

                        outputText.setText(line);
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