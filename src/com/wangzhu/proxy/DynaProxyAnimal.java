package com.wangzhu.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DynaProxyAnimal implements InvocationHandler {

	/**
	 * ������Ķ���
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
			// ִ��ԭ���ķ���֮ǰ��¼��־
			AnimalLogger.start();

			// JVMͨ���������ִ��ԭ���ķ�����������ƣ�
			ret = method.invoke(this.delegate, args);

			// ִ��ԭ���ķ���֮���¼��־
			AnimalLogger.end();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// ���ط����ķ���ֵ��������
		return ret;
	}

}
