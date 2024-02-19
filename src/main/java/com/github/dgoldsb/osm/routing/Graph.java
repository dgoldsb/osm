package com.github.dgoldsb.osm.routing;

import java.util.HashMap;
import java.util.HashSet;

/** In memory graph definition, expected as an input for a routing algorithm. */
public record Graph(
    HashMap<Vertex, HashSet<Vertex>> neighbourMap, HashMap<Vertex, String> labelMap) {}
