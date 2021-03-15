package com.prishan.aplabusiness;

import android.content.res.Configuration;
import android.util.Log;

import androidx.annotation.NonNull;

import com.prishan.aplabusiness.util.commonui.PopMessage;


public class Application extends android.app.Application {
    private static final String TAG = "Application";

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        PopMessage.makeLongToast(getApplicationContext(), "OnLowMemory: Please Check Device Memory");
        Log.d(TAG, "onLowMemory: Please Check Device Memory");
    }

}
