package com.wangzhu.enumutil;

/**
 * ʵ�ֽӿڵ�ö����
 * 
 * @author wangzhu
 * @date 2014-9-8����2:40:34
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
