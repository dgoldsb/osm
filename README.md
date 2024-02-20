# README

## Setup

- Install Java 21, for instance [Corretto JDK](https://docs.aws.amazon.com/corretto/latest/corretto-21-ug/downloads-list.html).
- Place your `.osm` file(s) in the [resources](src/main/resources/maps). The OSM filetype is ignored in Git, so you don't accidentally dox yourself.

## Running

In the current form running this code is a bit cumbersome.

1. Download a map from [OSM](https://www.openstreetmap.org/).
2. Place the map [here](src/main/resources/maps/test.osm).
3. In the map data, find two nodes you want to route between. This is easiest by looking for `way` element with a particular street name, and taking a node from that way.
4. Run the main and supply the IDs when prompted.

## TODO

Some thoughts one interesting concerns.

- Find start and end node by street name.
- List all street names related to the vertices in the route (or even better, omit irrelevant ones by storing the labels on pairs of vertices).
- Traversability of ways per mode of transport. Easiest is to assume any way is valid, but we should separate this concern in a neat way later.

