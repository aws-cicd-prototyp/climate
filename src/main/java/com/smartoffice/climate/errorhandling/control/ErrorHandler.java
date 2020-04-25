package com.smartoffice.climate.errorhandling.control;

import com.smartoffice.climate.errorhandling.entity.ClimateException;
import com.smartoffice.climate.errorhandling.entity.Error;
import com.smartoffice.climate.errorhandling.entity.ErrorCode;
import com.smartoffice.climate.errorhandling.entity.LogLevel;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.text.MessageFormat;

/**
 * @author michael_loibl
 * @since 16.03.20
 */
@ApplicationScoped
public class ErrorHandler {

  //private static final Logger LOGGER = LogManager.getLogger(ErrorHandler.class);

  public Response handle(final ErrorCode errorCode) {
    final Error error = createError(errorCode, null);
    return handle(errorCode.getResponseStatus(), errorCode.getLogLevel(), error, null);
  }

  public Response handle(final ErrorCode errorCode, final Object[] msgParams) {
    final Error error = createError(errorCode, msgParams);
    return handle(errorCode.getResponseStatus(), errorCode.getLogLevel(), error, null);
  }

  public Response handle(final ErrorCode errorCode, final Throwable throwable) {
    final Error error = createError(errorCode);
    return handle(errorCode.getResponseStatus(), errorCode.getLogLevel(), error, throwable);
  }

  public Response handle(final ClimateException ex) {
    final ErrorCode errorCode = ex.getErrorCode();
    final Error error = createError(errorCode, ex.getMessageParameters());
    return handle(errorCode.getResponseStatus(), errorCode.getLogLevel(), error, ex);
  }

  public Error handleAndReturnError(final ClimateException pException) {
    final ErrorCode errorCode = pException.getErrorCode();
    final Error error = createError(errorCode, pException.getMessageParameters());
    return error;
  }

  public Response handle(final WebApplicationException pWebAppException) {
    final Error error;
    final LogLevel logLevel;
    final int httpStatus = pWebAppException.getResponse().getStatus();
    if (400 <= httpStatus && httpStatus <= 499) {
      error = createError(ErrorCode.BAD_REQUEST);
      logLevel = ErrorCode.BAD_REQUEST.getLogLevel();
    } else {
      error = createError(ErrorCode.INTERNAL_SERVER_ERROR);
      logLevel = ErrorCode.INTERNAL_SERVER_ERROR.getLogLevel();
    }
    return handle(httpStatus, logLevel, error, pWebAppException);
  }

  private Response handle(final int httpStatus, final LogLevel logLevel, final Error error, final Throwable throwable) {
    return buildResponse(httpStatus, error);
  }

  private Error createError(final ErrorCode errorCode) {
    return createError(errorCode, null);
  }

  private Error createError(final ErrorCode errorCode, final Object[] msgParams) {
    return createError(errorCode.getErrorCode(), errorCode.getMessageFormat(), msgParams);
  }

  private Error createError(final String errorCode, final String msg, final Object[] msgParams) {
    final Error error = new Error();
    error.setErrorCode(errorCode);
    if (msgParams == null) {
      error.setErrorMessage(msg);
    } else {
      error.setErrorMessage(MessageFormat.format(msg, msgParams));
    }
    return error;
  }

  private Response buildResponse(final int pHttpStatus, final Error error) {
    return Response.status(pHttpStatus)
        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
        .entity(error)
        .build();
  }

}
