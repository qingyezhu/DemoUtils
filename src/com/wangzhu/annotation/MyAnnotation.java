package com.wangzhu.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ע�⣺<br/>
 * 
 * //@Target����ʾ��ע���������ʲô�ط���<br/>
 * ���ܵ�ElementType�����У�<br/>
 * CONSTRUCTOR��������������<br/>
 * FIELD��������������enumʵ����<br/>
 * LOCAL_VARIABLE���ֲ���������<br/>
 * METHOD����������<br/>
 * PACKAGE��������<br/>
 * PARAMETER����������<br/>
 * TYPE���ࡢ�ӿڣ�����ע�����ͣ���ö������<br/>
 * 
 * //@Retention����ʾ��Ҫ��ʲô���𱣴��ע����Ϣ��<br/>
 * ��ѡ��RetentionPolicy����������<br/>
 * SOURCE����콫������������<br/>
 * CLASS��ע�����ļ�class�п��ã����ᱻVM����<br/>
 * RUNTIME��VM���������ڼ䱣��ע�⣬��˿���ͨ��������ƶ�ȡע�����Ϣ<br/>
 * 
 * //@Documented����ע�������JavaDoc��
 * 
 * //@Inherited����������̳и����е�ע��
 * 
 * @author wangzhu
 * @date 2014-10-30����2:46:54
 * 
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotation {
	public String id();

	public String description() default "û��������Ϣ";
}
