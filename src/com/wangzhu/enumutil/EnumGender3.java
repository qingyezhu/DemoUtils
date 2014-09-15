package com.wangzhu.enumutil;

/**
 * 实现接口的枚举类
 * 
 * @author wangzhu
 * @date 2014-9-8下午2:40:34
 * 
 */
public enum EnumGender3 implements GenderDesc {

	MALE("男") {

		@Override
		public void info() {
			System.out.println("男子标记");
		}
	},
	FEMALE("女") {

		@Override
		public void info() {
			System.out.println("女子标记");
		}
	};

	private final String name;

	private EnumGender3(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}
}
