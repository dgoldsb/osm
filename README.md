# README

## Setup

- Install Java 21, for instance [Corretto JDK](https://docs.aws.amazon.com/corretto/latest/corretto-21-ug/downloads-list.html).
- Place your `.osm` file(s) in the [resources](src/main/resources/maps). The OSM filetype is ignored in Git, so you don't accidentally dox yourself.

## 

Some thoughts one interesting concerns.

- Traversability of ways per mode of transport. Easiest is to assume any way is valid, but we should separate this concern in a neat way later.
- Code formatting.
