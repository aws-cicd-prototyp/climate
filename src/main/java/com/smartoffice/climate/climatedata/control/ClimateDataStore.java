package com.smartoffice.climate.climatedata.control;

import com.smartoffice.climate.climatedata.entity.ClimateData;
import javax.enterprise.context.RequestScoped;
import java.util.List;
import java.util.stream.IntStream;
import static java.lang.String.format;
import static java.util.stream.Collectors.toList;

/**
 * @author michael_loibl
 * @since 16.03.20
 */
@RequestScoped
public class ClimateDataStore {

  /**
   * test-impl. => Will be replaced later by real sensor-data from dynamoDB!
   */
  public List<ClimateData> getClimateData(String date) {
    return IntStream.rangeClosed(7, 9)
        .boxed()
        .map(r -> new ClimateData(date, format("0%s:45:32", r), 20+r, 50+r))
        .collect(toList());
  }
}
