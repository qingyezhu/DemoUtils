package com.wangzhu.thread;

public class ClassLoaderDemo {

    /**
     * @param args
     */
    public static void main(String[] args) {
	System.out.println("类加载器：" + ClassLoaderDemo.class.getClassLoader());
	System.out.println("当前线程加载器："
		+ Thread.currentThread().getContextClassLoader());
	System.out.println("系统加载器：" + ClassLoader.getSystemClassLoader());
    }

}
