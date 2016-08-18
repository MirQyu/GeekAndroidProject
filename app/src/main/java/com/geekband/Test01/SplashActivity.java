package com.geekband.Test01;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by MirQ on 16/7/5.
 */
public class SplashActivity extends AppCompatActivity {

    private static String TAG = SplashActivity.class.getSimpleName();

    public static final String TITLE = "title";

    public static final String USER_INFO = "userInfo";

    public static final int REQUEST_CODE = 9999;

    private TextView mTextView;

    Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mTextView = (TextView) findViewById(R.id.title_text_view);
        final String title = mTextView.getText().toString();
        // 延迟自动跳转Activity
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // 跳转到MainActivity
                UserInfo userInfo = new UserInfo("俊浩", 12);
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                intent.putExtra(TITLE, title);
                // 传递对象
                intent.putExtra(USER_INFO, userInfo);
                // 启动
                startActivityForResult(intent, REQUEST_CODE, null);
            }
        }, 1300);

    }

    // 此方法 与 startActivityForResult 是一对
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i(TAG, "requestCode: " + requestCode + ", resultCode: " + resultCode);

        if (requestCode == REQUEST_CODE && resultCode == MainActivity.RESULT_CODE) {
            if (data != null) {
                String title = data.getStringExtra(TITLE);
                mTextView.setText(title);
            }
        }
    }
}
