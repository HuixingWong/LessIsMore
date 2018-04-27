package com.example.dogoodsoft_app.lessismore.aboutFile;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class DataChek {

    private Context context;


    private static DataChek sInstance;

    private DataChek(Context context) {


        this.context = context;

    }

    public static DataChek getsInstance(Context context) {

        if (sInstance == null) {

            synchronized (DataChek.class) {
                if (sInstance == null) {
                    sInstance = new DataChek(context);
                }
            }
        }

        return sInstance;
    }


    private final String filename = "temp.out";


    public void fileSave2Local(Object obj) {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            //通过openFileOutput方法得到一个输出流

            fos = context.openFileOutput(filename, Context.MODE_PRIVATE);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(obj); //写入
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (oos != null) oos.close();
                if (fos != null) fos.close(); //最后关闭输出流
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public Object readFileFromLocal() {
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;
        try {
            fileInputStream = new FileInputStream(filename);
            objectInputStream = new ObjectInputStream(fileInputStream);
            Object obj = objectInputStream.readObject();

            return obj;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileInputStream != null) fileInputStream.close();
                if (objectInputStream != null) objectInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
