package com.github.dgoldsb.osm.routing;

import com.github.dgoldsb.osm.parser.*;
import java.util.*;
import java.util.stream.Collectors;

/** In memory graph definition, expected as an input for a routing algorithm. */
public record Graph(
    HashMap<Long, Vertex> uidVertexMap,
    HashMap<Vertex, HashSet<Vertex>> neighbourMap,
    HashMap<Pair<Vertex>, String> labelMap) {
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
                    n -> new Vertex(n.getId(), n.getLatitude(), n.getLongitude()),
                    (existingValue, newValue) -> existingValue,
                    HashMap::new));

    // Populate the neighbours by exploring all ways.
    HashMap<Vertex, HashSet<Vertex>> neighbourMap = new HashMap<>();
    // Also track which vertices were part of a way, we will drop those that are not.
    HashSet<Long> encounteredVertices = new HashSet<>();
    for (Way way : osm.getWays()) {
      for (int i = 0; i < way.getNds().size() - 1; i += 1) {
        Vertex firstVertex = uidVertexMap.get(way.getNds().get(i).getRef());
        Vertex secondVertex = uidVertexMap.get(way.getNds().get(i + 1).getRef());

        encounteredVertices.add(firstVertex.uid());
        encounteredVertices.add(secondVertex.uid());

        neighbourMap.putIfAbsent(firstVertex, new HashSet<>());
        neighbourMap.putIfAbsent(secondVertex, new HashSet<>());

        neighbourMap.get(firstVertex).add(secondVertex);
        neighbourMap.get(secondVertex).add(firstVertex);
      }
    }

    // Quick and dirty extraction of names of ways.
    HashMap<Pair<Vertex>, String> labelMap = new HashMap<>();
    for (Way way : osm.getWays()) {
      if (way.getTags() == null) {
        continue;
      }

      Optional<Tag> labelTagOptional =
          way.getTags().stream().filter(t -> Objects.equals(t.getK(), "name")).findFirst();

      if (labelTagOptional.isPresent()) {
        for (int i = 0; i < way.getNds().size() - 1; i += 1) {
          Vertex firstVertex = uidVertexMap.get(way.getNds().get(i).getRef());
          Vertex secondVertex = uidVertexMap.get(way.getNds().get(i + 1).getRef());
          if (firstVertex != null && secondVertex != null) {
            labelMap.put(
                new Pair<Vertex>(firstVertex, secondVertex), labelTagOptional.get().getV());
            labelMap.put(
                new Pair<Vertex>(secondVertex, firstVertex), labelTagOptional.get().getV());
          }
        }
      }
    }

    // Turn our temporary map into a minimal node register with referential integrity with the
    // edges.
    HashMap<Long, Vertex> encounterUidVertexMap =
        uidVertexMap.entrySet().stream()
            .filter(e -> encounteredVertices.contains(e.getKey()))
            .collect(
                Collectors.toMap(
                    Map.Entry::getKey,
                    Map.Entry::getValue,
                    (existingValue, newValue) -> existingValue,
                    HashMap::new));

    return new Graph(encounterUidVertexMap, neighbourMap, labelMap);
  }

  public Vertex findVertex(Long id) throws RuntimeException {
    if (!this.uidVertexMap.containsKey(id)) {
      throw new RuntimeException(String.format("ID %d not known in map!", id));
    }
    return this.uidVertexMap.get(id);
  }

  public String findLabel(Vertex firstVertex, Vertex secondVertex) throws RuntimeException {
    Pair<Vertex> pair = new Pair<Vertex>(firstVertex, secondVertex);
    if (!this.labelMap.containsKey(pair)) {
      throw new RuntimeException(String.format("Edge %s not known in map!", pair));
    }
    return this.labelMap.get(pair);
  }
}
