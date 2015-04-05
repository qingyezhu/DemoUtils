package com.wangzhu.designpattern.template;

public abstract class Diagram {
    protected char c;

    public Diagram(char c) {
	this.c = c;
    }

    abstract protected void print(int size);

    abstract protected void printContent(String msg);

    public final void display(String msg) {
	int len = msg.getBytes().length;
	this.print(len);
	this.printContent(msg);
	this.print(len);
    }
}