package com.wangzhu.proxy;

import java.lang.reflect.Proxy;

/**
 * 代理工厂类
 * 
 * @author wangzhu
 * @date 2014-10-29下午2:27:04
 * 
 */
public class ProxyFactory {
    @SuppressWarnings("unchecked")
    public static <T extends IAnimal> T create(Class<T> clazz) throws Exception {
	// 创建代理类
	T object = (T) Proxy.newProxyInstance(clazz.getClassLoader(),// 返回目标类的类装载器，保持两个类的类装载器一样
		clazz.getInterfaces(),// 返回目标类实现的接口，保证组合而成的代理类也实现了这些接口
		new DynaProxyAnimal(clazz.newInstance()));// 指派谁去处理方法的对象
	return object;
    }

    @SuppressWarnings("unchecked")
    public static <T extends IAnimal> T create(T t) {
	// 创建代理类
	// T obj = (T) Proxy.newProxyInstance(t.getClass().getClassLoader(), //
	// 返回目标类的类装载器，保持两个类的类装载器一样
	// t.getClass().getInterfaces(), // 返回目标类实现的接口，保证组合而成的代理类也实现了这些接口
	// new DynaProxyAnimal(t));// 指派谁去处理方法的对象
	// return obj;

	return (T) new DynaProxyAnimal().bind(t);
    }

}
