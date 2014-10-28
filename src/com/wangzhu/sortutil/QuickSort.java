package com.wangzhu.sortutil;

import java.util.Arrays;
import java.util.Stack;

/**
 * 快速排序：<br/>
 * 选择一个基准元素，通常选择第一个元素或最后一个元素，通过一趟扫描，将待排序列分成两部分，<br/>
 * 一部分比基准元素小，一部分大于等于基准元素，此时基准元素在其排好序后的正确位置，<br/>
 * 然后在用同样的方法递归地排序划分的两部分。<br/>
 * 时间复杂度：O(nlogn)<br/>
 * 
 * @author wangzhu
 * @date 2014-9-27上午11:35:20
 * 
 */
public class QuickSort {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		final int[] arr1 = { 12, 31, 1, 8, 9, 9, 7, 1, 1 };
		QuickSort.sort(arr1);
		System.out.println(Arrays.toString(arr1));

		final int[] arr2 = { 19, 0, 1, 0, 1, 7, -7, 7, -7 };
		QuickSort.sort(arr2);
		System.out.println(Arrays.toString(arr2));
		final int[] arr3 = { 11, 12, -11, 12, 10, 9, 6, 3, 8 };
		QuickSort.sort(arr3);
		System.out.println(Arrays.toString(arr3));

		final int[] arr4 = { 1, 3, 7, 9, 10, 8, 6, 4, 2, 0, -1, -2, 9 };
		QuickSort.sort(arr4);
		System.out.println(Arrays.toString(arr4));
	}

	public static void sort(int[] arr) {
		if ((null != arr) && (arr.length > 0)) {
			// 查看数组属否为空或数组长度为0
			QuickSort.quickSortByStatck(arr, 0, arr.length - 1);
		}
	}

	/**
	 * 非递归的快排：利用栈
	 * 
	 * @param arr
	 * @param low
	 * @param high
	 */
	private static void quickSortByStatck(int[] arr, int low, int high) {
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(high);// 先存右边界
		stack.push(low);// 再存左边界
		while (!stack.isEmpty()) {
			int left = stack.pop();// 先弹出左边界
			int right = stack.pop();// 再弹出右边界
			if (left < right) {
				int mid = QuickSort.getMiddle(arr, left, right);
				if (left < mid) {
					stack.push(mid - 1);
					stack.push(left);
				}
				if (right > mid) {
					stack.push(right);
					stack.push(mid + 1);
				}
			}
		}
	}

	/**
	 * 递归形式的分治排序法
	 * 
	 * @param arr
	 * @param low
	 * @param high
	 */
	private static void quickSort(int[] arr, int low, int high) {
		if (low < high) {
			// 将arr数组进行一分为2
			int middle = QuickSort.getMiddle(arr, low, high);
			// 对低字表进行递归排序
			QuickSort.quickSort(arr, low, middle - 1);
			// 对高字表进行递归排序
			QuickSort.quickSort(arr, middle + 1, high);
		}
	}

	private static int getMiddle(int[] arr, int low, int high) {
		// 数组的第一个作为中轴
		int tmp = arr[low];
		while (low < high) {

			while ((low < high) && (arr[high] >= tmp)) {
				high--;
			}
			// 比中轴小的记录移动到低端
			arr[low] = arr[high];

			while ((low < high) && (arr[low] <= tmp)) {
				low++;
			}
			// 比中轴大的移动到高端
			arr[high] = arr[low];
		}
		// 中轴记录到尾
		arr[low] = tmp;
		// 返回中轴位置
		return low;
	}

}
