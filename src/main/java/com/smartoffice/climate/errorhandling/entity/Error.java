package com.smartoffice.climate.errorhandling.entity;

/**
 * @author michael_loibl
 * @since 16.03.20
 */
public class Error {

  private String errorCode;
  private String errorMessage;
  private String traceId;

  public String getErrorCode() {
    return errorCode;
  }

  public void setErrorCode(final String pErrorCode) {
    errorCode = pErrorCode;
  }

  public String getErrorMessage() {
    return errorMessage;
  }

  public void setErrorMessage(final String pErrorMessage) {
    errorMessage = pErrorMessage;
  }

  public String getTraceId() {
    return traceId;
  }

  public void setTraceId(final String pTraceId) {
    traceId = pTraceId;
  }

  @Override
  public String toString() {
    return "Error{" +
        "errorCode='" + errorCode + '\'' +
        ", errorMessage='" + errorMessage + '\'' +
        ", traceId='" + traceId + '\'' +
        '}';
  }
}
