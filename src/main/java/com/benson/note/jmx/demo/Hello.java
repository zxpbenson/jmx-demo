package com.benson.note.jmx.demo;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/*
 * 该类名称必须与实现的接口的前缀保持一致（即MBean前面的名称
 */
public class Hello implements HelloMBean {
    private String name;

    //private String age;

    public String getName() {
        System.out.println("get name : " + name);
        return name;
    }

    public void setName(String name) {
        System.out.println("set name : " + name);
        this.name = name;
    }

    //getter、setter 一律被归为属性，无论是否真的有对应字段
    public String getAge() {
        int age = new Random().nextInt();
        System.out.println("get age : " + age);
        return "" + age;
    }

    public void setAge(String age) {
        System.out.println("set age : " + age);
        String localAge = age;
    }

    //非getter/setter一律归为操作
    public void getTelephone() {
        System.out.println("get Telephone");
    }

    public void helloWorld() {
        System.out.println("hello world");
    }

    private List<Msg> msgList = Collections.synchronizedList(new LinkedList<Msg>());

    public List<Msg> helloWorld(String str) throws Exception {
        if (str == null) {
            throw new Exception("parameter null");
        }
        if (str.length() <= 1) {
            throw new Exception("parameter illegal : " + str);
        }
        System.out.println("helloWorld:" + str);

        Msg msg = new Msg();
        msg.setLog(str);
        msgList.add(msg);

        for (Msg one : msgList) {
            System.out.println("history hello world : " + one.getLog());
        }

        return msgList;
    }

}