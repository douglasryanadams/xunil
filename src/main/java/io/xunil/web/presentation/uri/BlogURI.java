package io.xunil.web.presentation.uri;

import io.xunil.web.controller.BlogController;
import io.xunil.web.main.Xunil;
import io.xunil.web.persistence.PersistenceMaster;
import io.xunil.web.presentation.model.BlogPost;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 * Created by on 5/17/16.
 */

@Path("blog")
@Produces("application/json")
public class BlogURI {

    private static final Logger log = LogManager.getLogger(BlogURI.class.getName());
    private BlogController controller;

    @Context
    private UriInfo uri;

    public BlogURI() {
        Xunil x = Xunil.getInstance();
        PersistenceMaster pMaster = x.getPersistenceMaster();
        controller = new BlogController(pMaster);
    }

    @GET
    @Path("{id}")
    public Response getBlog(@PathParam("id") Integer id) {
        log.debug("    Request: GET {}", uri.getAbsolutePath());
        BlogPost r = controller.read(id);
        return Response.ok(r).build();
    }

}
