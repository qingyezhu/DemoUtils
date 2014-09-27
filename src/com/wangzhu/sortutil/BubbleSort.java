package com.wangzhu.sortutil;

import java.util.Arrays;

/**
 * 冒泡排序<br/>
 * 让较大的数往下沉，让较小的数向上浮<br/>
 * 时间复杂度：O(n*n)<br/>
 * 
 * @author wangzhu
 * @date 2014-9-27上午11:11:10
 * 
 */
public class BubbleSort {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		final int[] arr1 = { 12, 31, 1, 8, 9, 9, 7, 1, 1 };
		BubbleSort.sort(arr1);
		System.out.println(Arrays.toString(arr1));

		final int[] arr2 = { 19, 0, 1, 0, 1, 7, -7, 7, -7 };
		BubbleSort.sort(arr2);
		System.out.println(Arrays.toString(arr2));
		final int[] arr3 = { 11, 12, -11, 12, 10, 9, 6, 3, 8 };
		BubbleSort.sort(arr3);
		System.out.println(Arrays.toString(arr3));

		final int[] arr4 = { 1, 3, 7, 9, 10, 8, 6, 4, 2, 0, -1, -2, 9 };
		BubbleSort.sort(arr4);
		System.out.println(Arrays.toString(arr4));
	}

	public static void sort(int[] arr) {
		for (int i = 0, len = arr.length - 1; i < len; i++) {
			for (int j = 0; j < (len - i); j++) {
				if (arr[j] > arr[j + 1]) {
					BubbleSort.swap(j, j + 1, arr);
				}
			}
		}
	}

	private static void swap(int j, int i, int[] arr) {
		int tmp = arr[j];
		arr[j] = arr[i];
		arr[i] = tmp;
	}
}
