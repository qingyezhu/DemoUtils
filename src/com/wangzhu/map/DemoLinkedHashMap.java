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
	// initialCapacity ��ʼ����
	// loadFactor ��������
	// accessOrder ����ģʽ ��Ϊtrueʱ�������������ʹ��(LRU)�����򱣳ֲ����˳��
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
