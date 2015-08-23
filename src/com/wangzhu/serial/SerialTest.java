package com.wangzhu.serial;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
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

    public static void main(String[] args) throws Exception {
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

	Thread.sleep(10);
	FileInputStream fis = null;
	ObjectInputStream ois = null;
	fis = new FileInputStream(SERIAL_FILE_PATH);
	ois = new ObjectInputStream(fis);
	stu = (SerialStudent) ois.readObject();
	ois.close();
	fis.close();
	System.out.println("readStu===" + stu);
    }
}
