package com.benson.note.jmx.demo;

import java.io.Serializable;

public class Msg implements Serializable {
    private String log;

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }
}
