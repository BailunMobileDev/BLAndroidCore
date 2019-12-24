package com.bailun.core.http;

/**
 * create by wangjing on 2019/12/6 0006
 * description:
 */
public class NetworkTransmissionUtils {

    private static NetworkTransmissionUtils m_Instance = null;

    public static NetworkTransmissionUtils getInstance() {
        if (null == m_Instance) {
            synchronized (NetworkTransmissionUtils.class) {
                if (null == m_Instance) {
                    m_Instance = new NetworkTransmissionUtils();
                }
            }
        }
        return m_Instance;
    }

    private NetworkTransmissionUtils(){

    }

    private HttpUtils httpUtils = new OkHttpUtils();

    public void setHttpUtils(HttpUtils httpUtils) {
        this.httpUtils = httpUtils;
    }

    public void httpRequest(final HttpRequestParam httpRequestParam, final HttpCallback callback) {
        if (httpRequestParam != null) {
            if (httpRequestParam.isSyncMsg()) {
                httpUtils.syncHttp(httpRequestParam, callback);
            } else {
                httpUtils.asynHttp(httpRequestParam, callback);
            }
        }
    }
}
