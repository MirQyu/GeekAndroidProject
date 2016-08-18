package com.geekband.Test01;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by MirQ on 16/7/6.
 */
public class ListViewDemoActivity extends AppCompatActivity implements View.OnClickListener{


    public static final String DATA_COUNTS = "DataCounts";
    public static final String LIST_VIEW_DATA = "list_view_data";
    private ListView mPhoneBookListView;

    private static final String TAG = ListViewDemoActivity.class.getSimpleName();

    private List<UserInfo> mUserInfoList;

    private PhoneBookAdapter mPhoneBookAdapter;

    private int mDataCounts = 10;

    private Button mCountsConfirmButton;

    private EditText mDataCountsEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "ListView 已加载!!!!!!!!!");
        setContentView(R.layout.activity_listview_demo);

        findViews();

        setData();

        setItemListener();

        testFile();
    }

    private void testFile() {
        File file = new File(getFilesDir(), "test.txt");
        Log.i(TAG, "getFileDir: " + getFilesDir());
        try {
            boolean isSuccess =  file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.i(TAG, "ExternalStorageDirectory: " + Environment.getExternalStorageDirectory().getAbsolutePath());
        Log.i(TAG, "user data directory: " + Environment.getDataDirectory().getAbsolutePath());
        try {
            FileOutputStream fileOutputStream = openFileOutput("test2.txt", Context.MODE_PRIVATE);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }

    private void findViews() {
        mPhoneBookListView = (ListView) findViewById(R.id.list_item);
        mDataCountsEditText = (EditText) findViewById(R.id.data_counts_edit_text);
        mCountsConfirmButton = (Button) findViewById(R.id.counts_confirm_button);
    }

    private void setData() {
        mUserInfoList= new ArrayList<>();
        SharedPreferences sharedPreferences = getSharedPreferences(LIST_VIEW_DATA, MODE_PRIVATE);
        mDataCounts = sharedPreferences.getInt(DATA_COUNTS, 10);
        for (int index = 0; index < mDataCounts; index++) {
            mUserInfoList.add(new UserInfo("俊浩", 10));
        }

        mPhoneBookAdapter = new PhoneBookAdapter(ListViewDemoActivity.this, mUserInfoList);

        // 视图与数据的绑定
        mPhoneBookListView.setAdapter(mPhoneBookAdapter);
    }

    private void setItemListener() {
        mPhoneBookListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    //清除旧数据
                    mUserInfoList.clear();
                    mUserInfoList.add(new UserInfo("晓松",2));
                    mUserInfoList.add(new UserInfo("小李",36));
                    mUserInfoList.add(new UserInfo("潇潇",10));

                    mPhoneBookAdapter.refreshData(mUserInfoList);
                }

                Toast.makeText(ListViewDemoActivity.this, mUserInfoList.get(position).getUserName() + "被点击了,怎么办",
                            Toast.LENGTH_SHORT).show();
            }
        });
        mCountsConfirmButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.counts_confirm_button:
                mDataCounts = Integer.valueOf(mDataCountsEditText.getText().toString());
                refreshListView();

                saveData2Preference();
        }
    }

    private void saveData2Preference() {

        SharedPreferences sharedPreferences = getSharedPreferences(LIST_VIEW_DATA, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(DATA_COUNTS, mDataCounts);
        editor.apply();
    }

    private void refreshListView() {
        mUserInfoList.clear();
        for (int index = 0; index < mDataCounts; index++) {
            mUserInfoList.add(new UserInfo("俊浩", 10));
        }
        mPhoneBookAdapter.refreshData(mUserInfoList);
    }
}
