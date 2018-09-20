package com.example.tranvantungit.multithreadallexample.boundservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class MyService extends Service {
    private final Binder mBinder = new ServiceBinder();
    BoundServiceListener mListener;

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    class ServiceBinder extends Binder {
        MyService getService() {
            return MyService.this;
        }

        public void setListener(BoundServiceListener listener) {
            mListener = listener;

        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    public String getValue() {
        return "";
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    public interface BoundServiceListener {
        void sendProgress(double progress);
    }
}
