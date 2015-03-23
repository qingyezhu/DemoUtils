package com.wangzhu.arrays;

import java.lang.reflect.Array;
import java.util.Arrays;

public class ChangeArray {
    public static void main(String[] args) {
	int[] arr1 = { 1, 4, 8, 10, 1, -1, 2 };
	System.out.println(Arrays.toString((int[]) resizeArray(arr1, 10)));
	System.out.println(Arrays.toString((int[]) resizeArray(arr1, 4)));
    }

    /**
     * 更改数组大小
     * 
     * @param oldArray
     * @param newSize
     * @return
     */
    private static Object resizeArray(Object oldArray, int newSize) {
	int oldSize = Array.getLength(oldArray);
	Class elementType = oldArray.getClass().getComponentType();
	Object newArray = Array.newInstance(elementType, newSize);
	int size = Math.min(oldSize, newSize);
	System.arraycopy(oldArray, 0, newArray, 0, size);
	return newArray;
    }
}
