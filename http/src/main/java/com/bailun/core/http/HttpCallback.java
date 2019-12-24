package com.bailun.core.http;

/**
 * 用于回调Http请求.
 */
public interface HttpCallback {
    void onSuccess(String t);
    void onError(int errCode, String strErrMsg);
    void onCompleted();
}
