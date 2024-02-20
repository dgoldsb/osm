package com.github.dgoldsb.osm.routing;

public record DistanceNodePair(Double distance, Vertex node)
    implements Comparable<DistanceNodePair> {

  @Override
  public int compareTo(DistanceNodePair o) {
    return this.distance.compareTo(o.distance);
  }
}
