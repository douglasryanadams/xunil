package io.xunil.web.main;

import io.xunil.web.presentation.model.ErrorMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

/**
 * Created on 7/31/16.
 */
public class XunilExceptionMapper implements ExceptionMapper<Exception> {
    private static final Logger log = LogManager.getLogger(XunilExceptionMapper.class);
    
    @Override
    public Response toResponse(Exception e) {
        log.warn("Exception caught in ExceptionMapper: {}", e);
        ErrorMessage error = new ErrorMessage();
        error.setError("Internal Server Error, please report here: https://github.com/douglasryanadams/xunil/issues");
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(error).build();
    }
}
