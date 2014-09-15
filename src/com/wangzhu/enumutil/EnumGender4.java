package com.wangzhu.enumutil;

/**
 * 实现接口的枚举类
 * 
 * @author wangzhu
 * @date 2014-9-15下午5:34:52
 * 
 */
public enum EnumGender4 implements GenderDesc {
	MALE("男"), FEMALE("女");
	private final String name;

	private EnumGender4(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	@Override
	public void info() {
		System.out.println("这是。。。" + this.name);
	}
}
