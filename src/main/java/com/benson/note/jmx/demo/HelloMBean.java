package com.benson.note.jmx.demo;

import java.util.List;

public interface HelloMBean {

    public static final String MBEAN_NAME = "Hello";

    public String getName();

    public void setName(String name);

    public String getAge();

    public void setAge(String age);

    public void helloWorld();

    public List<Msg> helloWorld(String str) throws Exception;

    public void getTelephone();
}