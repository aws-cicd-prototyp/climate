package com.smartoffice.climate.monitoring.boundary;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Readiness;

import javax.enterprise.context.ApplicationScoped;

/**
 * @author michael_loibl
 * @since 16.03.20
 */
@Readiness
@ApplicationScoped
public class ReadinessCheck implements HealthCheck {

  @Override
  public HealthCheckResponse call() {
    return HealthCheckResponse
        .named("readiness-check")
        .withData("microservice", "climate")
        .state(checkDbConnection() && doOtherReadinessChecks())
        .up()
        .build();
  }

  private boolean checkDbConnection(){
    //implement db-connectivity-check here...
    return true;
  }

  private boolean doOtherReadinessChecks(){
    //implement other checks here...
    return true;
  }
}
