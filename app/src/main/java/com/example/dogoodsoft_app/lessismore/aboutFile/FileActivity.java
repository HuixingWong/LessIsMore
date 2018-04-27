package com.example.dogoodsoft_app.lessismore.aboutFile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.dogoodsoft_app.lessismore.R;

public class FileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file);


        DataWhileKill dataWhileKill = new DataWhileKill();

        DataChek.getsInstance(this).fileSave2Local(dataWhileKill);


        Object o = DataChek.getsInstance(this).readFileFromLocal();

        if (o != null){

            if (o instanceof DataWhileKill){

                Toast.makeText(this,
                        ""+((DataWhileKill) o).isPause()
                        , Toast.LENGTH_SHORT).show();
            }

        }else {

            Toast.makeText(this, "this is null", Toast.LENGTH_SHORT).show();
        }


    }
}
