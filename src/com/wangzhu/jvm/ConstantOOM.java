package com.wangzhu.jvm;

import java.util.ArrayList;
import java.util.List;

public class ConstantOOM {

    // 运行时参数：-verbose:gc -XX:+PrintGCDetails -XX:PermSize=10M
    // -XX:MaxPermSize=10M
    public static void main(String[] args) {
	List<String> list = new ArrayList<String>();
	int i = 0;
	while (true) {
	    list.add(String.valueOf(i++).intern());
	}
    }

    // 抛出异常：Exception in thread "main" java.lang.OutOfMemoryError: PermGen space
    static class OOMObject {

    }
}
