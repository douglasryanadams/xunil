package com.xunil.web.server.tomcat;

import com.xunil.web.main.Xunil;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created on 5/16/16.
 */
public class XunilServletContextListener implements ServletContextListener {
    private Xunil xunil = Xunil.getInstance();

    public void contextInitialized(ServletContextEvent servletContextEvent) {
        xunil.start();
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        xunil.stop();
    }
}
