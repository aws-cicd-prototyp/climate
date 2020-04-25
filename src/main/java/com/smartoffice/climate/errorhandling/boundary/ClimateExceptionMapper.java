package com.smartoffice.climate.errorhandling.boundary;

import com.smartoffice.climate.errorhandling.control.ErrorHandler;
import com.smartoffice.climate.errorhandling.entity.ClimateException;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * @author michael_loibl
 * @since 16.03.20
 */
@Provider
public class ClimateExceptionMapper implements ExceptionMapper<ClimateException> {

  @Inject
  private ErrorHandler errorHandler;

  @Override
  public Response toResponse(final ClimateException pVehiclesException) {
    return errorHandler.handle(pVehiclesException);
  }

}
