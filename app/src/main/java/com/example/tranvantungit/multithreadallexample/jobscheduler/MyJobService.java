package com.example.tranvantungit.multithreadallexample.jobscheduler;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class MyJobService extends JobService {
    @Override
    public boolean onStartJob(final JobParameters params) {
        Log.e("TUNGLOG", "onStartJob + " + params.getJobId() + " + Thread: " + Thread.currentThread().getName());
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.e("TUNGLOG", "Runnable:run" + "Thread: " + Thread.currentThread().getName());
                Intent intent = new Intent("ServiceMessage");
                intent.putExtra("MESSAGE_KEY", "This is message from JobService");

                LocalBroadcastManager.getInstance(getApplicationContext())
                        .sendBroadcast(intent);

                jobFinished(params, false);
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        Log.e("TUNGLOG", "onStopJob + " + params.getJobId());
        return false;
    }
}
