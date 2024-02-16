package com.github.dgoldsb.osm.parser;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class Nd {
  @JacksonXmlProperty(isAttribute = true)
  private String ref;

  public String getRef() {
    return ref;
  }

  public void setRef(String ref) {
    this.ref = ref;
  }
}
