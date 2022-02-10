package com.example.interview.ui.main;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.interview.R;

public class MainFragment extends Fragment {

    private MainViewModel mViewModel;

    private Handler handler=new Handler();
    private Handler handler1;
    private Handler handler2;
    private TextView textView;
    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.main_fragment, container, false);
        textView=view.findViewById(R.id.message);
        long mainId=Thread.currentThread().getId();
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        new Thread(new Runnable() {
            public void run() {
                Looper.prepare();
                Looper loop1=Looper.myLooper();
                handler1= new Handler(Looper.myLooper()) {
                    public void handleMessage(Message msg) {
                       if(msg.what == 1){
                           Log.d("a",11+"");
                       }

                    }
                };
                Looper.loop();
                handler1.sendEmptyMessage(1);
            }
        }).start();
        new Thread(new Runnable() {
            public void run() {
                Looper.prepare();
                Looper loop1=Looper.myLooper();
                handler2 = new Handler(Looper.myLooper()) {
                    public void handleMessage(Message msg) {
                        if(msg.what == 2){
                            Log.d("a",112+"");
                        }

                    }
                };
                Looper.loop();

                handler2.sendEmptyMessage(2);

            }
        }).start();
        new Thread(new Runnable() {
            public void run() {
                Looper.prepare();
                Handler handler = new Handler(){
                    @Override
                    public void handleMessage(Message msg) {
                        Toast.makeText(getContext(), "handler msg", Toast.LENGTH_LONG).show();
                    }
                };
                handler.sendEmptyMessage(1);
                Looper.loop();
            };
        }).start();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MainViewModel.class);

    }

    @Override
    public void onStart() {
        super.onStart();

    }

    class LooperThread extends Thread {
        public Handler mHandler;

        public void run() {

            if (Looper.myLooper()==null)
                Looper.prepare();
            mHandler = new Handler(Looper.myLooper()) {
                public void handleMessage(Message msg) {
                    // process incoming messages here
                }
            };

            Looper.loop();
        }
    }
}