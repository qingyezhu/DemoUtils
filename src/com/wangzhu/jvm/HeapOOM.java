package com.wangzhu.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * Java堆<br/>
 * 所有的对象的实例分配都在Java堆上分配内存，堆大小由-Xms和-Xmx来进行调节<br/>
 * 
 * @author wangzhu
 * @date 2015-3-8下午4:41:24
 * 
 */
public class HeapOOM {

    // 运行时参数：-verbose:gc -Xms10M -Xmx10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
    // -XX:+HeapDumpOnOutOfMemoryError
    public static void main(String[] args) {
	List<OOMObject> list = new ArrayList<HeapOOM.OOMObject>();
	while (true) {
	    list.add(new OOMObject());
	}
    }

    static class OOMObject {

    }
}
