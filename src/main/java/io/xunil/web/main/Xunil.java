package io.xunil.web.main;

import io.xunil.web.persistence.PersistenceMaster;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by on 5/17/16.
 */
public class Xunil {
    private static final Logger log = LogManager.getLogger(Xunil.class);

    private static Xunil self = new Xunil();
    private static PersistenceMaster pMaster;

    private Xunil() {}

    public static Xunil getInstance() {
        return self;
    }

    public void start() {
        log.info("Starting Xunil Web");
        pMaster = new PersistenceMaster("xunil");
    }

    public void stop() {
        log.info("Stopping Xunil Web");
        pMaster.shutdown();
    }

    public PersistenceMaster getPersistenceMaster() {
        return pMaster;
    }
}
