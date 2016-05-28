package io.xunil.web.persistence;

import io.xunil.web.persistence.controller.BlogPostPersistenceController;

/**
 * Created on 5/22/16.
 */
public interface PersistenceMasterInterface {
    public BlogPostPersistenceController getBlogPostController();
}
