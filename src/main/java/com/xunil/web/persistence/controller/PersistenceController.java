package com.xunil.web.persistence.controller;

import com.xunil.web.persistence.PersistenceMaster;

/**
 * Created by on 5/21/16.
 */
abstract class PersistenceController<T> {

    private PersistenceMaster master;

    PersistenceController(PersistenceMaster master) {
        this.master = master;
    }

    PersistenceMaster getMaster() {
        return master;
    }

    public abstract T read(Integer id);

    public abstract Integer total();

}
