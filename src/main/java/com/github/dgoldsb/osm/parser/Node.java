package com.github.dgoldsb.osm.parser;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import java.util.List;

public class Node {
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
