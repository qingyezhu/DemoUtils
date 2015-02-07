package com.wangzhu.string;

public class StringUtils {
    public static String underlineToCamel(String str) {
	StringBuilder accum = new StringBuilder();
	for (int i = 0, len = str.length(); i < len; i++) {
	    char c = str.charAt(i);
	    if (c == '_') {
		i++;
		c = str.charAt(i);
		accum.append(Character.toUpperCase(c));
	    } else {
		accum.append(Character.toLowerCase(c));
	    }
	}
	return accum.toString();
    }

    /**
     * �ж��ַ����Ƿ�Ϊ��
     * 
     * @param cs
     * @return
     */
    public static boolean isEmpty(final CharSequence cs) {
	return (cs == null) || (cs.length() == 0);
    }

    /**
     * �ж��ַ����Ƿ�ǿ�
     * 
     * @param cs
     * @return
     */
    public static boolean isNotEmpty(final CharSequence cs) {
	return !StringUtils.isEmpty(cs);
    }

}
