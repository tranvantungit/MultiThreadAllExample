package com.example.tranvantungit.multithreadallexample.asynctask;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.tranvantungit.multithreadallexample.R;

import org.w3c.dom.Text;

import java.lang.ref.WeakReference;

public class AsynctaskActivity extends AppCompatActivity {
    Button runAsynctask;
    TextView resultText;
    TextView progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asynctask);

        runAsynctask = findViewById(R.id.runAsynctask);
        progress = findViewById(R.id.progress);
        resultText = findViewById(R.id.result);

        resultText.setText("");
        runAsynctask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultText.setText("");
                MyAsyncTask myAsyncTask = new MyAsyncTask(AsynctaskActivity.this);
                myAsyncTask.execute("Red", "Green", "Blue");
            }
        });
    }

    private static class MyAsyncTask extends AsyncTask<String, String, String> {
        WeakReference<AsynctaskActivity> activityWeakReference;

        MyAsyncTask(AsynctaskActivity asynctaskActivity) {
            activityWeakReference = new WeakReference<>(asynctaskActivity);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.e("TUNG", "onPreExecute:" + Thread.currentThread().getName());
        }

        @Override
        protected String doInBackground(String... strings) {
            AsynctaskActivity activity = activityWeakReference.get();
            StringBuilder builder = new StringBuilder();
            int i = 0;
            for (String obj : strings) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                builder.append(obj);
                activity.progress.setText(String.valueOf(i + 1));
                publishProgress(obj);
            }
            Log.e("TUNG", "doInBackground:" + Thread.currentThread().getName());
            activity.resultText.setText(Thread.currentThread().getName());
            return builder.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            AsynctaskActivity activity = activityWeakReference.get();
            activity.resultText.setText(s);
            Log.e("TUNG", "onPostExecute:" + Thread.currentThread().getName());
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            Log.e("TUNG", "onProgressUpdate:" + values[0]);
        }
    }

}
