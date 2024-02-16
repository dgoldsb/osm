package com.github.dgoldsb.osm.parser;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import java.math.BigDecimal;

public record Bounds(
    @JacksonXmlProperty(localName = "minlat") BigDecimal minimumLatitude,
    @JacksonXmlProperty(localName = "minlon") BigDecimal minimumLongitude,
    @JacksonXmlProperty(localName = "maxlat") BigDecimal maximumLatitude,
    @JacksonXmlProperty(localName = "maxlon") BigDecimal maximumLongitude) {}
