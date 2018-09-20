package com.example.tranvantungit.multithreadallexample.boundservice;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Messenger;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.tranvantungit.multithreadallexample.R;

public class BoundServiceActivity extends AppCompatActivity {
    Button runService;
    TextView result;
    String message;
    private MyService mBoundService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bound_service);
    }

    private final ServiceConnection mServiceConn =
            new ServiceConnection() {
                @Override
                public void onServiceConnected(ComponentName name, IBinder service) {
                    MyService.ServiceBinder serviceBinder = (MyService.ServiceBinder) service;
                    mBoundService = serviceBinder.getService();
                }

                @Override
                public void onServiceDisconnected(ComponentName name) {
                    if (mBoundService != null) {
                        mBoundService = null;
                    }
                }
            };



    @Override
    protected void onStart() {
        super.onStart();
        Intent serviceIntent = new Intent(this, MyService.class);
        bindService(serviceIntent, mServiceConn, Context.BIND_AUTO_CREATE);
    }
}
