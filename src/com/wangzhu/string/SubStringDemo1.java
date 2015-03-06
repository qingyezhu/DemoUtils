package com.wangzhu.string;

import java.io.UnsupportedEncodingException;

public class SubStringDemo1 {
    public static void main(String[] args) throws UnsupportedEncodingException {
	String str = "����j��abc";
	String charset = "UTF-8";
	printGetStrByLen(str, charset);
	charset = "GBK";
	printGetStrByLen(str, charset);

	str = "��ABC����d";
	charset = "UTF-8";
	printGetStrByLen(str, charset);
	charset = "GBK";
	printGetStrByLen(str, charset);

	// ����j��abc====���뷽ʽ===UTF-8
	// ����j��abc====0
	// 3===0
	//
	//
	// ����j��abc====1
	// 3===0
	//
	//
	// ����j��abc====2
	// 3===0
	//
	//
	// ����j��abc====3
	// 3===1
	// ��
	//
	// ����j��abc====4
	// 6===1
	// ��
	//
	// ����j��abc====5
	// 6===1
	// ��
	//
	// ����j��abc====6
	// 6===2
	// ����
	//
	// ����j��abc====7
	// 7===3
	// ����j
	//
	// ����j��abc====8
	// 10===3
	// ����j
	//
	// ����j��abc====9
	// 10===3
	// ����j
	//
	// ����j��abc====10
	// 10===4
	// ����j��
	//
	// ����j��abc====11
	// 11===5
	// ����j��a
	//
	// ����j��abc====12
	// 12===6
	// ����j��ab
	//
	// ����j��abc====13
	// ����j��abc
	//
	// ����j��abc====14
	// ����j��abc
	//
	// -------------
	// ����j��abc====���뷽ʽ===GBK
	// ����j��abc====0
	// 2===0
	//
	//
	// ����j��abc====1
	// 2===0
	//
	//
	// ����j��abc====2
	// 2===1
	// ��
	//
	// ����j��abc====3
	// 4===1
	// ��
	//
	// ����j��abc====4
	// 4===2
	// ����
	//
	// ����j��abc====5
	// 5===3
	// ����j
	//
	// ����j��abc====6
	// 7===3
	// ����j
	//
	// ����j��abc====7
	// 7===4
	// ����j��
	//
	// ����j��abc====8
	// 8===5
	// ����j��a
	//
	// ����j��abc====9
	// 9===6
	// ����j��ab
	//
	// ����j��abc====10
	// ����j��abc
	//
	// ����j��abc====11
	// ����j��abc
	//
	// -------------
	// ��ABC����d====���뷽ʽ===UTF-8
	// ��ABC����d====0
	// 3===0
	//
	//
	// ��ABC����d====1
	// 3===0
	//
	//
	// ��ABC����d====2
	// 3===0
	//
	//
	// ��ABC����d====3
	// 3===1
	// ��
	//
	// ��ABC����d====4
	// 4===2
	// ��A
	//
	// ��ABC����d====5
	// 5===3
	// ��AB
	//
	// ��ABC����d====6
	// 6===4
	// ��ABC
	//
	// ��ABC����d====7
	// 9===4
	// ��ABC
	//
	// ��ABC����d====8
	// 9===4
	// ��ABC
	//
	// ��ABC����d====9
	// 9===5
	// ��ABC��
	//
	// ��ABC����d====10
	// 12===5
	// ��ABC��
	//
	// ��ABC����d====11
	// 12===5
	// ��ABC��
	//
	// ��ABC����d====12
	// 12===6
	// ��ABC����
	//
	// ��ABC����d====13
	// ��ABC����d
	//
	// ��ABC����d====14
	// ��ABC����d
	//
	// -------------
	// ��ABC����d====���뷽ʽ===GBK
	// ��ABC����d====0
	// 2===0
	//
	//
	// ��ABC����d====1
	// 2===0
	//
	//
	// ��ABC����d====2
	// 2===1
	// ��
	//
	// ��ABC����d====3
	// 3===2
	// ��A
	//
	// ��ABC����d====4
	// 4===3
	// ��AB
	//
	// ��ABC����d====5
	// 5===4
	// ��ABC
	//
	// ��ABC����d====6
	// 7===4
	// ��ABC
	//
	// ��ABC����d====7
	// 7===5
	// ��ABC��
	//
	// ��ABC����d====8
	// 9===5
	// ��ABC��
	//
	// ��ABC����d====9
	// 9===6
	// ��ABC����
	//
	// ��ABC����d====10
	// ��ABC����d
	//
	// ��ABC����d====11
	// ��ABC����d
	//
	// -------------

    }

    public static void printGetStrByLen(String str, String charset)
	    throws UnsupportedEncodingException {
	System.out.println(str + "====���뷽ʽ===" + charset);
	for (int i = 0, len = str.getBytes(charset).length + 2; i < len; i++) {
	    System.out.println(getStrByLen(str, i, charset, false));
	    System.out.println();
	}
	System.out.println("-------------");
    }

    /**
     * ��ȡ�ַ�����UTF-8��3��byte��GBK��2��byte��
     * 
     * @param str
     * @param len
     * @param charset
     * @param flag
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String getStrByLen(String str, int len, String charset,
	    boolean flag) throws UnsupportedEncodingException {
	System.out.println(str + "====" + len);
	byte[] buf = str.getBytes(charset);
	int step = 1;
	if (charset.equals("UTF-8")) {
	    step = 2;
	}
	int length = buf.length;
	if (len >= length) {
	    return str;
	}
	int count = 0, size = 0;
	for (int i = 0; i < length; i++) {
	    count++;
	    if (buf[i] < 0) {
		// ����
		i += step;
		count += step;
	    }
	    size++;
	    if (count >= len) {
		if (!flag) {
		    if ((count > len) && (buf[i] < 0)) {
			size--;
		    }
		}
		System.out.println(count + "===" + size);
		return str.substring(0, size);
	    }
	}
	return null;
    }
}
