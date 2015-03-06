package com.wangzhu.bean;

import com.wangzhu.annotation.Bean;
import com.wangzhu.annotation.Resource;

@Bean("action")
public class Action {

    @Resource("qingyezhu")
    private Person person;

    public void setPerson(Person person) {
	this.person = person;
    }

    public void doAction() {
	System.out.println("My name is " + person.getName());
    }

}
