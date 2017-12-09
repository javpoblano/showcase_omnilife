package com.javpoblano.showcase.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.javpoblano.showcase.R;
import com.javpoblano.showcase.interfaces.ListListener;
import com.javpoblano.showcase.interfaces.ProductLinstener;
import com.javpoblano.showcase.models.inner.Product;
import com.javpoblano.showcase.models.ws.Estado;
import com.squareup.picasso.Picasso;

import java.util.List;

import io.realm.RealmResults;

/**
 * Created by javpoblano on 12/9/17.
 */

public class ProductAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    ProductLinstener listListener;
    RealmResults<Product> list;
    Context context;

    public ProductAdapter(Context context,ProductLinstener listListener, RealmResults<Product> list) {
        this.context=context;
        this.listListener = listListener;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_product_list,parent,false);
        return new ProductHolder(root);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Product aux = list.get(position);
        ((ProductHolder)holder).refresh(aux);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ProductHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView name,sku,precio,puntos;
        public ImageView img;

        public ProductHolder(View v)
        {
            super(v);
            name = (TextView) v.findViewById(R.id.tv_nombre);
            sku = (TextView) v.findViewById(R.id.tv_sku);
            precio = (TextView) v.findViewById(R.id.tv_costo);
            puntos = (TextView) v.findViewById(R.id.tv_puntos);
            img = (ImageView) v.findViewById(R.id.img);
            v.setOnClickListener(this);
        }

        public void refresh(Product product)
        {
            name.setText(product.getName());
            sku.setText(product.getSku());
            precio.setText("Costo: $"+product.getCost());
            puntos.setText("Puntos: "+product.getPoints());
            Picasso.with(context).load("https://blog.alante.org/hs-fs/hubfs/NOVAKID-OMNILIFE.jpg?t=1509996200651&width=620&height=365&name=NOVAKID-OMNILIFE.jpg")
                    //.resize()
                .into(img);
        }

        @Override
        public void onClick(View view) {
            int index = getLayoutPosition();
            listListener.onItemClick(list.get(index));
        }
    }
}
