package io.xunil.web.persistence;

import io.xunil.web.persistence.controller.BlogPostPersistenceController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by on 5/21/16.
 */
public class PersistenceMaster implements PersistenceMasterInterface {

    private static final Logger log = LogManager.getLogger(PersistenceMaster.class.toString());

    private static EntityManagerFactory emf;
    private static PersistenceControllerContainer persistenceControllerContainer;

    public PersistenceMaster(String persistenceUnit) {
        log.info("Constructing Persistence Master");
        emf = Persistence.createEntityManagerFactory(persistenceUnit);
        persistenceControllerContainer = new PersistenceControllerContainer();
    }

    public BlogPostPersistenceController getBlogPostController() {
        log.debug("    retrieving BlogPostController");
        if (persistenceControllerContainer.blogPostPersistenceController == null) {
            log.debug("    creating new BlogPostController");
            persistenceControllerContainer.blogPostPersistenceController = new BlogPostPersistenceController(emf);
        }
        return persistenceControllerContainer.blogPostPersistenceController;
    }

    private class PersistenceControllerContainer {
        BlogPostPersistenceController blogPostPersistenceController;
    }

    public void shutdown() {
        if (emf.isOpen()) emf.close();
    }

}
