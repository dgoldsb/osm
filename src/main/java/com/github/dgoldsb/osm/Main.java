package com.github.dgoldsb.osm;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.github.dgoldsb.osm.parser.Osm;
import com.github.dgoldsb.osm.parser.XmlParser;
import com.github.dgoldsb.osm.routing.Graph;
import com.github.dgoldsb.osm.routing.ShortestPathRouter;
import com.github.dgoldsb.osm.routing.Vertex;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) throws IOException {
    // God I last used scanner in university haha.
    Scanner scanner = new Scanner(System.in);

    // Parse the OSM file.
    // TODO: Map selection (or something).
    File xmlFile = new File("src/main/resources/maps/test.osm");
    XmlMapper xmlMapper = new XmlMapper();
    XmlParser xmlParser = new XmlParser(xmlMapper);
    Osm osm = xmlParser.parse(xmlFile);

    // Parse the graph from the OSM.
    Graph graph = Graph.fromOsm(osm);

    // Request for input to the routin algorithm.
    System.out.print("Enter start node UID: ");
    Long startNodeUid = Long.valueOf(scanner.nextLine());
    System.out.print("Enter end node UID: ");
    Long endNodeUid = Long.valueOf(scanner.nextLine());

    // Run the router.
    ShortestPathRouter router = new ShortestPathRouter();
    Vertex start = graph.findVertex(startNodeUid);
    Vertex end = graph.findVertex(endNodeUid);
    List<Vertex> route = router.findShortestPath(graph, start, end);

    // TODO: Move to separate method/class.
    System.out.printf(String.format("Route found traversing %d nodes\n", route.size()));
    List<String> labeledRoute = new ArrayList<>();
    HashMap<String, Double> distanceOnLabel = new HashMap<>();
    for (int i = 0; i < route.size() - 1; i += 1) {
      Vertex firstVertex = route.get(i);
      Vertex secondVertex = route.get(i + 1);
      try {
        String name = graph.findLabel(firstVertex, secondVertex);
        distanceOnLabel.putIfAbsent(name, 0.0);
        double cumulativeDistance =
            distanceOnLabel.getOrDefault(name, 0.0)
                + firstVertex.calculateDistanceToNode(secondVertex);
        distanceOnLabel.put(name, cumulativeDistance);
        if (labeledRoute.isEmpty() || !name.equals(labeledRoute.getLast())) {
          labeledRoute.add(name);
        }
      } catch (RuntimeException ignored) {
      }
    }

    for (String label : labeledRoute) {
      // Heuristic: sometimes a street not taken briefly enters the shortest path because of node
      // (mis)placement.
      if (distanceOnLabel.getOrDefault(label, 0.0) > 10.0) {
        System.out.printf(String.format("  %s\n", label));
      }
    }

    double lengthMeters = 0.0;
    for (int i = 1; i < route.size(); i++) {
      lengthMeters += route.get(i - 1).calculateDistanceToNode(route.get(i));
    }
    System.out.printf(String.format("Route is of length %f meters\n", lengthMeters));
  }
}
