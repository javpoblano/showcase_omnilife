package com.javpoblano.showcase.interfaces;

import com.javpoblano.showcase.models.inner.Product;

import io.realm.RealmResults;

/**
 * Created by javpoblano on 12/9/17.
 */

public interface ProductLinstener {
    void onProductListLoad(RealmResults<Product> resultados);
    void onProductCreated(boolean res);
    void onItemClick(Product product);
}
