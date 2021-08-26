package com.benson.note.jmx.demo.notify;

import javax.management.Notification;
import javax.management.NotificationListener;

public class EventNotificationListener implements NotificationListener {

    private static int idSeq = 0;

    private int id;

    public EventNotificationListener() {
        id = ++idSeq;
    }

    @Override
    public void handleNotification(Notification notification, Object handback) {
        System.out.println("EventNotificationListener " + id + " receive event : " + notification.getUserData());
    }
}
