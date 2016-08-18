package com.geekband.Test01;

import android.content.Intent;
import android.content.res.XmlResourceParser;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.geekband.Test01.xml.SAXParseHandler;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final int RESULT_CODE = 1234;

    private static final String TAG = MainActivity.class.getSimpleName();

    private LayoutInflater mLayoutInflater;

    private EditText mEditText;

    private Button mListViewButton;

    private Button mTestViewButton;

    private Button mTestFragmentButton;
    private Button mHandleButton;
    private Button mSqliteButton;
    private Button mNetworkButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate");

        setContentView(R.layout.activity_main);

//        mLayoutInflater = getLayoutInflater();
        mLayoutInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
//        mLayoutInflater = LayoutInflater.from(MainActivity.this);

        initViews();
        handleIntentData();

        try {
            testSAXParse();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * parse file by SAX
     */
    private void testSAXParse() throws ParserConfigurationException, SAXException, IOException {

        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        SAXParser saxParser = saxParserFactory.newSAXParser();
        XMLReader xmlReader = saxParser.getXMLReader();

        SAXParseHandler saxParseHandler = new SAXParseHandler();
        xmlReader.setContentHandler(saxParseHandler);

        InputStream inputStream = getResources().openRawResource(R.raw.test);

        InputSource inputSource = new InputSource(inputStream);

        xmlReader.parse(inputSource);



        // PULL
        XmlResourceParser xmlResourceParser = getResources().getXml(R.xml.test);

        try {
            while (xmlResourceParser.getEventType() != XmlResourceParser.END_DOCUMENT) {

            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
    }


    private void initViews() {
        mEditText = (EditText) findViewById(R.id.editText);
        mListViewButton = (Button) findViewById(R.id.list_view_button);
        mTestViewButton = (Button) findViewById(R.id.test_view_button);
        mTestFragmentButton = (Button) findViewById(R.id.test_fragment_button);
        mHandleButton = (Button) findViewById(R.id.handle_button);
        mSqliteButton = (Button) findViewById(R.id.sqlite_button);
        mNetworkButton = (Button) findViewById(R.id.network_button);

        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.i(TAG, "s: " + s.toString() + ", start: " + start +
                        ", count: " + count + ", after: " + after);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.i(TAG, "s: " + s.toString() + ", start: " + start +
                        ", before: " + before + ", count: " + count);
            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.i(TAG, "Editable: " + s);
                if (s.toString().length() > 5) {
                    Toast.makeText(MainActivity.this, "啊 救命,超过5的字了 " + s.length(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        mListViewButton.setOnClickListener(this);
        mTestViewButton.setOnClickListener(this);
        mTestFragmentButton.setOnClickListener(this);
        mHandleButton.setOnClickListener(this);
        mSqliteButton.setOnClickListener(this);
        mNetworkButton.setOnClickListener(this);

    }


    private void handleIntentData() {
        Intent intent = getIntent();
        if (intent != null) {
            String title = intent.getStringExtra(SplashActivity.TITLE);
            UserInfo userInfo = (UserInfo) intent.getSerializableExtra(SplashActivity.USER_INFO);
            setTitle("名字是: " + userInfo.getUserName());

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.list_view_button:
                //Toast.makeText(MainActivity.this, "击中我了", Toast.LENGTH_SHORT).show();
                // 回送礼物
//                Intent intent = new Intent();
//                intent.putExtra(SplashActivity.TITLE, "我是主页, 我送礼回来了");
//                setResult(RESULT_CODE, intent);
//                finish()
                Intent intent = new Intent(MainActivity.this, ListViewDemoActivity.class);
                startActivity(intent);
                break;
            case R.id.test_view_button:
                startActivity(new Intent(MainActivity.this, TestViewButtonActivity.class));
                break;
            case R.id.test_fragment_button:
                startActivity(new Intent(MainActivity.this, TestFragmentActivity.class));
                break;
            case R.id.handle_button:
                startActivity(new Intent(MainActivity.this, HandleButtonActivity.class));
                break;
            case R.id.sqlite_button:
                startActivity(new Intent(MainActivity.this, DatabaseButtonActivity.class));
                break;
            case R.id.network_button:
                startActivity(new Intent(MainActivity.this, NetworkActivity.class));
                break;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart");
    }
}
