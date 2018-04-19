package com.example.dogoodsoft_app.lessismore.test;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.graphics.Bitmap;

/**
 * Created by dogoodsoft-app on 2018/3/27.
 */

public class NameViewModel extends ViewModel {

    // Create a LiveData with a String
    private MutableLiveData<String> mCurrentName;
    private MutableLiveData<Bitmap> mCurrentBitmap;

    public MutableLiveData<String> getCurrentName() {
        if (mCurrentName == null) {
            mCurrentName = new MutableLiveData<String>();
        }
        return mCurrentName;
    }


    public MutableLiveData<Bitmap> getCurrentBitmap() {
        if (mCurrentBitmap == null) {
            mCurrentBitmap = new MutableLiveData<Bitmap>();
        }
        return mCurrentBitmap;
    }

// Rest of the ViewModel...
}
