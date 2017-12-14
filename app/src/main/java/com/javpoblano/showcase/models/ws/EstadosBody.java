package com.javpoblano.showcase.models.ws;

/**
 * Created by javpoblano on 12/7/17.
 */

public class EstadosBody {
    private String metodo;
    private EstadosBodyParams params;

    public String getMetodo() {
        return metodo;
    }

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }

    public EstadosBodyParams getParams() {
        return params;
    }

    public void setParams(EstadosBodyParams params) {
        this.params = params;
    }
}
