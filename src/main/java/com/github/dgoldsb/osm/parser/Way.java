package com.github.dgoldsb.osm.parser;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import java.util.List;

public class Way {
  @JacksonXmlProperty(isAttribute = true)
  private Long id;

  @JacksonXmlProperty(isAttribute = true)
  private Boolean visible;

  @JacksonXmlProperty(isAttribute = true)
  private String version;

  @JacksonXmlProperty(isAttribute = true)
  private Long changeset;

  @JacksonXmlProperty(isAttribute = true)
  private String timestamp;

  @JacksonXmlProperty(isAttribute = true)
  private String user;

  @JacksonXmlProperty(isAttribute = true)
  private Long uid;

  @JacksonXmlElementWrapper(localName = "nd", useWrapping = false)
  @JacksonXmlProperty(localName = "nd")
  private List<Nd> nds;

  @JacksonXmlElementWrapper(localName = "tag", useWrapping = false)
  @JacksonXmlProperty(localName = "tag")
  private List<Tag> tags;

  // Getters and setters
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Boolean getVisible() {
    return visible;
  }

  public void setVisible(Boolean visible) {
    this.visible = visible;
  }

  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
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

  public List<Nd> getNds() {
    return nds;
  }

  public void setNds(List<Nd> nds) {
    this.nds = nds;
  }

  public List<Tag> getTags() {
    return tags;
  }

  public void setTags(List<Tag> tags) {
    this.tags = tags;
  }
}
