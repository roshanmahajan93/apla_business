package com.prishan.aplabusiness.data.network;

import android.app.ProgressDialog;
import android.content.Context;

import com.prishan.aplabusiness.data.network.callback.NetworkCallback;
import com.prishan.aplabusiness.util.commonui.Utility;


public abstract class NetworkOperations implements NetworkCallback {

    private Boolean isVisible;
    private ProgressDialog progressDialog;

    public NetworkOperations(Boolean isVisible) {
        this.isVisible = isVisible;
    }

    public void onStart(Context context, String msg) {
        if (isVisible) {
            progressDialog = Utility.createProgressDialog(context);
        }
    }

    public void onComplete() {
        if (isVisible) {
            progressDialog.dismiss();
        }
    }
}
