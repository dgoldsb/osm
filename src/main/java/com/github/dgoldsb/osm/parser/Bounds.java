package com.github.dgoldsb.osm.parser;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public record Bounds(
    @JacksonXmlProperty(localName = "minlat") Double minimumLatitude,
    @JacksonXmlProperty(localName = "minlon") Double minimumLongitude,
    @JacksonXmlProperty(localName = "maxlat") Double maximumLatitude,
    @JacksonXmlProperty(localName = "maxlon") Double maximumLongitude) {}
