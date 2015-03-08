package com.wangzhu.jvm;

public class TestJVM {

    // ����ʱ�����������������ѡ�����������������ջ�����ط���ջ��
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

    // ����1��
    // -verbose:gc -Xms30M -Xmx30M -Xmn10M -XX:+PrintGCDetails
    // -XX:SurvivorRatio=8 -XX:+HeapDumpOnOutOfMemoryError

    // ����2��
    // -verbose:gc -Xms30M -Xmx30M -XX:+PrintGCDetails
    // -XX:NewRatio=2 -XX:SurvivorRatio=8 -XX:+HeapDumpOnOutOfMemoryError

    // -verbose:gc ��ӡ�����ռ�����ִ����־
    // -Xms30M ��С�ѵĴ�С
    // -Xmx30M ���ѵĴ�С
    // -Xmn10M �������С
    // -XX:NewRatio=2 JVM���е���������������Ĵ�С����Ϊ1:2
    // -XX:SurvivorRatio=8 ����Eden��Survivor�Ĵ�С������Ĭ��8��1

    // �������ϲ�����֪�ĳ����������Ѵ�СΪ30M�������Ϊ10M�������Ϊ20M��
    // �����������Eden=8M��From Survivor=To Survivor=1M

    // ��ע��
    // ����ʱ�ڴ����������ս��ܣ�http://www.cnblogs.com/God-froest/p/jvm_1_3.html
    // �������ܣ�http://blog.csdn.net/shenzhen_liubin/article/details/7207236
    // OOM��������http://blog.csdn.net/cutesource/article/details/8244250
}
