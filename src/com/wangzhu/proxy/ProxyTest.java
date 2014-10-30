package com.wangzhu.proxy;

public class ProxyTest {
	public static void main(String[] args) {
		// 创建目标业务类
		IAnimal target = new Animal();
		// 创建代理类
		IAnimal proxy = ProxyFactory.create(target);
		// 操作代理实例
		proxy.say("Animal bark: is dog");
	}
}
