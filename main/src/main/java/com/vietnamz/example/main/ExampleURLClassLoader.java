package com.vietnamz.example.main;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;

class ExampleURLClassLoader extends URLClassLoader {
    public ExampleURLClassLoader(URL[] urls, ClassLoader parent) {
        super(urls, parent);
    }

    public synchronized void addJarToClasspath(String jarName) throws MalformedURLException, ClassNotFoundException {
        File filePath = new File(jarName);
        URI uriPath = filePath.toURI();
        URL urlPath = uriPath.toURL();
        this.addURL(urlPath);
    }
}