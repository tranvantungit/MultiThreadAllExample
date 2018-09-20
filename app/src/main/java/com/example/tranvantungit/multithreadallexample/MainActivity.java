package com.example.tranvantungit.multithreadallexample;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.tranvantungit.multithreadallexample.asynctask.AsynctaskActivity;
import com.example.tranvantungit.multithreadallexample.jobscheduler.JobSchedulerActivity;
import com.example.tranvantungit.multithreadallexample.loader.LoaderActivity;
import com.example.tranvantungit.multithreadallexample.service.ServiceActivity;
import com.example.tranvantungit.multithreadallexample.thread.ThreadActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button asyncTaskActivity, threadActivity, serviceActivity, jobActivity, loaderActivity, boundService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        asyncTaskActivity = findViewById(R.id.asynctask);
        threadActivity = findViewById(R.id.thread);
        serviceActivity = findViewById(R.id.service);
        jobActivity = findViewById(R.id.job);
        loaderActivity = findViewById(R.id.loader);
        boundService = findViewById(R.id.boundService);

        asyncTaskActivity.setOnClickListener(this);
        threadActivity.setOnClickListener(this);
        serviceActivity.setOnClickListener(this);
        jobActivity.setOnClickListener(this);
        loaderActivity.setOnClickListener(this);
        boundService.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.asynctask:
                startActivity(new Intent(this, AsynctaskActivity.class));
                break;
            case R.id.thread:
                startActivity(new Intent(this, ThreadActivity.class));
                break;
            case R.id.job:
                startActivity(new Intent(this, JobSchedulerActivity.class));
                break;
            case R.id.loader:
                startActivity(new Intent(this, LoaderActivity.class));
                break;
            case R.id.service:
                startActivity(new Intent(this, ServiceActivity.class));
                break;
            case R.id.boundService:
                //startActivity(new Intent(this, ));
                break;

        }
    }
}
