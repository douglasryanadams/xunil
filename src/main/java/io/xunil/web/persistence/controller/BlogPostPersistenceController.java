package io.xunil.web.persistence.controller;

import io.xunil.web.persistence.data.BlogPost;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 * Created by on 5/21/16.
 */

public class BlogPostPersistenceController {
   private EntityManagerFactory emf;

   public BlogPostPersistenceController(EntityManagerFactory emf) {
      this.emf = emf;
   }

   public BlogPost read(Integer id) {
      EntityManager em = emf.createEntityManager();
      try {
         return em.find(BlogPost.class, id);
      }
      finally {
         if (em.isOpen()) em.close();
      }
   }
}
