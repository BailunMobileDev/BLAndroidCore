package com.bailun.core.http;

/**
 * create by wangjing on 2019/12/6 0006
 * description:
 */
public class BLHttpUtils {

    private static BLHttpUtils m_Instance = null;

    public static BLHttpUtils getInstance() {
        if (null == m_Instance) {
            synchronized (BLHttpUtils.class) {
                if (null == m_Instance) {
                    m_Instance = new BLHttpUtils();
                }
            }
        }
        return m_Instance;
    }

    private BLHttpUtils(){

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
