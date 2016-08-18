package com.geekband.Test01.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.geekband.Test01.R;

/**
 * Created by MirQ on 16/7/14.
 */
public class TestFragment extends Fragment{

    public final static String TAG = TestFragment.class.getSimpleName();
    public static final String INTEGRITY_NAME = "integrity name";
    public static final String INTEGRITY_NUMBER = "integrity number";


    public static TestFragment newInstance(String name, String number) {
        TestFragment testFragment = new TestFragment();
        Bundle bundle = new Bundle();
        bundle.putString(INTEGRITY_NAME, name);
        bundle.putString(INTEGRITY_NUMBER, number);
        testFragment.setArguments(bundle);
        return testFragment;
    }

    public TestFragment() {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.i(TAG, "onAttach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView");

        View view = inflater.inflate(R.layout.fragment_test, container, false);

        TextView nameTextView = (TextView) view.findViewById(R.id.name_text_view);
        Bundle bundle = getArguments();
        if (bundle != null) {
            nameTextView.setText(bundle.getString(INTEGRITY_NAME));
        } else {
            nameTextView.setText("NullPointer");
        }


        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i(TAG, "onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i(TAG, "onStart");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(TAG, "onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i(TAG, "onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i(TAG, "onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.i(TAG, "onDetach");
    }
}
