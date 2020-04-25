package com.smartoffice.climate.errorhandling.boundary;

import com.smartoffice.climate.errorhandling.control.ErrorHandler;

import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * @author michael_loibl
 * @since 16.03.20
 */
@Provider
public class WebApplicationExceptionMapper implements ExceptionMapper<WebApplicationException> {

  @Inject
  private ErrorHandler errorHandler;

  @Override
  public Response toResponse(final WebApplicationException pException) {
    return errorHandler.handle(pException);
  }

}
