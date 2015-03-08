package com.wangzhu.jvm;

public class TestJVM {

    // 运行时数据区【方法区、堆、程序计数器、虚拟机栈、本地方法栈】
    private static int _1M = 1024 * 1024;

    public static void main(String[] args) {
	byte[] alloc1, alloc2, alloc3, alloc4, alloc5, alloc6, alloc7, alloc8;
	alloc1 = new byte[1 * _1M];
	alloc2 = new byte[2 * _1M];
	alloc3 = new byte[3 * _1M];
	alloc4 = new byte[4 * _1M];
	alloc5 = new byte[5 * _1M];
	alloc6 = new byte[6 * _1M];

	alloc2 = null;
	alloc7 = new byte[7 * _1M];

    }

    // 命令1：
    // -verbose:gc -Xms30M -Xmx30M -Xmn10M -XX:+PrintGCDetails
    // -XX:SurvivorRatio=8 -XX:+HeapDumpOnOutOfMemoryError

    // 命令2：
    // -verbose:gc -Xms30M -Xmx30M -XX:+PrintGCDetails
    // -XX:NewRatio=2 -XX:SurvivorRatio=8 -XX:+HeapDumpOnOutOfMemoryError

    // -verbose:gc 打印垃圾收集器的执行日志
    // -Xms30M 最小堆的大小
    // -Xmx30M 最大堆的大小
    // -Xmn10M 年轻代大小
    // -XX:NewRatio=2 JVM堆中的新生代与老年代的大小比例为1:2
    // -XX:SurvivorRatio=8 设置Eden与Survivor的大小比例，默认8：1

    // 根据以上参数得知改程序的虚拟机堆大小为30M，年轻代为10M，老年代为20M，
    // 其中年轻代中Eden=8M，From Survivor=To Survivor=1M

    // 备注：
    // 运行时内存与垃圾回收介绍：http://www.cnblogs.com/God-froest/p/jvm_1_3.html
    // 参数介绍：http://blog.csdn.net/shenzhen_liubin/article/details/7207236
    // OOM的样例：http://blog.csdn.net/cutesource/article/details/8244250
}
