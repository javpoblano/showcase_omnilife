package com.javpoblano.showcase.presenters;

import android.content.Context;
import android.widget.Toast;

import com.javpoblano.showcase.R;
import com.javpoblano.showcase.api.OmnilifeWebService;
import com.javpoblano.showcase.interfaces.LoginPresenterInterface;
import com.javpoblano.showcase.interfaces.MainLoadInterface;
import com.javpoblano.showcase.models.ws.EstadosBody;
import com.javpoblano.showcase.models.ws.EstadosBodyParams;
import com.javpoblano.showcase.models.ws.EstadosResponse;
import com.javpoblano.showcase.models.ws.LoginResponse;
import com.javpoblano.showcase.utils.SharedPrefs;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by javpoblano on 12/7/17.
 */

public class MainPresenter {
    Context context;
    SharedPrefs sharedPrefs;
    Retrofit retrofit;
    OmnilifeWebService omnilifeWebService;
    MainLoadInterface listener;

    public MainPresenter(Context context,MainLoadInterface listener) {
        this.context = context;
        this.listener = listener;
        sharedPrefs =  new SharedPrefs(context);
        initRetrofit();
    }

    public void initRetrofit()
    {
        retrofit = new Retrofit.Builder()
                .baseUrl(context.getResources().getString(R.string.server))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        omnilifeWebService = retrofit.create(OmnilifeWebService.class);
    }

    public void getEstados()
    {
        EstadosBody estadosBody = new EstadosBody();
        estadosBody.setMetodo("getEstados");
        EstadosBodyParams params = new EstadosBodyParams();
        params.setPais("MEX");
        estadosBody.setParams(params);

        Call<EstadosResponse> getEstados = omnilifeWebService.getEstados(estadosBody);
        getEstados.enqueue(new Callback<EstadosResponse>() {
            @Override
            public void onResponse(Call<EstadosResponse> call, Response<EstadosResponse> response) {
                if(response.isSuccessful())
                {
                    listener.onEstadosLoad(response.body());
                }
            }

            @Override
            public void onFailure(Call<EstadosResponse> call, Throwable t) {
                Toast.makeText(context, "No tengo internet", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
