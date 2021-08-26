package com.benson.note.jmx.demo.notify;

import javax.management.*;
import java.lang.management.ManagementFactory;

public class Main {
    public static void main(String[] args) throws MalformedObjectNameException, NotCompliantMBeanException, InstanceAlreadyExistsException, MBeanRegistrationException, InterruptedException, InstanceNotFoundException {
        ObjectName esAName = new ObjectName("com.benson.note.jmx.demo.notify:name=EventSource");
        EventSource es = new EventSource();

        MBeanServer server = ManagementFactory.getPlatformMBeanServer();
        server.registerMBean(es, esAName);

        //es.addNotificationListener(new EventNotificationListener(), new EventNotificationFilter(Event.EVENT_TYPE[0]), null);//等价
        server.addNotificationListener(esAName, new EventNotificationListener(), new EventNotificationFilter(Event.EVENT_TYPE[0]), null);
        server.addNotificationListener(esAName, new EventNotificationListener(), new EventNotificationFilter(Event.EVENT_TYPE[1]), null);
        server.addNotificationListener(esAName, new EventNotificationListener(), new EventNotificationFilter(Event.EVENT_TYPE[2]), null);

        Thread.sleep(500000);
    }
}
