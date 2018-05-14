package com.example.dogoodsoft_app.lessismore.reflect;

import android.view.View;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Test {

    public static void main(String[] args) throws NoSuchFieldException {

        //两种反射获取的方法，可以调用名字用前者，无法获得类名用后者。

        Class<View> viewClass1 = View.class;
        Class<View> viewClass = null;
        try {
            viewClass = (Class<View>) Class.forName("android.view.View");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Constructor<?>[] constructors = viewClass.getConstructors();

        for (Constructor constructor:
             constructors) {

            constructor.setAccessible(true);
            System.out.println(constructor);

        }

        Method[] methods = viewClass.getMethods();
        for (Method method : methods){

            System.out.println(method);
            Class<?> returnType = method.getReturnType();

            System.out.println(returnType);

        }


        Field accessibility_live_region_none = viewClass.getDeclaredField("ACCESSIBILITY_LIVE_REGION_NONE");

        accessibility_live_region_none.setAccessible(true);

//        accessibility_live_region_none.setInt(, );

    }



}
