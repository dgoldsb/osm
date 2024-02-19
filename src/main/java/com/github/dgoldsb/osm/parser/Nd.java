package com.github.dgoldsb.osm.parser;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class Nd {
  @JacksonXmlProperty(isAttribute = true)
  private Long ref;

  public Long getRef() {
    return ref;
  }

  public void setRef(Long ref) {
    this.ref = ref;
  }
}
