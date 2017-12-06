package com.javpoblano.showcase.models.ws;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by javpoblano on 12/6/17.
 */

public class LoginResponseData {
    @SerializedName("nombre")
    @Expose
    private String name;

    @SerializedName("descuento")
    @Expose
    private int discount;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }
}
