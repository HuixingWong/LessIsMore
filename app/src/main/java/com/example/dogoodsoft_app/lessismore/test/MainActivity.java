package com.example.dogoodsoft_app.lessismore.test;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dogoodsoft_app.lessismore.R;
import com.example.dogoodsoft_app.lessismore.base.BaseActivity;

import java.util.Arrays;
import java.util.BitSet;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by dogoodsoft-app on 2018/3/26.
 */

public class MainActivity extends BaseActivity implements Handler.Callback{

    private NameViewModel mModel;
    @BindView(R.id.name_tv)
    Button nameTv;
    private static final String TAG = "ONLYFUCK";

    @BindView(R.id.show_img)
    ImageView imageView;

    private ThreadLocal<Boolean> threadLocal = new ThreadLocal<>();

    Handler handler1;
    Handler handler2;

    private List<String> urlList = Arrays.asList("https://ws1.sinaimg.cn/large/610dc034ly1fgepc1lpvfj20u011i0wv.jpg",
            "https://ws1.sinaimg.cn/large/d23c7564ly1fg6qckyqxkj20u00zmaf1.jpg",
            "https://ws1.sinaimg.cn/large/610dc034ly1fgchgnfn7dj20u00uvgnj.jpg");    //美女图片地址
    int mFinishCount;

    private  Handler handler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 200){

                    Log.e(TAG,"obj is :"+msg.obj);

                    Message message = Message.obtain();

                message.what = 300;
                message.obj = "what three fuck";
                handler2.sendMessage(message);

            }

        }
    };

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        DownLoadService.setmUihandler(new Handler(this));

        mModel = ViewModelProviders.of(this).get(NameViewModel.class);

        final  Observer<String> nameObserver = new Observer<String>(){

            @Override
            public void onChanged(@Nullable String s) {
                nameTv.setText(s);
            }

        };


        final Observer<Bitmap> bitmapObserver = new Observer<Bitmap>() {

            @Override
            public void onChanged(@Nullable Bitmap bitmap) {
                imageView.setImageBitmap(bitmap);
            }

        };

        mModel.getCurrentName().observe(this,nameObserver);
        mModel.getCurrentBitmap().observe(this,bitmapObserver);

        threadLocal.set(true);



        final Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {

                Looper.prepare();

                threadLocal.set(false);
                Log.e(TAG, Thread.currentThread() + ":" + threadLocal.get());



                handler1 = new Handler() {

                    @Override
                    public void handleMessage(Message msg) {
                        super.handleMessage(msg);
                        if (msg.what == 100){

                            Log.e(TAG,"obj is :"+msg.obj);
                            Message message = Message.obtain();
                            message.what = 200;
                            message.obj = "what two fuck";
                            handler.sendMessage(message);

                        }

                    }
                };
                Looper.loop();

            }
        });

        thread.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.e(TAG,Thread.currentThread()+":"+threadLocal.get());

        new Thread(new Runnable() {
            @Override
            public void run() {

                Looper.prepare();
                Log.e(TAG,Thread.currentThread()+":"+threadLocal.get());
                Message message = Message.obtain();
                message.what = 100;
                message.obj = "what a fuck";
                handler1.sendMessage(message);
                handler2 = new Handler(){
                    @Override
                    public void handleMessage(Message msg) {
                        super.handleMessage(msg);
                        if (msg.what == 300){

                            Log.e(TAG,""+msg.obj);

                        }

                    }
                };
                Looper.loop();


            }
        }).start();

    }


    @OnClick(R.id.btn) void changeText(View view) {

        String newName = "da Sb";
        mModel.getCurrentName().setValue(newName);

        Intent intent = new Intent(this, DownLoadService.class);

        for (String url : urlList) {
            intent.putExtra(DownLoadService.DOWN_URL, url);
            startService(intent);
        }

        Intent mIntent = new Intent();
        ComponentName mComp = new ComponentName("com.ruanmei.ithome","com.ruanmei.ithome.MainActivity1");
        //注意AcitivityName(目标应用程序)要完整的，带包名的PackageName的
        mIntent.setComponent(mComp);
        startActivity(mIntent);
    }

    @Override
    public boolean handleMessage(Message message) {

        if (message.what == DownLoadService.WHAT_DOWNLOAD_STARED){

            mModel.getCurrentName().setValue(message.obj.toString());

        }
        if (message.what == DownLoadService.WHAT_DOWNLOAD_FINISHIED){

            mModel.getCurrentBitmap().setValue((Bitmap) message.obj);

        }

        return false;
    }
}
