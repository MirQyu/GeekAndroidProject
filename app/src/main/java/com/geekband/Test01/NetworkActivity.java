package com.geekband.Test01;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by MirQ on 16/8/16.
 */
public class NetworkActivity extends AppCompatActivity implements View.OnClickListener {

    public static final int WHAT = 222;
    private EditText mEditText;
    private TextView mTextView;
    private Button mButton;

    public Handler mHandler;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network);
        findViews();
        mButton.setOnClickListener(this);

        if (mHandler == null) {
            mHandler = new InnerHandler(new WeakReference<>(this));
        }


    }

    static class InnerHandler extends Handler {
        private WeakReference<NetworkActivity> wr;

        public InnerHandler(WeakReference<NetworkActivity> wr) {
            this.wr = wr;
        }

        @Override
        public void handleMessage(Message msg) {
            if (msg.what == WHAT) {
                String content = (String)msg.obj;
                NetworkActivity context = wr.get();
                context.setTextView(content);
            }
        }
    }

    private String requestDataByAsyncTask(String urlString) {

        try {
            URL url = new URL(urlString);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(30000);
            connection.setRequestMethod("GET");

            connection.connect();
            int responseCode = connection.getResponseCode();
            //String responseMessage = connection.getResponseMessage();

            InputStream inputStream = connection.getInputStream();

            Reader reader = new InputStreamReader(inputStream);
            char[] buffer = new char[1024];
            reader.read(buffer);
             return responseCode + new String(buffer);

            //mTextView.setText(content);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }

    private void requestDataByHandler(final String urlString) {

        //final String s = urlString;
        Thread thread = new Thread(
                new Runnable() {
                    public void run() {
                        try {
                            URL url = new URL(urlString);

                            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                            connection.setConnectTimeout(30000);
                            connection.setRequestMethod("GET");

                            connection.connect();
                            int responseCode = connection.getResponseCode();
                            //String responseMessage = connection.getResponseMessage();

                            InputStream inputStream = connection.getInputStream();

                            Reader reader = new InputStreamReader(inputStream);
                            char[] buffer = new char[1024];
                            reader.read(buffer);
                            String content = new String(buffer) + responseCode;

                            Message msg = mHandler.obtainMessage(WHAT);
                            msg.obj = content;
                            mHandler.sendMessage(msg);
                            //mTextView.setText(content);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
        thread.start();

    }

    private void findViews() {
        mEditText = (EditText) findViewById(R.id.editText);
        mTextView = (TextView) findViewById(R.id.textView);
        mButton = (Button) findViewById(R.id.button);
    }

    private String getEditTextUrl() {
        return mEditText != null ? mEditText.getText().toString() : "";
    }

    public void setTextView(String content) {
        if (!TextUtils.isEmpty(content))
            mTextView.setText(content);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case    R.id.button:
                String url = "http://" + getEditTextUrl();
                // request network data

                // handler method
                //requestDataByHandler(url);

                // Async method
                new RequestNetworkDataTask().execute(url);
                break;
        }
    }

    class RequestNetworkDataTask extends AsyncTask<String, Integer, String> {

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected String doInBackground(String... params) {
            // 异步
            return requestDataByAsyncTask(params[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            mTextView.setText(s);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }
    }
}
