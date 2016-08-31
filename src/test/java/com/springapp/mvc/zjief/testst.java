package com.springapp.mvc.zjief;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by zhangjiefeng on 16/8/7.
 */
public class testst {
    public static void tets1() {
        try {
            Class aClass = Class.forName("com.springapp.mvc.zjief.Test1111");
            System.out.println(aClass);
            Constructor[] declaredConstructors = aClass.getDeclaredConstructors();
            System.out.println(declaredConstructors);
            Constructor declaredConstructor = aClass.getDeclaredConstructor((Class[]) null);
            Test1111 test1111 = (Test1111) declaredConstructor.newInstance();
            System.out.println(test1111);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        tets1();
    }
}
