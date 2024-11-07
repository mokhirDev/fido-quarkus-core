package org.acme.week.first.day.third.exceptions.mapper;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.acme.week.first.day.third.exceptions.AlreadyProductExistException;

@Provider
public class AlreadyProductExistExceptionMapper implements ExceptionMapper<AlreadyProductExistException> {
    @Override
    public Response toResponse(AlreadyProductExistException exception) {
        return Response.status(Response.Status.CONFLICT)
                .entity("{\"error\":\"" + exception.getMessage() + "\"}")
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}
