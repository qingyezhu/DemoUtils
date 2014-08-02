package com.wangzhu.map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DemoMap {

	public static void main(String[] args) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 3; j++) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("A", i);
				for (int k = 0; k < 2; k++) {
					map.put("B" + k, (i + j + k));
				}
				list.add(map);
			}
		}
		System.out.println(list);
		System.out.println(DemoMap.mergeListToMap(list, "A", new String[] {
				"B0", "B1" }));
	}

	/**
	 * �����ṩ�Ĺؼ����ԣ����������Խ���ӳ�䣬������ʾ��<br/>
	 * &nbsp;&nbsp;pA1<br/>
	 * &nbsp;&nbsp;&nbsp;&nbsp;(pB11,pC11,pD11)<br/>
	 * &nbsp;&nbsp;&nbsp;&nbsp;(pB12,pC12,pD12)<br/>
	 * &nbsp;&nbsp;&nbsp;&nbsp;(pB13,pC13,pD13)<br/>
	 * &nbsp;&nbsp;&nbsp;&nbsp;(pB14,pC14,pD14)<br/>
	 * &nbsp;&nbsp;pA2<br/>
	 * &nbsp;&nbsp;&nbsp;&nbsp;(pB21,pC21,pD21)<br/>
	 * &nbsp;&nbsp;&nbsp;&nbsp;(pB22,pC22,pD22)<br/>
	 * 
	 * @param list
	 *            ����
	 * @param propertyA
	 *            �ؼ�����
	 * @param propertyBArr
	 *            ��ȡ����
	 * @return
	 */
	public static Map<String, List<Map<String, Object>>> mergeListToMap(
			List<Map<String, Object>> list, String propertyA,
			String[] propertyBArr) {
		List<Map<String, Object>> itemList = null;

		Object tmpPropertyAValue = null;
		Map<String, List<Map<String, Object>>> resultMap = new HashMap<String, List<Map<String, Object>>>();

		for (int i = 0, size = list.size(); i < size; i++) {
			Object propertyAValue = DemoMap.getValueByKey(list, i, propertyA);
			if (!propertyAValue.equals(tmpPropertyAValue)) {
				itemList = new ArrayList<Map<String, Object>>();
				resultMap.put(String.valueOf(propertyAValue), itemList);
				tmpPropertyAValue = propertyAValue;
			}
			Map<String, Object> dataMap = new HashMap<String, Object>();
			for (String element : propertyBArr) {
				dataMap.put(element, DemoMap.getValueByKey(list, i, element));
			}
			itemList.add(dataMap);
		}
		return resultMap;
	}

	/**
	 * ��List�б��еĵ�index��Map�л�ȡproperty����Ӧ��ֵ
	 * 
	 * @param list
	 *            �б�
	 * @param index
	 *            �ڼ���
	 * @param property
	 *            Key����
	 * @return
	 */
	public static Object getValueByKey(List<Map<String, Object>> list,
			int index, String property) {
		Object value = null;
		if ((list != null) && (list.size() > index)) {
			Map<String, Object> map = list.get(index);
			if (map.containsKey(property)) {
				value = map.get(property);
			}
		}
		return value;
	}

}
