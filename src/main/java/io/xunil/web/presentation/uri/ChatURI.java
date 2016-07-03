package io.xunil.web.presentation.uri;

import io.xunil.web.controller.ChatController;
import io.xunil.web.memory.Sessions;
import io.xunil.web.presentation.model.ChatRegistrationRequest;
import io.xunil.web.presentation.model.ChatRegistrationResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 * Created on 7/3/16.
 */
@Path("chat")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ChatURI {
    private static final Logger log = LogManager.getLogger(ChatURI.class);

    @Context
    private UriInfo uri;

    @POST
    public Response postChatClient(ChatRegistrationRequest chatRegistration) {
        log.debug("    Request: POST {}", uri.getAbsolutePath());
        ChatController controller = new ChatController(Sessions.getInstance());
        ChatRegistrationResponse response = controller.register(chatRegistration);
        return Response.ok(response).build();
    }
}
