package com.example.dogoodsoft_app.lessismore.lifecycle;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.dogoodsoft_app.lessismore.R;

import me.drakeet.materialdialog.MaterialDialog;

public class LifeCycleTestActiity extends AppCompatActivity {


    private Button button;
    private MaterialDialog materialDialog;


    private final String TAG = "fuckfuck";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life_cycle_test_actiity);

        Log.e(TAG, "onCreate");

        button = findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               show();

            }
        });

    }

    private void show(){

         materialDialog = new MaterialDialog(LifeCycleTestActiity.this)
                .setTitle("MaterialDialog")
                .setMessage("Hello world!")
                .setPositiveButton("OK", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        materialDialog.dismiss();


                    }
                })
                .setNegativeButton("CANCEL", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        materialDialog.dismiss();
                    }
                });

        materialDialog.show();
    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "onResume");
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG, "onStart");

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG,"onPause" );
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG, "onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e(TAG,"onRestart" );
    }

    @Override
    protected void onDestroy() {
        Log.e(TAG,"onDestroy" );
        super.onDestroy();

    }
}

