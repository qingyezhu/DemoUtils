package com.wangzhu.enumutil;

/**
 * ö�ٲ�����
 * 
 * @author wangzhu
 * @date 2014-9-15����5:20:03
 * 
 */
public class GenderTest {

	public static void main(String[] args) {
		System.out.println(EnumGender1.FEMALE);
		System.out.println(EnumGender2.getName(1));

		EnumGender3.FEMALE.info();
		EnumGender4.MALE.info();

		System.out.println(EnumOption.PLUS.eval(10, 20));
	}
}
