package com.github.dgoldsb.osm.parser;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class NodeTest {

  @Test
  void calculateDistanceToNode() {
    // Arrange
    Node thisNode = new Node();
    thisNode.setLatitude(50.0);
    thisNode.setLongitude(60.0);
    Node otherNode = new Node();
    otherNode.setLatitude(50.5);
    otherNode.setLongitude(60.1);

    // Act
    int distanceKilometers = (int) (thisNode.calculateDistanceToNode(otherNode) / 1000);

    // Assert
    assert distanceKilometers == 56;
  }
}
