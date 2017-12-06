package com.javpoblano.showcase.interfaces;

import com.javpoblano.showcase.models.ws.LoginResponse;

/**
 * Created by javpoblano on 12/6/17.
 */

public interface LoginPresenterInterface {
    void onLoadFinished(LoginResponse loginResponse);
}
