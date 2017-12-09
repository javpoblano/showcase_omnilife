package com.javpoblano.showcase.activities;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;

import com.javpoblano.showcase.R;
import com.javpoblano.showcase.adapters.ProductAdapter;
import com.javpoblano.showcase.interfaces.ProductLinstener;
import com.javpoblano.showcase.models.inner.Product;
import com.javpoblano.showcase.presenters.ProductPresenter;

import io.realm.RealmResults;

public class ProductListActivity extends AppCompatActivity implements ProductLinstener{
    ProductPresenter presenter;
    RecyclerView recyclerView;
    ProductAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        presenter = new ProductPresenter(this);
        recyclerView = (RecyclerView)findViewById(R.id.rv);
        presenter.getProductos();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showNewProductDialog();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onProductListLoad(RealmResults<Product> resultados) {
        adapter = new ProductAdapter(this,this,resultados);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }

    @Override
    public void onProductCreated(boolean res) {
        presenter.getProductos();
    }

    @Override
    public void onItemClick(Product product) {
        //TO-DO
    }

    public void showNewProductDialog()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_product,null);
        builder.setView(dialogView);

        final TextInputEditText name = (TextInputEditText) dialogView.findViewById(R.id.et_nombre);
        final TextInputEditText sku = (TextInputEditText) dialogView.findViewById(R.id.et_sku);
        final TextInputEditText costo = (TextInputEditText) dialogView.findViewById(R.id.et_precio);
        final TextInputEditText punto = (TextInputEditText) dialogView.findViewById(R.id.et_puntos);

        builder.setNegativeButton("Cancelar",null);
        builder.setPositiveButton("Crear", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                presenter.createProduct(name.getText().toString(),
                        sku.getText().toString(),
                        Double.valueOf(costo.getText().toString()),
                        Integer.parseInt(punto.getText().toString()));
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
