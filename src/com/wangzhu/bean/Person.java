package com.wangzhu.bean;

import com.wangzhu.annotation.Bean;

@Bean("qingyezhu")
public class Person {

    private String name = "qingyezhu";

    public String getName() {
	return name;
    }
}
