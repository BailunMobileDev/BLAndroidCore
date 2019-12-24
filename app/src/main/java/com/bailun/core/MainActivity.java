package com.bailun.core;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.bailun.core.http.HttpCallback;
import com.bailun.core.http.HttpRequestParam;
import com.bailun.core.http.BLHttpDefine;
import com.bailun.core.http.BLHttpUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView textView = findViewById(R.id.text);
        HttpRequestParam param = new HttpRequestParam("https://www.baidu.com", BLHttpDefine.Method.GET);
        BLHttpUtils.getInstance().httpRequest(param, new HttpCallback() {
            @Override
            public void onSuccess(final String t) {
                textView.setText(t);
            }

            @Override
            public void onError(int errCode, final String strErrMsg) {
                textView.setText(strErrMsg);
            }

            @Override
            public void onCompleted() {

            }
        });
    }
}
