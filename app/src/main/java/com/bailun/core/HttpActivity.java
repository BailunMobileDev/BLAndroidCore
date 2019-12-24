package com.bailun.core;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.bailun.core.http.BLHttpDefine;
import com.bailun.core.http.BLHttpUtils;
import com.bailun.core.http.HttpCallback;
import com.bailun.core.http.HttpRequestParam;

public class HttpActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http);
        findViewById(R.id.btn_get_one).setOnClickListener(this);
        findViewById(R.id.btn_get_two).setOnClickListener(this);
        findViewById(R.id.btn_post).setOnClickListener(this);
        findViewById(R.id.btn_put).setOnClickListener(this);
        textView = findViewById(R.id.text);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_get_one) {
            HttpRequestParam param = new HttpRequestParam("http://47.107.146.9:8080/android/getInformation", BLHttpDefine.Method.GET);
            BLHttpUtils.getInstance().httpRequest(param, new HttpCallback() {
                @Override
                public void onSuccess(String t) {
                    textView.setText(t);
                }

                @Override
                public void onError(int errCode, String strErrMsg) {
                    textView.setText("错误码为" + errCode + "  错误信息为" + strErrMsg);
                }

                @Override
                public void onCompleted() {

                }
            });
        } else if (id == R.id.btn_get_two) {
            HttpRequestParam param = new HttpRequestParam("http://47.107.146.9:8080/android/getWithParams", BLHttpDefine.Method.GET);
            param.addQuery("name", "wangjing");
            param.addQuery("type", String.valueOf(1));
            BLHttpUtils.getInstance().httpRequest(param, new HttpCallback() {
                @Override
                public void onSuccess(String t) {
                    textView.setText(t);
                }

                @Override
                public void onError(int errCode, String strErrMsg) {
                    textView.setText("错误码为" + errCode + "  错误信息为" + strErrMsg);
                }

                @Override
                public void onCompleted() {

                }
            });
        } else if (id == R.id.btn_post) {
            HttpRequestParam param = new HttpRequestParam("http://47.107.146.9:8080/android/postInformation", BLHttpDefine.Method.POST);
            param.setBody("{ \"age\": 10, \"name\": \"wangjing\", \"phone\": \"181****9312\", \"sex\": 0}");
            BLHttpUtils.getInstance().httpRequest(param, new HttpCallback() {
                @Override
                public void onSuccess(String t) {
                    textView.setText(t);
                }

                @Override
                public void onError(int errCode, String strErrMsg) {
                    textView.setText("错误码为" + errCode + "  错误信息为" + strErrMsg);
                }

                @Override
                public void onCompleted() {

                }
            });

        } else if (id == R.id.btn_put) {
            HttpRequestParam param = new HttpRequestParam("http://47.107.146.9:8080/android/putInformation", BLHttpDefine.Method.PUT);
            param.setBody("{ \"age\": 10, \"name\": \"wangjing\", \"phone\": \"181****9312\", \"sex\": 0}");
            BLHttpUtils.getInstance().httpRequest(param, new HttpCallback() {
                @Override
                public void onSuccess(String t) {
                    textView.setText(t);
                }

                @Override
                public void onError(int errCode, String strErrMsg) {
                    textView.setText("错误码为" + errCode + "  错误信息为" + strErrMsg);
                }

                @Override
                public void onCompleted() {

                }
            });
        }
    }
}
