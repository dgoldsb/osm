package com.github.dgoldsb.osm.routing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

public class ShortestPathRouter {
  public List<Vertex> findShortestPath(Graph graph, Vertex start, Vertex end)
      throws RuntimeException {
    PriorityQueue<DistanceNodePair> queue = new PriorityQueue<>();
    HashMap<Vertex, Double> bestDistanceMap = new HashMap<>();
    HashMap<Vertex, Vertex> predecessorMap = new HashMap<>();

    // Queue the start node.
    queue.add(new DistanceNodePair(0.0, start));
    bestDistanceMap.put(start, 0.0);

    while (!queue.isEmpty()) {
      Vertex current = queue.remove().node();

      if (current == end) {
        return reconstructPath(predecessorMap, end);
      }

      for (Vertex neighbour : graph.neighbourMap().get(current)) {
        double oldDistance = bestDistanceMap.getOrDefault(neighbour, Double.POSITIVE_INFINITY);
        double newDistance =
            bestDistanceMap.get(current) + current.calculateDistanceToNode(neighbour);

        if (newDistance < oldDistance) {
          bestDistanceMap.put(neighbour, newDistance);
          predecessorMap.put(neighbour, current);

          // Remove previous priority.
          if (oldDistance != Double.POSITIVE_INFINITY) {
            queue.removeIf(n -> n.node() == neighbour);
          }

          queue.add(new DistanceNodePair(newDistance, neighbour));
        }
      }
    }
    throw new RuntimeException("No path found!");
  }

  private List<Vertex> reconstructPath(HashMap<Vertex, Vertex> shortestNeighboursMap, Vertex end) {
    List<Vertex> path = new ArrayList<>(List.of(end));

    while (shortestNeighboursMap.containsKey(path.getLast())) {
      path.add(shortestNeighboursMap.get(path.getLast()));
    }

    return path.reversed();
  }
}
