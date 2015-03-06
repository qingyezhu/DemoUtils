package com.wangzhu.bean;

import java.util.Set;

import com.wangzhu.annotation.BeanFactory;
import com.wangzhu.annotation.Scanner;

public class TestAnnotation {
    public static void main(String[] args) {
	Set<Class<?>> clazzs = new Scanner().scanPackage("com.wangzhu.bean");
	BeanFactory instance = BeanFactory.getInstance();
	instance.init(clazzs);
	Action action = (Action) instance.getBean("action");
	action.doAction();
    }

}
