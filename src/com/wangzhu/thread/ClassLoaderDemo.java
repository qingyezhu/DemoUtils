package com.wangzhu.thread;

public class ClassLoaderDemo {

    /**
     * @param args
     */
    public static void main(String[] args) {
	System.out.println("���������" + ClassLoaderDemo.class.getClassLoader());
	System.out.println("��ǰ�̼߳�������"
		+ Thread.currentThread().getContextClassLoader());
	System.out.println("ϵͳ��������" + ClassLoader.getSystemClassLoader());
    }

}
