package com.wangzhu;

import java.util.Arrays;
import java.util.List;

public class SqlHelper {

	/**
	 * ��ȡSql���������In��䣺��And��Or������<br/>
	 * ���磺<br/>
	 * and (t.userId = 'id0' or t.userId = 'id1' or t.userId = 'id2')
	 * 
	 * @param itemArr
	 *            ����ֵ�ַ���
	 * @param itemStr
	 *            ��������
	 * @return
	 */
	public static StringBuffer getInSql(String[] itemArr, String itemStr) {
		return SqlHelper.getInSql(Arrays.asList(itemArr), itemStr);
	}

	/**
	 * ��ȡSql���������In��䣺��And��Or������<br/>
	 * ���磺<br/>
	 * and (t.userId = 'id0' or t.userId = 'id1' or t.userId = 'id2')
	 * 
	 * @param itemList
	 *            ����ֵ�б�
	 * @param itemStr
	 *            ��������
	 * @return
	 */
	public static StringBuffer getInSql(List<String> itemList, String itemStr) {
		StringBuffer sqlSb = new StringBuffer();
		for (int i = 0, size = itemList.size(); i < size; i++) {
			if (i == 0) {
				sqlSb.append(" and (");
			} else {
				sqlSb.append(" or ");
			}
			sqlSb.append(itemStr).append(" = '").append(itemList.get(i))
					.append("'");
			if (i == (size - 1)) {
				sqlSb.append(")");
			}
		}
		return sqlSb;
	}

	/**
	 * ����
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("getInSql:\n"
				+ SqlHelper.getInSql(new String[] { "id0", "id1", "id2" },
						"t.userId"));
	}
}
