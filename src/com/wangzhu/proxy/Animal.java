package com.wangzhu.proxy;

public class Animal implements IAnimal {

	@Override
	public void say(String msg) {
		System.out.println("Animal: " + msg);
	}

}
