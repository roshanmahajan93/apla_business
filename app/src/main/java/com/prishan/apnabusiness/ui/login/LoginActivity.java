package com.prishan.apnabusiness.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.prishan.apnabusiness.Application;
import com.prishan.apnabusiness.data.model.requestmodels.RmLoginRequest;
import com.prishan.apnabusiness.data.network.NetworkOperations;
import com.prishan.apnabusiness.data.network.WebServiceCalls;
import com.prishan.apnabusiness.ui.MainActivity;
import com.prishan.apnabusiness.R;
import com.prishan.apnabusiness.util.Constant;
import com.prishan.apnabusiness.util.SharedPrefrenceObj;
import com.prishan.apnabusiness.util.commonui.PopMessage;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    private void checkLogin(final String username, final String password) {
        RmLoginRequest loginRequest = new RmLoginRequest(username, password);
        WebServiceCalls.Login.check(LoginActivity.this, loginRequest, new NetworkOperations(true) {
            @Override
            public void onSuccess(Bundle msg) {
                SharedPrefrenceObj.setSharedValue(
                        LoginActivity.this,
                        Constant.SharedPrefrence.User.USER,
                        (msg.getSerializable(Constant.SharedPrefrence.User.USER)).toString()
                );
                SharedPrefrenceObj.setSharedValue(
                        LoginActivity.this,
                        Constant.SharedPrefrence.User.USERNAME,
                        username
                );
                SharedPrefrenceObj.setSharedValue(
                        LoginActivity.this,
                        Constant.SharedPrefrence.User.PASSWORD,
                        password
                );
                initiateNextActivity();
            }

            @Override
            public void onFailure(Bundle msg) {
                String message = msg.getString(Constant.MESSAGE);
                System.err.println(message);
                PopMessage.makeLongToast(LoginActivity.this, message);
                /*if (message.equalsIgnoreCase(Constant.INVALID_SESSION_MESSAGE)) {
                    Application.goToSessionActivity(LoginActivity.this);
                }*/
            }
        });
    }

    private void initiateNextActivity() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}