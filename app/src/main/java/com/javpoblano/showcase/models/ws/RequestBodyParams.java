package com.javpoblano.showcase.models.ws;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by javpoblano on 12/6/17.
 */

public class RequestBodyParams {
    @SerializedName("eo")
    @Expose
    private String empresario;

    @SerializedName("password")
    @Expose
    private String password;

    @SerializedName("pais")
    @Expose
    private String country;

    public String getEmpresario() {
        return empresario;
    }

    public void setEmpresario(String empresario) {
        this.empresario = empresario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
