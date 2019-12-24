package com.bailun.core.http;

/**
 * create by wangjing on 2019/12/6 0006
 * description: 接口
 */
public interface HttpUtils {
    void syncHttp(HttpRequestParam httpRequestParam, final HttpCallback callback);
    void asynHttp(HttpRequestParam httpRequestParam, final HttpCallback callback);
}
