package com.example.dogoodsoft_app.lessismore.aboutFile;

import android.content.Context;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
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






    /**
     * 写入本地文件
     * @param context
     * @param obj
     * @param fileName
     */
    public static void write(Context context, Object obj, String fileName) {
        try {
            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            ObjectOutputStream oout = new ObjectOutputStream(bout);
            oout.writeObject(obj);
            oout.flush();
            oout.close();
            bout.close();
            byte[] b = bout.toByteArray();
            File file = new File(context.getFilesDir(), fileName);
            FileOutputStream out = new FileOutputStream(file);
            out.write(b);
            out.flush();
            out.close();
        } catch (Exception e) {
        } finally {

        }
    }

    public static void write2(Context context,Object object,String filename){

        try {

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(object);
            objectOutputStream.flush();
            objectOutputStream.close();
            byteArrayOutputStream.close();

            byte[] bytes = byteArrayOutputStream.toByteArray();
            File file = new File(context.getFilesDir(),filename);
            FileOutputStream outputStream = new FileOutputStream(file);
            outputStream.write(bytes);
            outputStream.flush();
            outputStream.close();


        }catch (Exception e){
            e.printStackTrace();
        }


    }

    /**
     * 从本地文件读取
     * @param context
     * @param fileName
     * @return
     */
    public static Object read(Context context, String fileName) {
        // 拿出持久化数据
        Object obj = null;
        try {
            File file = new File(context.getFilesDir(), fileName);
            FileInputStream in = new FileInputStream(file);
            ObjectInputStream oin = new ObjectInputStream(in);
            obj = oin.readObject();
            in.close();
            oin.close();
        } catch (Exception e) {
        }
        return obj;
    }

    public static Object read2(Context context,String filename){

        Object obj = null;

        try {

            File file = new File(context.getFilesDir(),filename);
            FileInputStream inputStream = new FileInputStream(file);
            ObjectInputStream in = new ObjectInputStream(inputStream);
            obj = in.readObject();
            inputStream.close();
            in.close();

        }catch (Exception e){

            e.printStackTrace();

        }

        return obj;


    }

}
