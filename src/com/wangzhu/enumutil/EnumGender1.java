package com.wangzhu.enumutil;

/**
 * 
 * @author wangzhu
 * @date 2014-9-15����5:26:20
 * 
 */
public enum EnumGender1 {
	// �˴���ö��ֵ������ö�Ӧ�Ĺ�����������
	MALE("��"), FEMALE("Ů");
	private final String name;

	private EnumGender1(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}
}
