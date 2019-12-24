package com.bailun.core.http;

import android.os.Handler;
import android.os.Looper;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * create by wangjing on 2019/12/6 0006
 * description:
 */
class OkHttpUtils implements HttpUtils {

    private OkHttpClient client = new OkHttpClient.Builder().build();

    private Handler handler = new Handler(Looper.getMainLooper());

    @Override
    public void syncHttp(HttpRequestParam httpRequestParam, HttpCallback callback) {
        Call call = client.newCall(paramToRequest(httpRequestParam));
        try {
            Response response = call.execute();
            callback.onSuccess(response.body().string());
        } catch (IOException e) {
            callback.onError(BLHttpDefine.ResponseCode.UNKNOWN, e.toString());
        } finally {
            callback.onCompleted();
        }
    }

    @Override
    public void asynHttp(HttpRequestParam httpRequestParam, final HttpCallback callback) {
        Call call = client.newCall(paramToRequest(httpRequestParam));

        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull final IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callback.onError(BLHttpDefine.ResponseCode.UNKNOWN, e.toString());
                        callback.onCompleted();
                    }
                });
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull final Response response) throws IOException {
                final String body = response.body().string();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callback.onSuccess(body);
                        callback.onCompleted();
                    }
                });
            }
        });
    }

    private static Request paramToRequest(HttpRequestParam param) {
        if (param.getUrl() == null || param.getUrl().equals("")) {
            throw new NullPointerException("url 不能为null或者\"\"");
        }
        RequestBody body = null;
        if (param.getM_ls4Body() != null) {
            body = RequestBody.create((String) param.getM_ls4Body().getObject(), MediaType.parse(param.getM_ls4Body().getStrType()));
        }
        HttpUrl.Builder urlBuilder = HttpUrl.parse(param.getUrl()).newBuilder();
        if (param.getM_map4query() != null) {
            for (Map.Entry<String, String> entry : param.getM_map4query().entrySet()) {
                urlBuilder.addQueryParameter(entry.getKey(), entry.getValue());
            }
        }
        Request.Builder builder = new Request.Builder().url(urlBuilder.build()).method(getMethodName(param.getRequestMethod()), body);
        if (param.getM_map4verify() != null) {
            for (Map.Entry<String, String> entry : param.getM_map4verify().entrySet()) {
                builder.addHeader(entry.getKey(), entry.getValue());
            }
        }
        return builder.build();
    }

    private static String getMethodName(int type) {
        String name = null;
        switch (type) {
            case BLHttpDefine.Method.POST:
                name = "POST";
                break;
            case BLHttpDefine.Method.PUT:
                name = "PUT";
                break;
            case BLHttpDefine.Method.HEAD:
                name = "HEAD";
                break;
            case BLHttpDefine.Method.DELETE:
                name = "DELETE";
                break;
            case BLHttpDefine.Method.GET:
            default:
                name = "GET";
                break;
        }
        return name;
    }
}
