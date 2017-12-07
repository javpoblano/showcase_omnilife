package com.javpoblano.showcase.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.javpoblano.showcase.R;
import com.javpoblano.showcase.interfaces.ListListener;
import com.javpoblano.showcase.models.ws.Estado;

import java.util.List;

/**
 * Created by javpoblano on 12/7/17.
 */

public class EstadosAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    ListListener listListener;
    List<Estado> list;

    public EstadosAdapter(ListListener listListener, List<Estado> list) {
        this.listListener = listListener;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_estado,parent,false);
        return new EstadoHolder(root);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Estado aux = list.get(position);
        ((EstadoHolder)holder).refresh(aux);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class EstadoHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView name;

        public ImageView img;

        public EstadoHolder(View v)
        {
            super(v);
            name = (TextView) v.findViewById(R.id.tv_name);
            img = (ImageView) v.findViewById(R.id.img);
            v.setOnClickListener(this);
        }

        public void refresh(Estado estado)
        {
            name.setText(estado.getDescripcion());
        }

        @Override
        public void onClick(View view) {
            int index = getLayoutPosition();
            listListener.onItemClick(list.get(index));
        }
    }
}
