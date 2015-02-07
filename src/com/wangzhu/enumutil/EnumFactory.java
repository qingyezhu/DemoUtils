package com.wangzhu.enumutil;

/**
 * Ӧ��ö��ʵ�ֵĹ�������
 * 
 * @author wangzhu
 * @date 2015-1-4����4:36:07
 * 
 */
public enum EnumFactory {
	Cat {
		@Override
		public Animal create() {
			return new Cat();
		}
	},
	Dog {
		@Override
		public Animal create() {
			return new Dog();
		}
	};

	public abstract Animal create();

}

class Animal {

}

class Cat extends Animal {

}

class Dog extends Animal {

}
