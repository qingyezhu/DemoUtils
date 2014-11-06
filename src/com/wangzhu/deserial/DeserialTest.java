package com.wangzhu.deserial;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import com.wangzhu.SerialStudent;

/**
 * 反序列化
 * 
 * @author wangzhu
 * @date 2014-11-2下午12:24:31
 * 
 */
public class DeserialTest {
	private static final String SERIAL_FILE_PATH = "serialTest.txt";

	/**
	 * @param args
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static void main(String[] args) throws IOException,
			ClassNotFoundException {
		SerialStudent stu = null;
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		fis = new FileInputStream(DeserialTest.SERIAL_FILE_PATH);
		ois = new ObjectInputStream(fis);
		stu = (SerialStudent) ois.readObject();
		ois.close();
		fis.close();
		System.out.println("DeserialTest: " + stu);
	}

}
