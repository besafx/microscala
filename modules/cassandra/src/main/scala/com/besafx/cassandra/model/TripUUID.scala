package com.besafx.cassandra.model

import java.util.UUID

import play.api.libs.json.{Json, OWrites, Reads}

case class TripUUID(value: UUID) extends AnyVal

object TripUUID {
  implicit val tripUUIDReads: Reads[TripUUID] = Json.reads[TripUUID]
  implicit val tripUUIDWrites: OWrites[TripUUID] = Json.writes[TripUUID]

}
