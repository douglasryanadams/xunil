package io.xunil.web.presentation.uri;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 * Created by on 5/19/16.
 */

@Path("file")
public class FileURI {

    @GET
    public Response getFile() {
        return Response.ok("File Placeholder").build();
    }
}
