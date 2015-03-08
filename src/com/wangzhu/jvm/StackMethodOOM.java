package com.wangzhu.jvm;

public class StackMethodOOM {

    // 运行时参数：-verbose:gc -XX:+PrintGCDetails -Xss128k
    public static void main(String[] args) {
	StackMethodOOM oom = new StackMethodOOM();
	oom.stackLeak();
    }

    private int stackLength = 1;

    // 抛出异常：Exception in thread "main" java.lang.StackOverflowError
    public void stackLeak() {
	stackLength++;
	this.stackLeak();
    }
}
