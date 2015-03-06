package com.wangzhu.proxy;

import java.lang.reflect.Method;

import com.wangzhu.dateutil.DateUtil;

public class AnimalLogger {

    public static void start(Method method) {
	pringMsg(DateUtil.getYMDHMST(), method.getName(), "start");
    }

    public static void end(Method method) {
	pringMsg(DateUtil.getYMDHMST(), method.getName(), "end");
    }

    private static void pringMsg(String prefix, String msg, String suffix) {
	System.out.println(String.format("��ǰʱ�䣺%s ��������%s ״̬��%s", prefix, msg,
		suffix));
    }
}
