package com.smartoffice.climate.errorhandling.entity;

/**
 * @author michael_loibl
 * @since 16.03.20
 */
public enum ErrorCode {

  // client errors (business errors)
  BAD_REQUEST("CL_B_1000", "Bad Request.", 400, LogLevel.DEBUG),
  INVALID_PARAMETER("CL_B_1001", "Invalid request parameter: {0}", 400, LogLevel.DEBUG),
  RESOURCE_NOT_FOUND("CL_B_1002", "Requested resource not found.", 404, LogLevel.DEBUG),

  // server errors (system errors)
  INTERNAL_SERVER_ERROR("CL_S_2001", "Unexpected server error.", 500, LogLevel.ERROR),
  SETTING_CONFIGURATION_ERROR("CL_S_2005", "Setting configuration error: {0}", 500, LogLevel.ERROR),
  BATCH_DELETE_MAX_RETRIES_EXCEEDED("CL_S_2018", "Batch max retries for deletion exceeded.", 500, LogLevel.ERROR);

  private final String code;
  private final String errorMessageFormat;
  private final int responseStatus;
  private final LogLevel logLevel;

  ErrorCode(final String pErrorCode, final String pPErrorMessageFormat, final int pResponseStatus, final LogLevel pLogLevel) {
    code = pErrorCode;
    errorMessageFormat = pPErrorMessageFormat;
    responseStatus = pResponseStatus;
    logLevel = pLogLevel;
  }

  public String getErrorCode() {
    return code;
  }

  public String getMessageFormat() {
    return errorMessageFormat;
  }

  public LogLevel getLogLevel() {
    return logLevel;
  }

  public int getResponseStatus() {
    return responseStatus;
  }
}
