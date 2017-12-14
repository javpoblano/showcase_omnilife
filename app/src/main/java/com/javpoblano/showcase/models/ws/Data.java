package com.javpoblano.showcase.models.ws;

/**
 * Created by javpoblano on 12/7/17.
 */

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("estado")
    @Expose
    private List<Estado> estado = null;

    public List<Estado> getEstado() {
        return estado;
    }

    public void setEstado(List<Estado> estado) {
        this.estado = estado;
    }

}
