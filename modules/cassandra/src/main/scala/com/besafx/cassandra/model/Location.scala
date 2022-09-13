package com.besafx.cassandra.model

import play.api.libs.json.Json

case class Location(lat: Double,
                    lon: Double)
object Location {

  implicit val locatioWrites = Json.writes[Location]
  implicit val locationReads  = Json.reads[Location]


}