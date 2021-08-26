package com.benson.note.jmx.demo.notify;

public interface EventSourceMBean {
    void triggerEvent(String tip);
    void triggerEvent();
}
