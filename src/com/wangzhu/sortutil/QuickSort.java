package com.wangzhu.sortutil;

import java.util.Arrays;
import java.util.Stack;

/**
 * ��������<br/>
 * ѡ��һ����׼Ԫ�أ�ͨ��ѡ���һ��Ԫ�ػ����һ��Ԫ�أ�ͨ��һ��ɨ�裬���������зֳ������֣�<br/>
 * һ���ֱȻ�׼Ԫ��С��һ���ִ��ڵ��ڻ�׼Ԫ�أ���ʱ��׼Ԫ�������ź�������ȷλ�ã�<br/>
 * Ȼ������ͬ���ķ����ݹ�����򻮷ֵ������֡�<br/>
 * ʱ�临�Ӷȣ�O(nlogn)<br/>
 * 
 * @author wangzhu
 * @date 2014-9-27����11:35:20
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
			// �鿴��������Ϊ�ջ����鳤��Ϊ0
			QuickSort.quickSortByStatck(arr, 0, arr.length - 1);
		}
	}

	/**
	 * �ǵݹ�Ŀ��ţ�����ջ
	 * 
	 * @param arr
	 * @param low
	 * @param high
	 */
	private static void quickSortByStatck(int[] arr, int low, int high) {
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(high);// �ȴ��ұ߽�
		stack.push(low);// �ٴ���߽�
		while (!stack.isEmpty()) {
			int left = stack.pop();// �ȵ�����߽�
			int right = stack.pop();// �ٵ����ұ߽�
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
	 * �ݹ���ʽ�ķ�������
	 * 
	 * @param arr
	 * @param low
	 * @param high
	 */
	private static void quickSort(int[] arr, int low, int high) {
		if (low < high) {
			// ��arr�������һ��Ϊ2
			int middle = QuickSort.getMiddle(arr, low, high);
			// �Ե��ֱ���еݹ�����
			QuickSort.quickSort(arr, low, middle - 1);
			// �Ը��ֱ���еݹ�����
			QuickSort.quickSort(arr, middle + 1, high);
		}
	}

	private static int getMiddle(int[] arr, int low, int high) {
		// ����ĵ�һ����Ϊ����
		int tmp = arr[low];
		while (low < high) {

			while ((low < high) && (arr[high] >= tmp)) {
				high--;
			}
			// ������С�ļ�¼�ƶ����Ͷ�
			arr[low] = arr[high];

			while ((low < high) && (arr[low] <= tmp)) {
				low++;
			}
			// ���������ƶ����߶�
			arr[high] = arr[low];
		}
		// �����¼��β
		arr[low] = tmp;
		// ��������λ��
		return low;
	}

}
