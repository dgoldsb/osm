package com.github.dgoldsb.osm.routing;

import com.github.dgoldsb.osm.parser.*;
import java.util.*;
import java.util.stream.Collectors;

/** In memory graph definition, expected as an input for a routing algorithm. */
public record Graph(
    HashMap<Long, Vertex> uidVertexMap,
    HashMap<Vertex, HashSet<Vertex>> neighbourMap,
    HashMap<Vertex, String> labelMap) {

  /**
   * Instantiate a graph from OSM nodes and ways.
   *
   * @param osm the open street map data to load
   * @return an instantiated Graph object
   */
  public static Graph fromOsm(Osm osm) {
    // Create a temporary map of UID to vertex.
    HashMap<Long, Vertex> uidVertexMap =
        osm.getNodes().stream()
            .collect(
                Collectors.toMap(
                    Node::getId,
                    n -> new Vertex(n.getUid(), n.getLatitude(), n.getLongitude()),
                    (existingValue, newValue) -> existingValue,
                    HashMap::new));

    // Populate the neighbours by exploring all ways.
    HashMap<Vertex, HashSet<Vertex>> neighbourMap = new HashMap<>();
    for (Way way : osm.getWays()) {
      for (int i = 0; i < way.getNds().size() - 1; i += 1) {
        Vertex firstVertex = uidVertexMap.get(way.getNds().get(i).getRef());
        Vertex secondVertex = uidVertexMap.get(way.getNds().get(i + 1).getRef());

        neighbourMap.getOrDefault(firstVertex, new HashSet<>()).add(secondVertex);
        neighbourMap.getOrDefault(secondVertex, new HashSet<>()).add(firstVertex);
      }
    }

    // Quick and dirty extraction of names of ways, we do not consider the edge case of nodes that
    // are in multiple
    // ways.
    HashMap<Vertex, String> labelMap = new HashMap<>();
    for (Way way : osm.getWays()) {
      if (way.getTags() == null) {
        continue;
      }

      Optional<Tag> labelTagOptional =
          way.getTags().stream().filter(t -> Objects.equals(t.getK(), "name")).findFirst();

      if (labelTagOptional.isPresent()) {
        for (Nd nd : way.getNds()) {
          Vertex vertex = uidVertexMap.get(nd.getRef());
          if (vertex != null) {
            labelMap.put(vertex, labelTagOptional.get().getV());
          }
        }
      }
    }

    return new Graph(uidVertexMap, neighbourMap, labelMap);
  }

  public Vertex findVertex(Long uid) throws RuntimeException {
    if (!this.uidVertexMap.containsKey(uid)) {
      throw new RuntimeException(String.format("UID %d not known in map!", uid));
    }
    return this.uidVertexMap.get(uid);
  }
}
