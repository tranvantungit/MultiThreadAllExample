package com.example.tranvantungit.multithreadallexample.thread;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.tranvantungit.multithreadallexample.R;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadActivity extends AppCompatActivity {

    Handler mHandler;
    private final String MESSAGE_KEY = "MESSAGE_KEY";

    Button runThread;
    TextView resultText;
    TextView progress;
    Button runThreadRunnable;
    ExecutorService mExecutorService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);

        runThread = findViewById(R.id.runThread);
        progress = findViewById(R.id.progress);
        resultText = findViewById(R.id.result);
        runThreadRunnable = findViewById(R.id.runThreadRunnable);

        resultText.setText("");
        mHandler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                Bundle bundle = msg.getData();
                String message = bundle.getString(MESSAGE_KEY);
                //resultText.setText(message);
                resultText.append(message + "\n");
                Log.e("TUNG", "handleMessage:Thread:" + Thread.currentThread().getName());
            }
        };

        runThread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultText.setText("");
                createThread();
            }
        });

        runThreadRunnable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultText.setText("");
                createThreadRunnable();
            }
        });

        mExecutorService = Executors.newFixedThreadPool(5);

    }

    public void createThread() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Log.e("TUNG", "Thread starting for 5 seconds");

                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Message message = new Message();
                Bundle bundle = new Bundle();
                bundle.putString(MESSAGE_KEY, "This is Message from thread!");
                message.setData(bundle);
                mHandler.sendMessage(message);
                Log.e("TUNG", "run():Thread:" + Thread.currentThread().getName());
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

    public void createThreadRunnable() {
        for (int i = 0; i < 10; i++) {
            Runnable worker = new BackgroundTask(i, mHandler);
            mExecutorService.execute(worker);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mExecutorService.shutdown();
    }
}
