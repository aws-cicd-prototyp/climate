package com.smartoffice.climate.monitoring.boundary;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;

import javax.enterprise.context.ApplicationScoped;

/**
 * @author michael_loibl
 * @since 16.03.20
 */
@Liveness
@ApplicationScoped
public class LivenessCheck implements HealthCheck {

  @Override
  public HealthCheckResponse call() {
    return HealthCheckResponse
        .named("liveness-check")
        .withData("microservice", "climate")
        .up()
        .build();
  }
}
