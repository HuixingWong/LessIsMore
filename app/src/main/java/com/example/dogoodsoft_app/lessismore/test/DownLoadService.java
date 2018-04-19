package com.example.dogoodsoft_app.lessismore.test;

import android.app.IntentService;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

/**
 * Created by dogoodsoft-app on 2018/4/17.
 */

public class DownLoadService extends IntentService{

    private static final String TAG = "DownloadService";

    public static final String DOWN_URL = "down_load_url";

    public static final int WHAT_DOWNLOAD_FINISHIED = 1;
    public static final int WHAT_DOWNLOAD_STARED = 2;

    private static Handler mUihandler;


    public DownLoadService() {
        super(TAG);
    }


    public static void setmUihandler(final  Handler handler){

       mUihandler = handler;
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {


        String url = intent.getStringExtra(DOWN_URL);
        if (!TextUtils.isEmpty(url)){

            sendMessageToMainThread(WHAT_DOWNLOAD_STARED, "\n "
                    + new Date(System.currentTimeMillis()).toString() + " 开始下载任务：\n" + url);

            Bitmap bitmap = DownloadFromUrl(url);
            if (bitmap != null){

                SystemClock.sleep(10000);
                sendMessageToMainThread(WHAT_DOWNLOAD_FINISHIED,bitmap);

            }

        }

    }

    //发消息到住县城

    private void  sendMessageToMainThread(final   int id,final Object o){


        if (mUihandler != null){

            mUihandler.sendMessage(mUihandler.obtainMessage(id,o));


        }


    }


    //dowloadpic

    private Bitmap DownloadFromUrl(String url) {

        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            BufferedInputStream inputStream =
                    new BufferedInputStream(connection.getInputStream());
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            connection.disconnect();
            inputStream.close();
            return bitmap;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }



    }





}
