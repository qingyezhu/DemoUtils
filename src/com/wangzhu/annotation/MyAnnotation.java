package com.wangzhu.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 注解：<br/>
 * 
 * //@Target：表示该注解可以用于什么地方。<br/>
 * 可能的ElementType参数有：<br/>
 * CONSTRUCTOR：构造器的声明<br/>
 * FIELD：域声明（包括enum实例）<br/>
 * LOCAL_VARIABLE：局部变量声明<br/>
 * METHOD：方法声明<br/>
 * PACKAGE：包声明<br/>
 * PARAMETER：参数声明<br/>
 * TYPE：类、接口（包括注解类型）或枚举声明<br/>
 * 
 * //@Retention：表示需要在什么级别保存该注解信息。<br/>
 * 可选的RetentionPolicy参数包括：<br/>
 * SOURCE：这届将被编译器丢弃<br/>
 * CLASS：注解在文件class中可用，但会被VM丢弃<br/>
 * RUNTIME：VM将在运行期间保留注解，因此可以通过放射机制读取注解的信息<br/>
 * 
 * //@Documented：将注解包含在JavaDoc中
 * 
 * //@Inherited：允许子类继承父类中的注解
 * 
 * @author wangzhu
 * @date 2014-10-30下午2:46:54
 * 
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotation {
	public String id();

	public String description() default "没有描述信息";
}
