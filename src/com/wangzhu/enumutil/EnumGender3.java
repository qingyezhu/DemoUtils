package com.wangzhu.enumutil;

/**
 * ʵ�ֽӿڵ�ö����
 * 
 * @author wangzhu
 * @date 2014-9-8����2:40:34
 * 
 */
public enum EnumGender3 implements GenderDesc {

	MALE("��") {

		@Override
		public void info() {
			System.out.println("���ӱ��");
		}
	},
	FEMALE("Ů") {

		@Override
		public void info() {
			System.out.println("Ů�ӱ��");
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