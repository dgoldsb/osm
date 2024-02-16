package com.github.dgoldsb.osm.parser;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class Tag {
  @JacksonXmlProperty(isAttribute = true)
  private String k;

  @JacksonXmlProperty(isAttribute = true)
  private String v;

  public String getV() {
    return v;
  }

  public void setV(String v) {
    this.v = v;
  }

  public String getK() {
    return k;
  }

  public void setK(String k) {
    this.k = k;
  }
}
