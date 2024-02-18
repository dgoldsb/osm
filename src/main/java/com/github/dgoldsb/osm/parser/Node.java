package com.github.dgoldsb.osm.parser;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import java.util.List;

public class Node {
  private final int RADIUS_OF_EARTH = 6378100;

  @JacksonXmlProperty(isAttribute = true)
  private Long id;

  @JacksonXmlProperty(localName = "lat", isAttribute = true)
  private Double latitude;

  @JacksonXmlProperty(localName = "lon", isAttribute = true)
  private Double longitude;

  @JacksonXmlElementWrapper(localName = "tag", useWrapping = false)
  @JacksonXmlProperty(localName = "tag")
  private List<Tag> tags;

  @JacksonXmlProperty(isAttribute = true)
  private Boolean visible;

  @JacksonXmlProperty(isAttribute = true)
  private Long changeset;

  @JacksonXmlProperty(isAttribute = true)
  private String timestamp;

  @JacksonXmlProperty(isAttribute = true)
  private String user;

  @JacksonXmlProperty(isAttribute = true)
  private Long uid;

  @JacksonXmlProperty(isAttribute = true)
  private String version;

  /**
   * Returns the distance between this node and the other in meters.
   *
   * @param other other node to compare distance to
   * @return distance in meters
   */
  public Double calculateDistanceToNode(Node other) {
    // Convert latitude and longitude from degrees to radians.
    double thisLatitudeRadians = Math.toRadians(this.latitude);
    double thisLongitudeRadians = Math.toRadians(this.longitude);
    double otherLatitudeRadians = Math.toRadians(other.latitude);
    double otherLongitudeRadians = Math.toRadians(other.longitude);

    // Calculate the differences between latitudes and longitudes.
    double deltaLatitude = otherLatitudeRadians - thisLatitudeRadians;
    double deltaLongitude = otherLongitudeRadians - thisLongitudeRadians;

    // Haversine formula.
    double a =
        Math.pow(Math.sin(deltaLatitude / 2), 2)
            + Math.cos(thisLatitudeRadians)
                * Math.cos(otherLatitudeRadians)
                * Math.pow(Math.sin(deltaLongitude / 2), 2);
    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

    return RADIUS_OF_EARTH * c;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Double getLatitude() {
    return latitude;
  }

  public void setLatitude(Double latitude) {
    this.latitude = latitude;
  }

  public Double getLongitude() {
    return longitude;
  }

  public void setLongitude(Double longitude) {
    this.longitude = longitude;
  }

  public Boolean getVisible() {
    return visible;
  }

  public void setVisible(Boolean visible) {
    this.visible = visible;
  }

  public Long getChangeset() {
    return changeset;
  }

  public void setChangeset(Long changeset) {
    this.changeset = changeset;
  }

  public String getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(String timestamp) {
    this.timestamp = timestamp;
  }

  public String getUser() {
    return user;
  }

  public void setUser(String user) {
    this.user = user;
  }

  public Long getUid() {
    return uid;
  }

  public void setUid(Long uid) {
    this.uid = uid;
  }

  public List<Tag> getTags() {
    return tags;
  }

  public void setTags(List<Tag> tags) {
    this.tags = tags;
  }

  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }
}
