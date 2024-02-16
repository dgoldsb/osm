package com.github.dgoldsb.osm.parser;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import java.util.List;

public class Osm {
  @JacksonXmlElementWrapper(localName = "node", useWrapping = false)
  @JacksonXmlProperty(localName = "node")
  private List<Node> nodes;

  @JacksonXmlElementWrapper(localName = "way", useWrapping = false)
  @JacksonXmlProperty(localName = "way")
  private List<Way> ways;

  @JacksonXmlElementWrapper(localName = "relation", useWrapping = false)
  @JacksonXmlProperty(localName = "relation")
  private List<Relation> relations;

  private Bounds bounds;

  @JacksonXmlProperty(isAttribute = true)
  private String version;

  @JacksonXmlProperty(isAttribute = true)
  private String generator;

  @JacksonXmlProperty(isAttribute = true)
  private String copyright;

  @JacksonXmlProperty(isAttribute = true)
  private String attribution;

  @JacksonXmlProperty(isAttribute = true)
  private String license;

  public List<Node> getNodes() {
    return nodes;
  }

  public void setNodes(List<Node> nodes) {
    this.nodes = nodes;
  }

  public List<Way> getWays() {
    return ways;
  }

  public void setWays(List<Way> ways) {
    this.ways = ways;
  }

  public List<Relation> getRelations() {
    return relations;
  }

  public void setRelations(List<Relation> relations) {
    this.relations = relations;
  }

  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  public String getGenerator() {
    return generator;
  }

  public void setGenerator(String generator) {
    this.generator = generator;
  }

  public String getCopyright() {
    return copyright;
  }

  public void setCopyright(String copyright) {
    this.copyright = copyright;
  }

  public String getAttribution() {
    return attribution;
  }

  public void setAttribution(String attribution) {
    this.attribution = attribution;
  }

  public String getLicense() {
    return license;
  }

  public void setLicense(String license) {
    this.license = license;
  }

  public Bounds getBounds() {
    return bounds;
  }

  public void setBounds(Bounds bounds) {
    this.bounds = bounds;
  }
}
