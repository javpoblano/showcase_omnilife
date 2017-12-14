package com.javpoblano.showcase.models.inner;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by javpoblano on 12/9/17.
 */

public class Product extends RealmObject{
    @PrimaryKey
    private String id;
    private String name;
    private String sku;
    private double cost;
    private int points;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
