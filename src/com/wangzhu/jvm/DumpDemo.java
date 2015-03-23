package com.wangzhu.jvm;

public class DumpDemo {

    private static final int LEN = 1000;

    // jvm args:-verbose:gc -Xms10M -Xmx10M -XX:+PrintGCDetails -XX:NewRatio=1
    // -XX:SurvivorRatio=8 -XX:+HeapDumpOnOutOfMemoryError
    // -XX:HeapDumpPath=E:\
    public static void main(String[] args) {
	long[] arr;
	for (int i = 0; i <= LEN; i *= 2) {
	    arr = new long[i];
	}
    }

}
