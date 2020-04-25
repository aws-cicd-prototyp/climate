package com.smartoffice.climate.climatedata.boundary;

import com.smartoffice.climate.climatedata.control.ClimateDataStore;
import com.smartoffice.climate.validation.control.CustomDate;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author michael_loibl
 * @since 16.03.20
 */
@Path("climates")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ClimateDataResource {

  @Inject
  private ClimateDataStore climateDataStore;

  @GET
  @Path("dates/{date}")
  public Response getClimateData(@PathParam("date") @CustomDate String date) {
    return Response
        .ok(climateDataStore.getClimateData(date))
        .build();
  }
}
