package com.xunil.web.persistence.controller;

import com.xunil.web.persistence.PersistenceMaster;

/**
 * Created by on 5/21/16.
 */

public class BlogPostController extends PersistenceController{

    public BlogPostController(PersistenceMaster master) {
        super(master);
    }

    @Override
    public Object read(Integer id) {

        return null;
    }

    @Override
    public Integer total() {
        return null;
    }
}
