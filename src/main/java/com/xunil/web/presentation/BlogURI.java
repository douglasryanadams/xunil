package com.xunil.web.presentation;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.net.URI;

/**
 * Created by on 5/17/16.
 */

@Path("blog")
public class BlogURI {

    private static final Logger log = LogManager.getLogger(BlogURI.class.getName());

    @Context
    private URI uri;

    @GET
    @Path("{id}")
    public Response getBlog(@PathParam("id") String id) {
        log.debug(uri.getQuery());
        return null;
    }

}
