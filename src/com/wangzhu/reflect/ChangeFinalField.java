package com.wangzhu.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class ChangeFinalField {
    public static void setFinalStatic(Field field, Object newValue)
	    throws Exception {
	field.setAccessible(true);
	Field modifiersField = Field.class.getDeclaredField("modifiers");
	modifiersField.setAccessible(true);
	modifiersField.setInt(field, field.getModifiers() & (~Modifier.FINAL));
	field.set(null, newValue);
    }

    public static void main(String[] args) throws Exception {
	System.out.println("start==boolean==" + Bean.BOOLEAN_VALUE);
	setFinalStatic(Bean.class.getDeclaredField("BOOLEAN_VALUE"), true);
	System.out.println("end==boolean==" + Bean.BOOLEAN_VALUE);

	System.out.println("start==object==" + Bean.OBJECT_VALUE);
	setFinalStatic(Bean.class.getDeclaredField("OBJECT_VALUE"), "----====");
	System.out.println("start==object==" + Bean.OBJECT_VALUE);

	System.out.println("start==int==" + Bean.getInt());
	setFinalStatic(Bean.class.getDeclaredField("INT_VALUE"), -12);
	System.out.println("end==int==" + Bean.getInt());

	System.out.println("start==Integer==" + Bean.getInteger());
	setFinalStatic(Bean.class.getDeclaredField("INTEGER_VALUE"), -100);
	System.out.println("end==Integer==" + Bean.getInteger());

	System.out.println("start==string==" + Bean.getString());
	setFinalStatic(Bean.class.getDeclaredField("STRING_VALUE"), "str===str");
	System.out.println("end==string==" + Bean.getString());

    }
}

class Bean {
    private static final int INT_VALUE = 12;
    private static final Integer INTEGER_VALUE = 100;
    public final static Boolean BOOLEAN_VALUE = false;
    private final static String STRING_VALUE = "string===";
    public final static Object OBJECT_VALUE = "234";

    public static int getInt() {
	return INT_VALUE;
    }

    public static Integer getInteger() {
	return INTEGER_VALUE;
    }

    public static String getString() {
	return STRING_VALUE;
    }

}