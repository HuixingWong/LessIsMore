package com.example.dogoodsoft_app.lessismore.dagger;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.dogoodsoft_app.lessismore.R;

import java.lang.reflect.Field;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class DaggerActivity extends AppCompatActivity {


    @OnClick({R.id.dagger})
    public void dagger(){

        ZhaiNan waimai = DaggerPlatform.builder().build().waimai();

        Toast.makeText(this, ""+waimai.eat(), Toast.LENGTH_SHORT).show();


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dagger);
        ButterKnife.bind(this);


        Class<View> viewClass = View.class;
        View view  = new View(this);

        try {
            Field field = viewClass.getDeclaredField("ACCESSIBILITY_LIVE_REGION_NONE");

            field.setAccessible(true);

            field.setInt(view, 100);

            Log.e("fuckagain", field.getInt(view)+"");

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
