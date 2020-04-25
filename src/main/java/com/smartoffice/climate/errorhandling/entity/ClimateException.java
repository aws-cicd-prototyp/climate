package com.smartoffice.climate.errorhandling.entity;

import java.text.MessageFormat;

/**
 * @author michael_loibl
 * @since 16.03.20
 */
public class ClimateException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  private final ErrorCode errorCode;
  private transient final Object[] messageParameters;

  public ClimateException(final String internalErrorMessage, final ErrorCode pErrorCode, final Throwable pCause, final Object... pMessageParameters) {
    super(errorMessage(pErrorCode, pMessageParameters) + " - " + internalErrorMessage, pCause);
    this.errorCode = pErrorCode;
    this.messageParameters = pMessageParameters;
  }

  public ClimateException(final ErrorCode pErrorCode, final Throwable pCause, final Object... pMessageParameters) {
    this(null, pErrorCode, pCause, pMessageParameters);
  }

  public ClimateException(final String internalErrorMessage, final ErrorCode pErrorCode, final Throwable pCause) {
    this(internalErrorMessage, pErrorCode, pCause, null);
  }

  public ClimateException(final ErrorCode pErrorCode, final Throwable pCause) {
    this(null, pErrorCode, pCause, null);
  }

  public ClimateException(final String internalErrorMessage, final ErrorCode pErrorCode, final Object... pPMessageParameters) {
    this(internalErrorMessage, pErrorCode, null, pPMessageParameters);
  }

  public ClimateException(final ErrorCode pErrorCode, final Object... pPMessageParameters) {
    this(null, pErrorCode, null, pPMessageParameters);
  }

  public ClimateException(final String internalErrorMessage, final ErrorCode pErrorCode) {
    this(internalErrorMessage, pErrorCode, null, null);
  }

  public ClimateException(final ErrorCode pErrorCode) {
    this(null, pErrorCode, null, null);
  }

  public ErrorCode getErrorCode() {
    return errorCode;
  }

  public Object[] getMessageParameters() {
    return messageParameters == null ? null : messageParameters.clone();
  }

  private static String errorMessage(ErrorCode errorCode, Object[] messageParameters) {
    return messageParameters == null ?
        errorCode.getErrorCode() + " - " + errorCode.getMessageFormat() :
        errorCode.getErrorCode() + " - " + MessageFormat.format(errorCode.getMessageFormat(), messageParameters);
  }

  @Override
  public String toString() {
    return "VehiclesException{" +
        "errorCode=" + errorCode +
        " msg=" + getMessage() +
        '}';
  }
}
