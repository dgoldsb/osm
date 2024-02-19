package com.github.dgoldsb.osm.routing;

public record Vertex(long uid, double latitude, double longitude) {
  /**
   * Returns the distance between this node and the other in meters.
   *
   * @param other other node to compare distance to
   * @return distance in meters
   */
  public Double calculateDistanceToNode(Vertex other) {
    // Convert latitude and longitude from degrees to radians.
    double thisLatitudeRadians = Math.toRadians(this.latitude);
    double thisLongitudeRadians = Math.toRadians(this.longitude);
    double otherLatitudeRadians = Math.toRadians(other.latitude);
    double otherLongitudeRadians = Math.toRadians(other.longitude);

    // Calculate the differences between latitudes and longitudes.
    double deltaLatitude = otherLatitudeRadians - thisLatitudeRadians;
    double deltaLongitude = otherLongitudeRadians - thisLongitudeRadians;

    // Haversine formula.
    double a =
        Math.pow(Math.sin(deltaLatitude / 2), 2)
            + Math.cos(thisLatitudeRadians)
                * Math.cos(otherLatitudeRadians)
                * Math.pow(Math.sin(deltaLongitude / 2), 2);
    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

    return 6378100 * c;
  }
}
