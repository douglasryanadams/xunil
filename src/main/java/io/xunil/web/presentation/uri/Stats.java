package io.xunil.web.presentation.uri;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 * Created on 5/28/16.
 */
@Path("stats")
public class Stats {
    private static final Logger log = LogManager.getLogger(Stats.class);

    @Context
    UriInfo uri;

    @GET
    @Path("log")
    public Response logStats() throws Exception {
        log.info("Placeholder called: GET {}", uri.getAbsolutePath());
        return Response.ok().build();
    }
}
