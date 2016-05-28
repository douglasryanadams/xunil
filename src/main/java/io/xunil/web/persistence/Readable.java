package io.xunil.web.persistence;

/**
 * Created on 5/22/16.
 */
interface Readable<T> {
    T read(Integer id);
}
