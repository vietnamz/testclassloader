package com.vietnamz.example.main;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        System.out.println(main.getClass().getClassLoader());

        try {
            Path second = Paths.get("tmp.jar");
            Path first = Paths.get("firstmodule-1.0.jar");
            Path third = Paths.get("secondmodule-1.0.jar");
            URL[] jars = new URL[]{second.toUri().toURL(), first.toUri().toURL(), third.toUri().toURL()};
            URLClassLoader cls = new ExampleURLClassLoader(jars, main.getClass().getClassLoader());
            System.out.println(cls.getClass().getClassLoader());
            System.out.println(cls.getParent());
            Class<?> firstClass = cls.loadClass("com.vietnamz.example.first.FirstClass");
            System.out.println(firstClass.getClassLoader());
            Constructor<?> cons = firstClass.getConstructor();
            Object firstClssObjs = cons.newInstance();
            Method method = firstClass.getMethod("methodFirst");
            method.invoke(firstClssObjs);
        } catch (Exception e) {
            System.out.println(e.getCause());
        }

    }
}
