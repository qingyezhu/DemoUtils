package com.wangzhu.map;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class DemoLinkedHashMap {

    /**
     * @param args
     */
    public static void main(String[] args) {
	Map<Integer, String> map = new HashMap<Integer, String>();
	for (int i = 0; i < 10; i++) {
	    map.put(i, "A" + i);
	}

	Map<Integer, String> linkedMap = new LinkedHashMap<Integer, String>(map);
	System.out.println("before: " + linkedMap);
	linkedMap.get(1);
	linkedMap.get(3);
	linkedMap.get(7);
	System.out.println("after: " + linkedMap);

	System.out.println("=====");
	// initialCapacity 初始容量
	// loadFactor 加载因子
	// accessOrder 排序模式 当为true时，采用最近最少使用(LRU)，否则保持插入的顺序
	Map<Integer, String> linkedMap1 = new LinkedHashMap<Integer, String>(
		10, 0.75f, true);
	linkedMap1.putAll(map);
	System.out.println("before: " + linkedMap1);
	linkedMap1.get(1);
	linkedMap1.get(3);
	linkedMap1.get(7);
	System.out.println("after: " + linkedMap1);
    }

}
