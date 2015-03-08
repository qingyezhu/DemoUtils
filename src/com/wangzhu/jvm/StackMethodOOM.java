package com.wangzhu.jvm;

public class StackMethodOOM {

    // ����ʱ������-verbose:gc -XX:+PrintGCDetails -Xss128k
    public static void main(String[] args) {
	StackMethodOOM oom = new StackMethodOOM();
	oom.stackLeak();
    }

    private int stackLength = 1;

    // �׳��쳣��Exception in thread "main" java.lang.StackOverflowError
    public void stackLeak() {
	stackLength++;
	this.stackLeak();
    }
}
