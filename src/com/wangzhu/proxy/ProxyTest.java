package com.wangzhu.proxy;

public class ProxyTest {
	public static void main(String[] args) {
		// ����Ŀ��ҵ����
		IAnimal target = new Animal();
		// ����������
		IAnimal proxy = ProxyFactory.create(target);
		// ��������ʵ��
		proxy.say("Animal bark: is dog");
	}
}
