package com.github.dgoldsb.osm;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.github.dgoldsb.osm.parser.Osm;
import com.github.dgoldsb.osm.parser.XmlParser;
import com.github.dgoldsb.osm.routing.Graph;
import com.github.dgoldsb.osm.routing.ShortestPathRouter;
import com.github.dgoldsb.osm.routing.Vertex;
import java.io.File;
import java.io.IOException;
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
    Vertex end = graph.findVertex(startNodeUid);
    List<Vertex> route = router.findShortestPath(graph, start, end);

    System.out.printf(String.format("Route found traversing %d nodes", route.size()));
  }
}
