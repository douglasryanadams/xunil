package com.xunil.web.main;

import com.xunil.web.presentation.BlogURI;
import com.xunil.web.presentation.FileURI;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by on 5/17/16.
 */

@ApplicationPath("/")
public class XunilApplication extends Application {

    private static final Logger log = LogManager.getLogger(XunilApplication.class.getName());

    @Override
    public Set<Class<?>> getClasses() {
        log.info("Loading web app classes");
        Set<Class<?>> classes = super.getClasses();
        if (classes == null || classes.size() == 0) classes = new HashSet<Class<?>>();
        log.info("    Loading Resources");
        log.debug("      - BlogURI");
        classes.add(BlogURI.class);
        log.debug("      - FileURI");
        classes.add(FileURI.class);
        return classes;
    }

    @Override
    public Map<String, Object> getProperties() {
        // Might be handy: https://jersey.java.net/documentation/latest/appendix-properties.html
        return super.getProperties();
    }
}
