package com.wangzhu.enumutil;

/**
 * 
 * @author wangzhu
 * @date 2014-9-15下午5:19:02
 * 
 */
public enum EnumGender2 {
	// 此处的枚举值必须调用对应的构造器来创建
	MALE("男", 1), FEMALE("女", 2);
	private String name;
	private int index;

	private EnumGender2(String name, int index) {
		this.name = name;
		this.index = index;
	}

	public static String getName(int index) {
		for (EnumGender2 gender : EnumGender2.values()) {
			if (index == gender.index) {
				return gender.name;
			}
		}
		return null;
	}
}
