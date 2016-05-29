package io.xunil.web.controller;

import io.xunil.web.persistence.PersistenceMaster;
import io.xunil.web.persistence.controller.BlogPostPersistenceController;
import io.xunil.web.presentation.model.BlogPost;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created on 5/22/16.
 */
public class BlogController {
    private static final Logger log = LogManager.getLogger(BlogController.class);

    private BlogPostPersistenceController dbController;

    public BlogController(PersistenceMaster persistenceMaster) {
        log.info("Constructing BlogController({})", persistenceMaster);
        this.dbController = persistenceMaster.getBlogPostController();
    }

    public BlogPost read(Integer id) {
        log.info("Reading Blog Post with ID: {}", id);
        return new BlogPost(dbController.read(id));
    }
}
