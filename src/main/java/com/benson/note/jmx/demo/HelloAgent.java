package com.benson.note.jmx.demo;

import com.sun.jdmk.comm.HtmlAdaptorServer;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import javax.management.*;
import javax.management.remote.JMXConnectorServer;
import javax.management.remote.JMXConnectorServerFactory;
import javax.management.remote.JMXServiceURL;

public class HelloAgent {

    public static final String DOMAIN = "com.benson.note.jmx.demo";
    public static final String HELLO_MBEAN_NAME = String.format("%s:name=%s", DOMAIN, HelloMBean.MBEAN_NAME);

    public static void main(String[] args) throws InterruptedException, MalformedObjectNameException, NotCompliantMBeanException, InstanceAlreadyExistsException, MBeanRegistrationException {
        //jconsole();
        //htmlAgent();
        jmxrmi();
    }

    /*
     * JConsole
     * */
    public static void jconsole() throws MalformedObjectNameException, NotCompliantMBeanException, InstanceAlreadyExistsException, MBeanRegistrationException, InterruptedException {
        MBeanServer server = ManagementFactory.getPlatformMBeanServer();

        ObjectName helloName = new ObjectName(HELLO_MBEAN_NAME);
        //create mbean and register mbean
        server.registerMBean(new Hello(), helloName);

        Thread.sleep(60 * 60 * 1000);
    }

    /*
     * JConsole or WebBrowser
     * */
    public static void htmlAgent() throws NotCompliantMBeanException, InstanceAlreadyExistsException, MBeanRegistrationException, MalformedObjectNameException {
        MBeanServer server = ManagementFactory.getPlatformMBeanServer();

        ObjectName helloName = new ObjectName(HELLO_MBEAN_NAME);
        //create mbean and register mbean
        server.registerMBean(new Hello(), helloName);

        ObjectName adapterName = new ObjectName(DOMAIN + ":name=HTMLAdapter,port=8082");
        HtmlAdaptorServer adapter = new HtmlAdaptorServer();
        server.registerMBean(adapter, adapterName);
        adapter.start();
    }

    /*
     * JConsole or JMXClientApp
     * */
    public static void jmxrmi() throws NotCompliantMBeanException, InstanceAlreadyExistsException, MBeanRegistrationException, MalformedObjectNameException {
        MBeanServer server = ManagementFactory.getPlatformMBeanServer();
        ObjectName helloName = new ObjectName(HELLO_MBEAN_NAME);
        //create mbean and register mbean
        server.registerMBean(new Hello(), helloName);
        try {
            //这个步骤很重要，注册一个端口，绑定url后用于客户端通过rmi方式连接JMXConnectorServer
            LocateRegistry.createRegistry(9999);
            //URL路径的结尾可以随意指定，但如果需要用Jconsole来进行连接，则必须使用jmxrmi
            JMXServiceURL url = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://localhost:9999/jmxrmi");
            JMXConnectorServer jcs = JMXConnectorServerFactory.newJMXConnectorServer(url, null, server);
            System.out.println("begin rmi start");
            jcs.start();
            System.out.println("rmi start");
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}