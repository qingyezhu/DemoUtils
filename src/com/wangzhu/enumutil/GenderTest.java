package com.wangzhu.enumutil;


/**
 * ö�ٲ�����
 * 
 * @author wangzhu
 * @date 2014-9-15����5:35:28
 * 
 */
public class GenderTest {

	public static void main(String[] args) {
		System.out.println(EnumGender1.FEMALE);
		System.out.println(EnumGender2.getName(1));

		EnumGender3.FEMALE.info();
		EnumGender4.MALE.info();

		System.out.println(EnumOption.PLUS.eval(10, 20));

		// ����ͨ���������쳣����Ϊ��switch��ʱ������ʹ�õ���enumGender3.ordinal()������enumGender3
		EnumGender3 enumGender3 = null;
		switch (enumGender3) {
		case MALE:
			System.out.println("EnumGender1 ��");
			break;
		case FEMALE:
			System.out.println("EnumGender1 Ů");
			break;
		default:
			System.out.println("EnumGender1 ����");
			break;
		}
	}
}
