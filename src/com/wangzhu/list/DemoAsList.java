package com.wangzhu.list;

import java.util.Arrays;
import java.util.List;

/**
 * 1)��ʹ��asListʱ����Ҫ�������������͵�������<br/>
 * 2)��asList�������б��ɲ���
 * 
 * @author wangzhu
 * @date 2014-11-9����3:14:31
 * 
 */
public class DemoAsList {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		int[] intArr = new int[] { 1, 2, 3, 4, 5 };
		List<int[]> list1 = Arrays.asList(intArr);
		System.out.println(list1.get(0).getClass());
		System.out.println(list1.get(0).equals(intArr));

		Integer[] integerArr = new Integer[] { 1, 2, 3, 4, 5 };
		List<Integer> list2 = Arrays.asList(integerArr);
		System.out.println(list2.get(0).getClass());
		System.out.println(list2.get(0).equals(integerArr[0]));
	}

}
