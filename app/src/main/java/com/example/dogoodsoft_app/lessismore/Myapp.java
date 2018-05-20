package com.example.dogoodsoft_app.lessismore;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.example.dogoodsoft_app.lessismore.base.BaseActivity;

public class Myapp extends Application{


    @Override
    public void onCreate() {
        super.onCreate();


        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle bundle) {


                if (activity instanceof BaseActivity){

                    ((BaseActivity) activity).fuck();
                }

            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {

            }
        });
    }
}
