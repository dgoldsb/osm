package com.github.dgoldsb.osm.parser;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

// TODO: Ignoring properties for now, at the moment we are not using relations yet.
@JsonIgnoreProperties(ignoreUnknown = true)
public record Relation(Long id) {}
