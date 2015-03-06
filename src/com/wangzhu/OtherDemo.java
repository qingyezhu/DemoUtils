package com.wangzhu;

import java.io.UnsupportedEncodingException;

public class OtherDemo {

    /**
     * @param args
     */
    public static void main(String[] args) {
	String str = "汉字";
	try {
	    System.out.println(new String(str.getBytes()));
	    System.out.println(new String(str.getBytes("utf-8")));
	    System.out.println(new String(str.getBytes("utf-8"), "utf-8"));
	    System.out.println(new String(str.getBytes("gbk")));
	} catch (UnsupportedEncodingException e) {
	    e.printStackTrace();
	}

	// System.out.println("num=" + OtherDemo.num);
	// System.out.println("str=" + OtherDemo.str);
	//
	// OtherDemo.copeOfArray();
	//
	// OtherDemo.forOfArray();
	//
	// Cat cat = new Cat();
	// cat.say();

	try {
	    boolean a1 = true;
	    boolean a2 = false;
	    int c = 1;
	    if ((a2 = true) && (c >= 1)) {
		// 布尔值可以赋值，其他的都不行，否则编译出错
	    }
	    return;
	} finally {
	    System.out.println("aaaa");
	}
    }

    private static final int MAX = 2500000;

    /**
     * 复制数组时，建议使用System.arraycopy
     */
    private static void copeOfArray() {
	int[] aArr = new int[OtherDemo.MAX];
	int[] bArr = new int[OtherDemo.MAX];
	for (int i = 0; i < OtherDemo.MAX; i++) {
	    aArr[i] = i;
	}

	long start = System.currentTimeMillis();
	for (int i = 0; i < OtherDemo.MAX; i++) {
	    aArr[i] = bArr[i];
	}
	long end = System.currentTimeMillis();
	System.out.println(end - start);
	start = System.currentTimeMillis();
	System.arraycopy(aArr, 0, bArr, 0, aArr.length);
	end = System.currentTimeMillis();
	System.out.println(end - start);
    }

    /**
     * 循环遍历时，建议使用0值为终结条件
     */
    private static void forOfArray() {
	int[] aArr = new int[OtherDemo.MAX];
	long start = System.currentTimeMillis();
	for (int i = 0, len = aArr.length; i < len; i++) {
	    aArr[i] = i;
	}
	long end = System.currentTimeMillis();
	System.out.println(end - start);

	int[] bArr = new int[OtherDemo.MAX];
	start = System.currentTimeMillis();
	for (int i = aArr.length - 1; i >= 0; i--) {
	    bArr[i] = i;
	}
	end = System.currentTimeMillis();
	System.out.println(end - start);
    }

    /**
     * static类型的变量，赋值需要注意其顺序
     */
    static {
	OtherDemo.num = 1;
	OtherDemo.str = "str";
    }
    private static int num;
    private static String str = null;
}

class Animal {
    final void show() {
	System.out.println("Animal show");
    }
}

class Cat extends Animal {
    void say() {
	this.show();
	System.out.println("cat say");
    }
}
