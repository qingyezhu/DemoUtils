package com.wangzhu;

import java.util.UUID;

public class UuidGenerator {

	/**
	 * ��ȡ32λ��UUid<br/>
	 * ���磺<br/>
	 * f47c898a34934842b599f3ecaf782413
	 * 
	 * @return
	 */
	public static String getUUid() {
		String ret = null;
		UUID uuid = UUID.randomUUID();
		ret = uuid.toString();
		return ret.replaceAll("-", "");
	}

	/**
	 * ����
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("getUUid: " + UuidGenerator.getUUid());
	}
}
