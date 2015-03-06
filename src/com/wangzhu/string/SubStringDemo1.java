package com.wangzhu.string;

import java.io.UnsupportedEncodingException;

public class SubStringDemo1 {
    public static void main(String[] args) throws UnsupportedEncodingException {
	String str = "我是j好abc";
	String charset = "UTF-8";
	printGetStrByLen(str, charset);
	charset = "GBK";
	printGetStrByLen(str, charset);

	str = "我ABC汉字d";
	charset = "UTF-8";
	printGetStrByLen(str, charset);
	charset = "GBK";
	printGetStrByLen(str, charset);

	// 我是j好abc====编码方式===UTF-8
	// 我是j好abc====0
	// 3===0
	//
	//
	// 我是j好abc====1
	// 3===0
	//
	//
	// 我是j好abc====2
	// 3===0
	//
	//
	// 我是j好abc====3
	// 3===1
	// 我
	//
	// 我是j好abc====4
	// 6===1
	// 我
	//
	// 我是j好abc====5
	// 6===1
	// 我
	//
	// 我是j好abc====6
	// 6===2
	// 我是
	//
	// 我是j好abc====7
	// 7===3
	// 我是j
	//
	// 我是j好abc====8
	// 10===3
	// 我是j
	//
	// 我是j好abc====9
	// 10===3
	// 我是j
	//
	// 我是j好abc====10
	// 10===4
	// 我是j好
	//
	// 我是j好abc====11
	// 11===5
	// 我是j好a
	//
	// 我是j好abc====12
	// 12===6
	// 我是j好ab
	//
	// 我是j好abc====13
	// 我是j好abc
	//
	// 我是j好abc====14
	// 我是j好abc
	//
	// -------------
	// 我是j好abc====编码方式===GBK
	// 我是j好abc====0
	// 2===0
	//
	//
	// 我是j好abc====1
	// 2===0
	//
	//
	// 我是j好abc====2
	// 2===1
	// 我
	//
	// 我是j好abc====3
	// 4===1
	// 我
	//
	// 我是j好abc====4
	// 4===2
	// 我是
	//
	// 我是j好abc====5
	// 5===3
	// 我是j
	//
	// 我是j好abc====6
	// 7===3
	// 我是j
	//
	// 我是j好abc====7
	// 7===4
	// 我是j好
	//
	// 我是j好abc====8
	// 8===5
	// 我是j好a
	//
	// 我是j好abc====9
	// 9===6
	// 我是j好ab
	//
	// 我是j好abc====10
	// 我是j好abc
	//
	// 我是j好abc====11
	// 我是j好abc
	//
	// -------------
	// 我ABC汉字d====编码方式===UTF-8
	// 我ABC汉字d====0
	// 3===0
	//
	//
	// 我ABC汉字d====1
	// 3===0
	//
	//
	// 我ABC汉字d====2
	// 3===0
	//
	//
	// 我ABC汉字d====3
	// 3===1
	// 我
	//
	// 我ABC汉字d====4
	// 4===2
	// 我A
	//
	// 我ABC汉字d====5
	// 5===3
	// 我AB
	//
	// 我ABC汉字d====6
	// 6===4
	// 我ABC
	//
	// 我ABC汉字d====7
	// 9===4
	// 我ABC
	//
	// 我ABC汉字d====8
	// 9===4
	// 我ABC
	//
	// 我ABC汉字d====9
	// 9===5
	// 我ABC汉
	//
	// 我ABC汉字d====10
	// 12===5
	// 我ABC汉
	//
	// 我ABC汉字d====11
	// 12===5
	// 我ABC汉
	//
	// 我ABC汉字d====12
	// 12===6
	// 我ABC汉字
	//
	// 我ABC汉字d====13
	// 我ABC汉字d
	//
	// 我ABC汉字d====14
	// 我ABC汉字d
	//
	// -------------
	// 我ABC汉字d====编码方式===GBK
	// 我ABC汉字d====0
	// 2===0
	//
	//
	// 我ABC汉字d====1
	// 2===0
	//
	//
	// 我ABC汉字d====2
	// 2===1
	// 我
	//
	// 我ABC汉字d====3
	// 3===2
	// 我A
	//
	// 我ABC汉字d====4
	// 4===3
	// 我AB
	//
	// 我ABC汉字d====5
	// 5===4
	// 我ABC
	//
	// 我ABC汉字d====6
	// 7===4
	// 我ABC
	//
	// 我ABC汉字d====7
	// 7===5
	// 我ABC汉
	//
	// 我ABC汉字d====8
	// 9===5
	// 我ABC汉
	//
	// 我ABC汉字d====9
	// 9===6
	// 我ABC汉字
	//
	// 我ABC汉字d====10
	// 我ABC汉字d
	//
	// 我ABC汉字d====11
	// 我ABC汉字d
	//
	// -------------

    }

    public static void printGetStrByLen(String str, String charset)
	    throws UnsupportedEncodingException {
	System.out.println(str + "====编码方式===" + charset);
	for (int i = 0, len = str.getBytes(charset).length + 2; i < len; i++) {
	    System.out.println(getStrByLen(str, i, charset, false));
	    System.out.println();
	}
	System.out.println("-------------");
    }

    /**
     * 截取字符串【UTF-8：3个byte，GBK：2个byte】
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
		// 汉字
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
