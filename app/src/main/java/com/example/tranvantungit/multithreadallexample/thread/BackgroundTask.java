package com.example.tranvantungit.multithreadallexample.thread;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class BackgroundTask implements Runnable {
    private int threadNumber;
    private Handler mHandler;

    BackgroundTask(int threadNumber, Handler handler) {
        this.threadNumber = threadNumber;
        mHandler = handler;
    }

    @Override
    public void run() {
        Log.e("TUNG", "start Thread number: " + threadNumber);
        Log.e("TUNG", "BackgroundTask:running + Thread: " + Thread.currentThread().getName());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Message message = new Message();
        Bundle bundle = new Bundle();
        bundle.putString("MESSAGE_KEY", "This is message from Thread: " + threadNumber);
        message.setData(bundle);
        mHandler.sendMessage(message);
        Log.e("TUNG", "BackgroundTask:complete:ThreadNumber:" + threadNumber);
    }
}
