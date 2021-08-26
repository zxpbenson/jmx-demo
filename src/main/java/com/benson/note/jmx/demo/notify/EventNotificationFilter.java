package com.benson.note.jmx.demo.notify;

import javax.management.Notification;
import javax.management.NotificationFilter;

public class EventNotificationFilter implements NotificationFilter {

    private String type;

    public EventNotificationFilter(String type) {
        this.type = type;
    }

    @Override
    public boolean isNotificationEnabled(Notification notification) {
        return this.type.equals(notification.getType());
    }
}
