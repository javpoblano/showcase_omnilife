package com.javpoblano.showcase.models.ws;

/**
 * Created by javpoblano on 12/7/17.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Estado {

    @SerializedName("clv_estado")
    @Expose
    private String clvEstado;
    @SerializedName("descripcion")
    @Expose
    private String descripcion;

    public String getClvEstado() {
        return clvEstado;
    }

    public void setClvEstado(String clvEstado) {
        this.clvEstado = clvEstado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
