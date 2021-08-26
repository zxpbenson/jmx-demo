package com.benson.note.jmx.demo.notify;

public class Event {

    public static final String[] EVENT_TYPE = {"a", "b", "c"};
    private int seq;
    private String msg;

    public Event(int seq, String msg) {
        this.seq = seq;
        this.msg = msg;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{\"seq\": ");
        sb.append(this.seq);
        sb.append(", \"msg\": \"");
        sb.append(this.msg);
        sb.append("\"}");
        return sb.toString();
    }

    public static void main(String[] args) {
        Event e = new Event(1, "msg 1");
        System.out.println(e);
    }
}
