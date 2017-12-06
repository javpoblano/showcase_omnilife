package com.javpoblano.showcase.presenters;

import android.content.Context;

import com.javpoblano.showcase.R;
import com.javpoblano.showcase.api.OmnilifeWebService;
import com.javpoblano.showcase.interfaces.LoginPresenterInterface;
import com.javpoblano.showcase.models.ws.LoginRequestBody;
import com.javpoblano.showcase.models.ws.LoginResponse;
import com.javpoblano.showcase.models.ws.RequestBodyParams;
import com.javpoblano.showcase.utils.SharedPrefs;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by javpoblano on 12/6/17.
 */

public class LoginPresenter {
    Context context;
    SharedPrefs sharedPrefs;
    LoginResponse loginResponse;
    Retrofit retrofit;
    OmnilifeWebService omnilifeWebService;
    LoginPresenterInterface listener;


    public LoginPresenter(Context context,LoginPresenterInterface listener)
    {
        this.context = context;
        sharedPrefs = new SharedPrefs(context);
        this.listener=listener;
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

    public void attemptLogin(String user,String pass,String country)
    {
        LoginRequestBody loginRequestBody = new LoginRequestBody();
        RequestBodyParams params = new RequestBodyParams();
        params.setEmpresario(user);
        params.setPassword(pass);
        params.setCountry(country);
        loginRequestBody.setMethod("loginEo");
        loginRequestBody.setParams(params);

        Call<LoginResponse> login = omnilifeWebService.login(loginRequestBody);
        login.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response){
                listener.onLoadFinished(response.body());
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

            }
        });
    }

}
