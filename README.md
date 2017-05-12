# Project Name: Wildlife Tracker

An an application that allows Rangers to track wildlife sightings in the area.

## Created by: Minneh Mugo

## Prerequisites

You will need the following things properly installed on your computer.

* JRE
* JDK

## Installation

* `git clone <https://github.com/Minneh/wildlife-tracker>` this repository
* `cd wildlife-tracker`

## Running / Development

* `gradle run`

### Running Tests

* `gradle test`

### Building

* `gradle build`

### SQL

CREATE DATABASE wildlife_tracker;

CREATE TABLE animals (
  id serial PRIMARY KEY,
  name varchar,
  health varchar,
  age varchar,
  is_endangered boolean
);

CREATE TABLE sightings (
  id serial PRIMARY KEY,
  animal_id int,
  location varchar,
  ranger_name varchar
  )
