package com.benson.note.jmx.demo.notify;

import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;
import java.util.Random;

public class EventSource extends NotificationBroadcasterSupport implements EventSourceMBean {

    private volatile int seq = 0;

    @Override
    public void triggerEvent(String tip) {
        Random rand = new Random();
        int randVal = rand.nextInt(100);
        int seq = this.seq++;
        String msg = tip == null ? "msg " + seq : tip;
        Notification notify = new Notification(Event.EVENT_TYPE[randVal % 3], this, seq, System.currentTimeMillis(), msg);
        notify.setUserData(new Event(seq, msg));
        sendNotification(notify);
    }

    @Override
    public void triggerEvent() {
        triggerEvent(null);
    }
}
