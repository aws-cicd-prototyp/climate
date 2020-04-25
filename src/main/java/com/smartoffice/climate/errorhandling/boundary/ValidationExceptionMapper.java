package com.smartoffice.climate.errorhandling.boundary;

import com.smartoffice.climate.errorhandling.control.ErrorHandler;
import com.smartoffice.climate.errorhandling.entity.ErrorCode;

import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author michael_loibl
 * @since 16.03.20
 */
@Provider
public class ValidationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {

  @Inject
  private ErrorHandler errorHandler;

  @Override
  public Response toResponse(final ConstraintViolationException pException) {
    final String errorMessage = createErrorMessage(pException);
    return errorHandler.handle(ErrorCode.INVALID_PARAMETER, new Object[]{errorMessage});
  }

  private String createErrorMessage(final ConstraintViolationException pException) {
    List<String> messages = new ArrayList<>();
    for (ConstraintViolation constraintViolation : pException.getConstraintViolations()) {
      messages.add(constraintViolation.getMessage());
    }
    Collections.sort(messages);
    return String.join(", ", messages);
  }
}
