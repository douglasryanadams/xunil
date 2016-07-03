package io.xunil.web.main;

import io.xunil.web.presentation.uri.BlogURI;
import io.xunil.web.presentation.uri.ChatURI;
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

@ApplicationPath("/api")
public class XunilApplication extends Application {

    private static final Logger log = LogManager.getLogger(XunilApplication.class);

    @Override
    public Set<Class<?>> getClasses() {
        log.info("Loading web app classes");
        Set<Class<?>> classes = super.getClasses();
        if (classes == null || classes.size() == 0) classes = new HashSet<>();
        log.info("    Loading Resources");
        log.debug("      - BlogURI");
        classes.add(BlogURI.class);
        log.debug("      - ChatURI");
        classes.add(ChatURI.class);
        return classes;
    }

    @Override
    public Map<String, Object> getProperties() {
        // Might be handy: https://jersey.java.net/documentation/latest/appendix-properties.html
        return super.getProperties();
    }
}
