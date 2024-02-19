package com.github.dgoldsb.osm.routing;

import org.junit.jupiter.api.Test;

class VertexTest {

  @Test
  void calculateDistanceToNode() {
    // Arrange
    Vertex thisNode = new Vertex(123L, 50.0, 60.0);
    Vertex otherNode = new Vertex(234L, 50.5, 60.1);

    // Act
    int distanceKilometers = (int) (thisNode.calculateDistanceToNode(otherNode) / 1000);

    // Assert
    assert distanceKilometers == 56;
  }
}
