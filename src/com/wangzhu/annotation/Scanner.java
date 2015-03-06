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

	// �Ƿ�ѭ��������
	boolean recursive = true;
	// ���ɨ�赽����
	Set<Class<?>> clazzs = new LinkedHashSet<Class<?>>();
	// ������ת��Ϊ�ļ�·��
	String packageDirName = packageName.replace('.', '/');
	try {
	    Enumeration<URL> dirs = classLoader.getResources(packageDirName);
	    while (dirs.hasMoreElements()) {
		URL url = dirs.nextElement();
		String protocol = url.getProtocol();
		if ("file".equals(protocol)) {
		    // ��ȡ��������·��
		    String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
		    // ���ļ��ķ�ʽɨ���������µ��ļ�������ӵ�������
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
	// ��ȡ�˰���Ŀ¼������һ���ļ�
	File dir = new File(packagePath);
	if (!dir.exists() || !dir.isDirectory()) {
	    // ��������ڣ����߲���Ŀ¼ֱ�ӷ���
	    return;
	}
	// ������ڣ��ͻ�ȡ�ð��µ������ļ�������Ŀ¼
	File[] dirFiles = dir.listFiles(new FileFilter() {
	    // �Զ�����˹����������ѭ����������Ŀ¼ʱ������.class��β���ļ�
	    @Override
	    public boolean accept(File file) {
		return (recursive && file.isDirectory())
			|| (file.getName().endsWith(".class"));
	    }
	});
	// ѭ�������ļ�
	for (File file : dirFiles) {
	    if (file.isDirectory()) {
		// ����Ŀ¼ʱ����ݹ����ɨ��
		this.findClassInPackageByFile(
			packageName + "." + file.getName(),
			file.getAbsolutePath(), recursive, clazzs);
	    } else {
		// ����Java�����ļ�ʱ��ȥ����׺
		String fileName = file.getName();
		String className = fileName.substring(0, fileName.length() - 6);
		try {
		    // ��ӵ�������
		    clazzs.add(classLoader.loadClass(packageName + "."
			    + className));
		} catch (ClassNotFoundException e) {
		    e.printStackTrace();
		}
	    }
	}
    }
}
