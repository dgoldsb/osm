package com.github.dgoldsb.osm.routing;

import com.github.dgoldsb.osm.parser.Nd;
import com.github.dgoldsb.osm.parser.Node;
import com.github.dgoldsb.osm.parser.Tag;
import com.github.dgoldsb.osm.parser.Way;
import java.util.*;
import java.util.stream.Collectors;

/** In memory graph definition, expected as an input for a routing algorithm. */
public record Graph(
    HashMap<Vertex, HashSet<Vertex>> neighbourMap, HashMap<Vertex, String> labelMap) {

  /**
   * Instantiate a graph from OSM nodes and ways.
   *
   * @param nodes nodes in the map to load
   * @param ways ways in the map to load
   * @return an instantiated Graph object
   */
  public static Graph fromNodes(List<Node> nodes, List<Way> ways) {
    // Create a temporary map of UID to vertex.
    HashMap<Long, Vertex> uidVertexMap =
        nodes.stream()
            .collect(
                Collectors.toMap(
                    Node::getUid,
                    n -> new Vertex(n.getUid(), n.getLatitude(), n.getLongitude()),
                    (existingValue, newValue) -> existingValue,
                    HashMap::new));

    // Populate the neighbours by exploring all ways.
    HashMap<Vertex, HashSet<Vertex>> neighbourMap = new HashMap<>();
    for (Way way : ways) {
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
    for (Way way : ways) {
      Optional<Tag> labelTagOptional =
          way.getTags().stream().filter(t -> Objects.equals(t.getK(), "name")).findFirst();

      if (labelTagOptional.isPresent()) {
        for (Nd nd : way.getNds()) {
          Vertex vertex = uidVertexMap.get(nd.getRef());
          labelMap.put(vertex, labelTagOptional.get().getV());
        }
      }
    }

    return new Graph(neighbourMap, labelMap);
  }
}
