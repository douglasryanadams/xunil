package com.xunil.web.main;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by on 5/17/16.
 */
public class Xunil {
    private static final Logger log = LogManager.getLogger(Xunil.class.getName());

    private static Xunil self = new Xunil();

    private Xunil() {
    }

    public static Xunil getInstance() {
        return self;
    }

    public void start() {
        log.info("Starting Xunil Web");
    }

    public void stop() {
        log.info("Stopping Xunil Web");
    }
}
