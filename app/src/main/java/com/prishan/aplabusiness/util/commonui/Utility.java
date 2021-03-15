package com.prishan.aplabusiness.util.commonui;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;

import com.prishan.aplabusiness.R;
import com.prishan.aplabusiness.data.network.retrofit.RFClient;
import com.prishan.aplabusiness.data.network.retrofit.RFInterface;


public class Utility {

    public static RFInterface getRetrofitInterface(String BASE_URL) {
        return RFClient.getClient(BASE_URL).create(RFInterface.class);
    }

    public static ProgressDialog createProgressDialog(Context context) {
        ProgressDialog dialog = new ProgressDialog(context);
        dialog.show();
        dialog.setCancelable(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.progressdialog);

        return dialog;
    }

}
