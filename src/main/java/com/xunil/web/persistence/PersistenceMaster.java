package com.xunil.web.persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 * Created by on 5/21/16.
 */
public class PersistenceMaster {

    private EntityManagerFactory emf;

    public PersistenceMaster(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void getCriteriaBuilder() {
    }
}
