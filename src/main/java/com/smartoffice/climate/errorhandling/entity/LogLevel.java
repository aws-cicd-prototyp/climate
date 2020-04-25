package com.smartoffice.climate.errorhandling.entity;

/**
 * Log level enumeration.
 * <p/>
 * Usually the log levels TRACE, DEBUG and INFO should only be used for Business Errors whereas the higher levels WARN,
 * ERROR and FATAL are suitable for System Errors.
 */
public enum LogLevel {

  /** The TRACE Level designates finer-grained informational events than the DEBUG. */
  TRACE,

  /** The DEBUG Level designates fine-grained informational events that are most useful to debug an application. */
  DEBUG,

  /**
   * The INFO level designates informational messages that highlight the progress of the application at coarse-grained
   * level.
   */
  INFO,

  /** The WARN level designates potentially harmful situations. */
  WARN,

  /** The ERROR level designates error events that might still allow the application to continue running. */
  ERROR,

  /** The FATAL level designates very fatal error events that will presumably lead the application to abort. */
  FATAL,

  /** Indicates nologging. */
  OFF
}
