package com.javpoblano.showcase.presenters;

import com.javpoblano.showcase.interfaces.ProductLinstener;
import com.javpoblano.showcase.models.inner.Product;

import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by javpoblano on 12/9/17.
 */

public class ProductPresenter {
    Realm realm;
    ProductLinstener listener;

    public ProductPresenter(ProductLinstener listener) {
        realm = Realm.getDefaultInstance();
        this.listener = listener;
    }

    public void createProduct(String nombre,String sku,double precio,int puntos)
    {
        realm.beginTransaction();
        Product product = realm.createObject(Product.class, UUID.randomUUID().toString());
        product.setName(nombre);
        product.setSku(sku);
        product.setCost(precio);
        product.setPoints(puntos);
        realm.commitTransaction();
        //TO-DO falta validar el resultado de la transaccion
        listener.onProductCreated(true);
    }

    public void getProductos()
    {
        final RealmResults<Product> products =
                realm.where(Product.class).findAll();
        listener.onProductListLoad(products);
    }
}
