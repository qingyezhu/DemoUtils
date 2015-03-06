package com.wangzhu.proxy;

import java.lang.reflect.Proxy;

/**
 * ��������
 * 
 * @author wangzhu
 * @date 2014-10-29����2:27:04
 * 
 */
public class ProxyFactory {
    @SuppressWarnings("unchecked")
    public static <T extends IAnimal> T create(Class<T> clazz) throws Exception {
	// ����������
	T object = (T) Proxy.newProxyInstance(clazz.getClassLoader(),// ����Ŀ�������װ�������������������װ����һ��
		clazz.getInterfaces(),// ����Ŀ����ʵ�ֵĽӿڣ���֤��϶��ɵĴ�����Ҳʵ������Щ�ӿ�
		new DynaProxyAnimal(clazz.newInstance()));// ָ��˭ȥ�������Ķ���
	return object;
    }

    @SuppressWarnings("unchecked")
    public static <T extends IAnimal> T create(T t) {
	// ����������
	// T obj = (T) Proxy.newProxyInstance(t.getClass().getClassLoader(), //
	// ����Ŀ�������װ�������������������װ����һ��
	// t.getClass().getInterfaces(), // ����Ŀ����ʵ�ֵĽӿڣ���֤��϶��ɵĴ�����Ҳʵ������Щ�ӿ�
	// new DynaProxyAnimal(t));// ָ��˭ȥ�������Ķ���
	// return obj;

	return (T) new DynaProxyAnimal().bind(t);
    }

}
