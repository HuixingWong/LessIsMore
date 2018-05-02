package com.example.dogoodsoft_app.lessismore.aboutFile;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.dogoodsoft_app.lessismore.R;

public class FileActivity extends AppCompatActivity {


    private final String filename = "temp.out";;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file);


        DataWhileKill dataWhileKill = new DataWhileKill();

        DataChek.getsInstance(this).write(this,dataWhileKill,filename);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
            Toast.makeText(this, "this is catch", Toast.LENGTH_SHORT).show();
        }

        Object o = DataChek.getsInstance(this).read(this,filename );



        if (o != null){

            if (o instanceof DataWhileKill){

                Toast.makeText(this,
                        ""+((DataWhileKill) o).getCountTime()
                        , Toast.LENGTH_SHORT).show();
            }

        }else {

            Toast.makeText(this, "this is null", Toast.LENGTH_SHORT).show();
        }


    }
}
