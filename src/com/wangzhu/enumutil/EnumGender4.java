package com.wangzhu.enumutil;

/**
 * ʵ�ֽӿڵ�ö����
 * 
 * @author wangzhu
 * @date 2014-9-15����5:34:52
 * 
 */
public enum EnumGender4 implements GenderDesc {
	MALE("��"), FEMALE("Ů");
	private final String name;

	private EnumGender4(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	@Override
	public void info() {
		System.out.println("���ǡ�����" + this.name);
	}
}
