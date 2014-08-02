package com.wangzhu.sortutil;

import java.util.Arrays;

/**
 * ֱ�Ӳ�������<br/>
 * ����ǰ��n - 1(n >= 2)�������ź���ģ�����Ҫ�ѵ�n�������뵽ǰ�����������У�ʹ����n����Ҳ������ġ�<br/>
 * ��˷���ѭ����ֱ��ȫ������ã�<br/>
 * 
 * @author wangzhu2014-7-16����5:20:25
 * 
 */
public class InsertSort {

	public static void main(final String[] args) {
		final int[] arr1 = { 12, 31, 1, 8, 9, 9, 7, 1, 1 };
		InsertSort.sort(arr1);
		System.out.println(Arrays.toString(arr1));

		final int[] arr2 = { 19, 0, 1, 0, 1, 7, -7, 7, -7 };
		InsertSort.sort(arr2);
		System.out.println(Arrays.toString(arr2));
		final int[] arr3 = { 11, 12, -11, 12, 10, 9, 6, 3, 8 };
		InsertSort.sort(arr3);
		System.out.println(Arrays.toString(arr3));

		final int[] arr4 = { 1, 3, 7, 9, 10, 8, 6, 4, 2, 0, -1, -2, 9 };
		InsertSort.sort(arr4);
		System.out.println(Arrays.toString(arr4));

	}

	public static void sort(final int[] arr) {

		for (int i = 1, len = arr.length; i < len; i++) {
			final int tmp = arr[i];
			int j = i - 1;
			for (; (j >= 0) && (arr[j] > tmp); j--) {
				arr[j + 1] = arr[j];
			}
			arr[j + 1] = tmp;
		}
	}
}
