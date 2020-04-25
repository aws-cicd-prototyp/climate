package com.smartoffice.climate.climatedata.entity;

import io.quarkus.runtime.annotations.RegisterForReflection;

import java.util.Objects;

/**
 * @author michael_loibl
 * @since 16.03.20
 */
@RegisterForReflection
public class ClimateData {

  private String date;
  private String time;
  private int temperature;
  private int humidity;

  public ClimateData() {
  }

  public ClimateData(String date, String time, int temperature, int humidity) {
    this.date = date;
    this.time = time;
    this.temperature = temperature;
    this.humidity = humidity;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public String getTime() {
    return time;
  }

  public void setTime(String time) {
    this.time = time;
  }

  public int getTemperature() {
    return temperature;
  }

  public void setTemperature(int temperature) {
    this.temperature = temperature;
  }

  public int getHumidity() {
    return humidity;
  }

  public void setHumidity(int humidity) {
    this.humidity = humidity;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ClimateData that = (ClimateData) o;
    return temperature == that.temperature &&
        humidity == that.humidity &&
        Objects.equals(date, that.date) &&
        Objects.equals(time, that.time);
  }

  @Override
  public int hashCode() {
    return Objects.hash(date, time, temperature, humidity);
  }

  @Override
  public String toString() {
    return "ClimateInfo{" +
        "date=" + date +
        ", time=" + time +
        ", temperature=" + temperature +
        ", humidity=" + humidity +
        '}';
  }
}
