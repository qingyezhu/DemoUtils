package com.wangzhu.enumutil;

/**
 * 应用枚举实现的工厂方法
 * 
 * @author wangzhu
 * @date 2015-1-4下午4:36:07
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
