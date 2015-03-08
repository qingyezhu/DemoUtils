package com.wangzhu.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * Java��<br/>
 * ���еĶ����ʵ�����䶼��Java���Ϸ����ڴ棬�Ѵ�С��-Xms��-Xmx�����е���<br/>
 * 
 * @author wangzhu
 * @date 2015-3-8����4:41:24
 * 
 */
public class HeapOOM {

    // ����ʱ������-verbose:gc -Xms10M -Xmx10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
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
