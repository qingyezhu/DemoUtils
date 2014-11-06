package com.wangzhu.serial;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import com.wangzhu.SerialStudent;

/**
 * –Ú¡–ªØ≤‚ ‘
 * 
 * @author wangzhu
 * @date 2014-11-2œ¬ŒÁ12:24:50
 * 
 */
public class SerialTest {
	private static final String SERIAL_FILE_PATH = "serialTest.txt";

	public static void main(String[] args) throws IOException {
		SerialStudent stu = new SerialStudent(1, "Lili", 12);
		System.out.println("SerialTest: " + stu);
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		fos = new FileOutputStream(SerialTest.SERIAL_FILE_PATH);
		oos = new ObjectOutputStream(fos);
		oos.writeObject(stu);
		oos.flush();
		oos.close();
		fos.close();
	}
}
