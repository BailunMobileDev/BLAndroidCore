package com.bailun.core.http;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by kingpang on 2018/9/30.
 */

public class HttpRequestParam {
    private String m_strUrl = null;
    private int m_nMethod = -1; //http的访问方式

    private BodyParam m_ls4Body = null;
    private Map<String, String> m_map4query = null; //http的访问query的属性参数
    private Map<String, String> m_map4verify = null; //http的访问效验的属性参数 key：value

    private int m_nTimeout = 0;
    private boolean m_bRequestFinished = false;
    private boolean m_bSyncMsg = false;
    private int m_nMaxRequestLen = 0;

    public HttpRequestParam(String url, int method) {
        this.m_strUrl = url;
        this.m_nMethod = method;
    }

    public void addVerify(String key, String value) {
        if (null == m_map4verify) {
            m_map4verify = new HashMap<>();
        }

        if (null != m_map4verify) {
            m_map4verify.put(key, value);
        }
    }

    public void addQuery(String key, String value) {
        if (null == m_map4query) {
            m_map4query = new HashMap<>();
        }
        m_map4query.put(key, value);
    }

    public void setBody(String strContent) {
        m_ls4Body = new BodyParam("application/json", strContent);
    }

    public void setBody(byte[] content) {
        m_ls4Body = new BodyParam("multipart/form-data", content);
    }

    public void setBody(File content){
        m_ls4Body = new BodyParam("multipart/form-data", content);
    }

    public void setTimeout(int timeout) {
        this.m_nTimeout = timeout;
    }

    public void setRequestFinishResult(boolean bFinished) {
        m_bRequestFinished = bFinished;
    }

    public void setSyncMsg() {
        this.m_bSyncMsg = true;
    }

    int getRequestMethod() {
        return m_nMethod;
    }

    int getTimeout() {
        return this.m_nTimeout;
    }

    String getUrl() {
        return this.m_strUrl;
    }

    boolean isSyncMsg() {
        return this.m_bSyncMsg;
    }

    public boolean isRequestFinished() {
        return m_bRequestFinished;
    }

    BodyParam getM_ls4Body() {
        return m_ls4Body;
    }

    Map<String, String> getM_map4query() {
        return m_map4query;
    }

    Map<String, String> getM_map4verify() {
        return m_map4verify;
    }
}
