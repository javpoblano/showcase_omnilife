package com.javpoblano.showcase.models.ws;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by javpoblano on 12/6/17.
 */

public class LoginRequestBody {
    @SerializedName("metodo")
    @Expose
    private String method;

    @SerializedName("params")
    @Expose
    private RequestBodyParams params;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public RequestBodyParams getParams() {
        return params;
    }

    public void setParams(RequestBodyParams params) {
        this.params = params;
    }
}
