package com.wangzhu;

import java.util.UUID;

public class UuidGenerator {

	/**
	 * 获取32位的UUid<br/>
	 * 例如：<br/>
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
	 * 测试
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("getUUid: " + UuidGenerator.getUUid());
	}
}
