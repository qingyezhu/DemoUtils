package com.wangzhu.sortutil;

import java.util.Arrays;

/**
 * 直接插入排序<br/>
 * 假设前面n - 1(n >= 2)个数是排好序的，现在要把第n个数插入到前面有序数列中，使得这n个数也是有序的。<br/>
 * 如此反复循环，直到全部排序好！<br/>
 * 
 * @author wangzhu2014-7-16下午5:20:25
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
