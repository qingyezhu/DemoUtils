package com.wangzhu.thread;

import java.net.URL;

/**
 * 同步与互斥
 * 
 * @author wangzhu
 * @date 2015-3-21下午4:37:35
 * 
 */
public class SynchronizedTest {

    static class Output {
	public void print(String str) {
	    synchronized (this) {// 若不加同步与互斥，则会出现数据结果丢失

		String basePath = System.getProperty("user.dir");
		System.out.println("basePath==" + basePath);
		basePath = basePath.replaceAll("\\\\", "/");
		System.out.println("basePath==" + basePath);
		basePath = basePath.substring(0, basePath.lastIndexOf("/"));
		System.out.println("basePath==" + basePath);
		URL url = Thread.currentThread().getContextClassLoader()
			.getResource("");
		if (url != null) {
		    System.out.println("url===" + url.getFile());
		}

		for (int i = 0, len = str.length(); i < len; i++) {
		    System.out.print(str.charAt(i));
		}
		System.out.println();
	    }
	}
    }

    public static void main(String[] args) {
	final Output output = new Output();
	new Thread() {
	    @Override
	    public void run() {
		while (true) {
		    try {
			Thread.sleep(10);
		    } catch (InterruptedException e) {
			e.printStackTrace();
		    }
		    output.print("qingyezhu");
		}
	    };
	}.start();
	new Thread() {
	    @Override
	    public void run() {
		while (true) {
		    try {
			Thread.sleep(10);
		    } catch (InterruptedException e) {
			e.printStackTrace();
		    }
		    output.print("welcome");
		}
	    };
	}.start();

    }
}
