package com.example.interview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.example.interview.ui.main.MainFragment;

public class MainActivity extends AppCompatActivity {


    private Handler handler=new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow();
        }

        LooperThread thread=new LooperThread();
        thread.run();

        
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