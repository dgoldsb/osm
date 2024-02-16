package com.github.dgoldsb.osm.parser;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import java.io.File;
import java.io.IOException;

public class XmlParser {
  private final XmlMapper xmlMapper;

  public XmlParser(XmlMapper xmlMapper) {
    // xmlMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    this.xmlMapper = xmlMapper;
  }

  public Osm parse(File xmlFile) throws IOException {
    return xmlMapper.readValue(xmlFile, Osm.class);
  }
}
