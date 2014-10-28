package com.wangzhu.map;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.SortedMap;
import java.util.TreeMap;

public class DemoMap1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Map<String, String> map = DemoMap1.getMapData();
		System.out.println(map);

		DemoMap1.convertMapKeyToList(map);

		DemoMap1.converMapValueToList(map);

		DemoMap1.converMapToList(map);

		DemoMap1.showMap(map);

		DemoMap1.sortMap(map);

		DemoMap1.otherMap(map);
	}

	/**
	 * �������
	 */
	private static Random random = new Random();

	/**
	 * ����Map����
	 * 
	 * @return
	 */
	private static Map<String, String> getMapData() {
		Map<String, String> map = new HashMap<String, String>();
		for (int i = 0; i < 5; i++) {
			map.put("key" + DemoMap1.random.nextInt(100), "value"
					+ DemoMap1.random.nextInt(10));
		}
		return map;
	}

	/**
	 * ��Map��Keyת��ΪList
	 * 
	 * @param map
	 */
	private static void convertMapKeyToList(Map<String, String> map) {
		List<String> list = new ArrayList<String>(map.keySet());
		System.out.println("convertMapKeyToList: " + list);
	}

	/**
	 * ��Map��Valueת��ΪList
	 * 
	 * @param map
	 */
	private static void converMapValueToList(Map<String, String> map) {
		List<String> list = new ArrayList<String>(map.values());

		System.out.println("converMapValueToList: " + list);
	}

	/**
	 * ��Mapת��ΪList
	 * 
	 * @param map
	 */
	private static void converMapToList(Map<String, String> map) {
		List<Entry<String, String>> list = new ArrayList<Entry<String, String>>(
				map.entrySet());
		System.out.println("converMapToList: " + list);
	}

	/**
	 * ����Map
	 * 
	 * @param map
	 */
	private static void showMap(Map<String, String> map) {
		System.out.println("1.keySet");
		for (String key : map.keySet()) {
			System.out.println("key: " + key + " === value: " + map.get(key));
		}

		System.out.println("2.Entry");
		for (Entry<String, String> entry : map.entrySet()) {
			System.out.println("key: " + entry.getKey() + " === value: "
					+ entry.getValue());
		}

		System.out.println("3.Iterator");
		Iterator<Entry<String, String>> iter = map.entrySet().iterator();
		while (iter.hasNext()) {
			Entry<String, String> entry = iter.next();
			System.out.println("key: " + entry.getKey() + " === value: "
					+ entry.getValue());
		}
	}

	/**
	 * Map����
	 * 
	 * @param map
	 */
	private static void sortMap(Map<String, String> map) {
		List<Entry<String, String>> list = new ArrayList<Entry<String, String>>(
				map.entrySet());

		System.out.println("1.Collections key sort");
		Collections.sort(list, new Comparator<Entry<String, String>>() {

			@Override
			public int compare(Entry<String, String> e1,
					Entry<String, String> e2) {
				// ��keyֵ������ĸ��С��������
				return e1.getKey().compareTo(e2.getKey());
			}

		});

		System.out.println("key sort list: " + list);

		System.out.println("2.Collections value sort");
		Collections.sort(list, new Comparator<Entry<String, String>>() {

			@Override
			public int compare(Entry<String, String> e1,
					Entry<String, String> e2) {
				// ��valueֵ������ĸ�Ӵ�С����
				return e2.getValue().compareTo(e1.getValue());
			}

		});

		System.out.println("value sort list: " + list);

		System.out.println("3.sortedmap key sort");
		SortedMap<String, String> sortedMap = new TreeMap<String, String>(
				new Comparator<String>() {

					@Override
					public int compare(String s1, String s2) {
						return s2.compareTo(s1);
					}
				});

		sortedMap.putAll(map);
		System.out.println("sort map: " + sortedMap);
	}

	/**
	 * Map������һЩӦ��<br/>
	 * �磺��Map�����ɱ�Map��
	 * 
	 * @param map
	 */
	private static void otherMap(Map<String, String> map) {
		// �����յ�Map
		Map<String, String> emptyMap = Collections.emptyMap();
		System.out.println("emptyMap===" + emptyMap);

		Map<String, String> unmodifyMap = Collections.unmodifiableMap(map);
		System.out.println("unmodifyMap====" + unmodifyMap);
		map.put("key" + DemoMap1.random.nextInt(1000), "value"
				+ DemoMap1.random.nextInt(100));
		System.out.println("map====" + map);
		System.out.println("unmodifyMap====" + unmodifyMap);

		// ��������׳��쳣��java.lang.UnsupportedOperationException
		// unmodifyMap.put("key" + random.nextInt(1000),
		// "value" + random.nextInt(100));

		System.out.println("unmodifyMap====" + unmodifyMap);
		String[] data = new String[] { "����", "����", "����", "Lili", "John", "Hame" };
		/**
		 * ��¼�����˳��
		 */
		Map<String, String> linkedMap = new LinkedHashMap<String, String>();
		Map<String, String> oMap = new HashMap<String, String>();
		for (String element : data) {
			linkedMap.put(element, element);
			oMap.put(element, element);
		}
		System.out.println("HashMap��");
		DemoMap1.showMap(oMap);
		System.out.println("LinkedHashMap��");
		DemoMap1.showMap(linkedMap);
	}

}
