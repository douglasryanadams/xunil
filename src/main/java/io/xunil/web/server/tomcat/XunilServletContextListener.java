package io.xunil.web.server.tomcat;

import io.xunil.web.main.Xunil;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Created on 5/16/16.
 */
@WebListener
public class XunilServletContextListener implements ServletContextListener {
    private Xunil xunil = Xunil.getInstance();

    public void contextInitialized(ServletContextEvent servletContextEvent) {
        xunil.start();
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        xunil.stop();
    }
}
