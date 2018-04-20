package com.example.dogoodsoft_app.lessismore.test;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;

/**
 * Created by dogoodsoft-app on 2018/4/17.
 */

public class DownloadThread extends HandlerThread implements Handler.Callback{





    public DownloadThread(String name) {
        super(name);
    }

    @Override
    public boolean handleMessage(Message message) {
        return false;
    }

    @Override
    protected void onLooperPrepared() {
        super.onLooperPrepared();
    }

    @Override
    public boolean quitSafely() {
        return super.quitSafely();
    }
}
