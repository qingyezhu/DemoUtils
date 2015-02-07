package com.wangzhu.enumutil;


/**
 * 枚举测试类
 * 
 * @author wangzhu
 * @date 2014-9-15下午5:35:28
 * 
 */
public class GenderTest {

	public static void main(String[] args) {
		System.out.println(EnumGender1.FEMALE);
		System.out.println(EnumGender2.getName(1));

		EnumGender3.FEMALE.info();
		EnumGender4.MALE.info();

		System.out.println(EnumOption.PLUS.eval(10, 20));

		// 编译通过，运行异常，因为在switch的时候真正使用的是enumGender3.ordinal()而不是enumGender3
		EnumGender3 enumGender3 = null;
		switch (enumGender3) {
		case MALE:
			System.out.println("EnumGender1 男");
			break;
		case FEMALE:
			System.out.println("EnumGender1 女");
			break;
		default:
			System.out.println("EnumGender1 其他");
			break;
		}
	}
}
