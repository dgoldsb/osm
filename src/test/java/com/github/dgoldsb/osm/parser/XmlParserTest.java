package com.github.dgoldsb.osm.parser;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import java.io.File;
import java.io.IOException;
import org.junit.jupiter.api.Test;

public class XmlParserTest {
  @Test
  public void testXmlDeserialization() throws IOException {
    // Arrange
    File xmlFile = new File("src/test/resources/maps/kadikoy.osm");
    XmlMapper xmlMapper = new XmlMapper();
    XmlParser xmlParser = new XmlParser(xmlMapper);

    // Act
    Osm osm = xmlParser.parse(xmlFile);

    // Assert
    assert osm.getNodes().size() == 1;
    assert osm.getRelations().size() == 1;
    assert osm.getWays().size() == 1;
  }
}
