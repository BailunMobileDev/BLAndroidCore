package com.bailun.core.http;


import java.io.File;

public class BodyParam {
    String strType;
    Object object;

    BodyParam(String strType, String value) {
        this.strType = strType;
        this.object = value;
    }

    BodyParam(String strType, byte[] object) {
        this.strType = strType;
        this.object = object;
    }

    public BodyParam(String strType, File object) {
        this.strType = strType;
        this.object = object;
    }

    public String getStrType() {
        return strType;
    }

    public void setStrType(String strType) {
        this.strType = strType;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
