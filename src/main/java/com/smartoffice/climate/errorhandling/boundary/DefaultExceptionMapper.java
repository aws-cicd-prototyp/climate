package com.smartoffice.climate.errorhandling.boundary;


import com.smartoffice.climate.errorhandling.control.ErrorHandler;
import com.smartoffice.climate.errorhandling.entity.ErrorCode;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * @author michael_loibl
 * @since 16.03.20
 */
@Provider
public class DefaultExceptionMapper implements ExceptionMapper<Throwable> {

  @Inject
  private ErrorHandler errorHandler;

  @Override
  public Response toResponse(final Throwable pException) {
    return errorHandler.handle(ErrorCode.INTERNAL_SERVER_ERROR, pException);
  }

}
