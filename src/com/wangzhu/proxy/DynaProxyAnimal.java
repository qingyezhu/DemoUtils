package com.wangzhu.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DynaProxyAnimal implements InvocationHandler {

	/**
	 * 被代理的对象
	 */
	private Object delegate;

	public DynaProxyAnimal(Object delegate) {
		this.delegate = delegate;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		Object ret = null;
		try {
			// 执行原来的方法之前记录日志
			AnimalLogger.start();

			// JVM通过这条语句执行原来的方法（反射机制）
			ret = method.invoke(this.delegate, args);

			// 执行原来的方法之后记录日志
			AnimalLogger.end();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 返回方法的返回值给调用者
		return ret;
	}

}
