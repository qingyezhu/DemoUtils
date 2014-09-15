package com.wangzhu.enumutil;

/**
 * 
 * @author wangzhu
 * @date 2014-9-15下午5:34:22
 * 
 */
public enum EnumGender1 {
	// 此处的枚举值必须调用对应的构造器来创建
	MALE("男"), FEMALE("女");
	private final String name;

	private EnumGender1(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}
}
