package com.smartoffice.climate.climatedata.boundary;

import com.smartoffice.climate.errorhandling.entity.ClimateException;
import com.smartoffice.climate.errorhandling.entity.ErrorCode;

import javax.json.Json;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.smartoffice.climate.errorhandling.entity.ErrorCode.INTERNAL_SERVER_ERROR;

/**
 * @author michael_loibl
 * @since 08.05.20
 */
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("sleep")
public class SleepResource {

  private static ExecutorService executor = Executors.newFixedThreadPool(10);

  @GET
  public Response getClimateData() {
    try {
      Thread.sleep(8000);
    } catch (InterruptedException e) {
      //do nothing - only testResource
    }
    return Response
        .ok(Json.createObjectBuilder()
            .add("date", LocalDate.now().toString())
            .add("message", "hello world")
            .build())
        .build();
  }

  @GET
  @Path("sleep-async")
  public void asyncGet(@Suspended final AsyncResponse asyncResponse) {
    executor.execute(() -> {
      try {
        Thread.sleep(8000);
      } catch (InterruptedException e) {
        Thread.interrupted();
        throw new ClimateException(INTERNAL_SERVER_ERROR, "ERROR on async-resource", e);
      }
      asyncResponse.resume(Json.createObjectBuilder()
          .add("date", LocalDate.now().toString())
          .add("message", "hello after some sleeping time :)")
          .build());
    });
  }

  @GET
  @Path("temperature")
  public Response getTemp() {
    return Response
        .ok("6")
        .build();
  }

  @GET
  @Path("humidity")
  public Response getHumidity() {
    return Response
        .ok("5")
        .build();
  }

  @GET
  @Path("pressure")
  public Response getPressure() {
    return Response
        .ok("4")
        .build();
  }

  @GET
  @Path("quality")
  public Response getQuality() {
    return Response
        .ok("3")
        .build();
  }

  @GET
  @Path("illumination")
  public Response getIllumination() {
    return Response
        .ok("2")
        .build();
  }
}
