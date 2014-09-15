package com.wangzhu.enumutil;

/**
 * 
 * @author wangzhu
 * @date 2014-9-15����5:19:02
 * 
 */
public enum EnumGender2 {
	// �˴���ö��ֵ������ö�Ӧ�Ĺ�����������
	MALE("��", 1), FEMALE("Ů", 2);
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
