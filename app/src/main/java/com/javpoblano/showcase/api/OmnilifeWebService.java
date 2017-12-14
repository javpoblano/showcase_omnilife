package com.javpoblano.showcase.api;

import com.javpoblano.showcase.models.ws.EstadosBody;
import com.javpoblano.showcase.models.ws.EstadosResponse;
import com.javpoblano.showcase.models.ws.LoginRequestBody;
import com.javpoblano.showcase.models.ws.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * Created by javpoblano on 12/6/17.
 */

public interface OmnilifeWebService {
    @POST("restShopping/process/method")
    Call<LoginResponse> login (@Body LoginRequestBody body);

    @POST("restShopping/process/method")
    Call<EstadosResponse> getEstados (@Body EstadosBody body);
}
