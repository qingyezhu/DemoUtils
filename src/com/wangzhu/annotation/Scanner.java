package com.wangzhu.annotation;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.LinkedHashSet;
import java.util.Set;

public class Scanner {
    private ClassLoader classLoader = null;

    public Set<Class<?>> scanPackage(String packageName) {
	classLoader = Thread.currentThread().getContextClassLoader();

	// 是否循环搜索包
	boolean recursive = true;
	// 存放扫描到的类
	Set<Class<?>> clazzs = new LinkedHashSet<Class<?>>();
	// 将报名转化为文件路径
	String packageDirName = packageName.replace('.', '/');
	try {
	    Enumeration<URL> dirs = classLoader.getResources(packageDirName);
	    while (dirs.hasMoreElements()) {
		URL url = dirs.nextElement();
		String protocol = url.getProtocol();
		if ("file".equals(protocol)) {
		    // 获取包的物理路径
		    String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
		    // 以文件的方式扫描整个包下的文件，并添加到集合中
		    this.findClassInPackageByFile(packageName, filePath,
			    recursive, clazzs);
		}
	    }
	    return clazzs;
	} catch (IOException e) {
	    e.printStackTrace();
	}

	return null;
    }

    private void findClassInPackageByFile(String packageName,
	    String packagePath, final boolean recursive, Set<Class<?>> clazzs) {
	// 获取此包的目录，建立一个文件
	File dir = new File(packagePath);
	if (!dir.exists() || !dir.isDirectory()) {
	    // 如果不存在，或者不是目录直接返回
	    return;
	}
	// 如果存在，就获取该包下的所有文件，包括目录
	File[] dirFiles = dir.listFiles(new FileFilter() {
	    // 自定义过滤规则，如果可以循环并且是子目录时，或以.class结尾的文件
	    @Override
	    public boolean accept(File file) {
		return (recursive && file.isDirectory())
			|| (file.getName().endsWith(".class"));
	    }
	});
	// 循环所有文件
	for (File file : dirFiles) {
	    if (file.isDirectory()) {
		// 当是目录时，则递归继续扫描
		this.findClassInPackageByFile(
			packageName + "." + file.getName(),
			file.getAbsolutePath(), recursive, clazzs);
	    } else {
		// 当是Java的类文件时，去掉后缀
		String fileName = file.getName();
		String className = fileName.substring(0, fileName.length() - 6);
		try {
		    // 添加到集合中
		    clazzs.add(classLoader.loadClass(packageName + "."
			    + className));
		} catch (ClassNotFoundException e) {
		    e.printStackTrace();
		}
	    }
	}
    }
}
