package com.besafx.database.checkpoint.model

import java.util.UUID

import play.api.libs.json.{Json, OWrites, Reads}

case class TripViolationCheckpointUUID(value: UUID) extends AnyVal

object TripViolationCheckpointUUID {
  implicit val tripViolationCheckpointUUIDReads: Reads[TripViolationCheckpointUUID]    = Json.reads[TripViolationCheckpointUUID]
  implicit val tripViolationCheckpointUUIDWrites: OWrites[TripViolationCheckpointUUID] = Json.writes[TripViolationCheckpointUUID]

  def apply(uuid: String): TripViolationCheckpointUUID = TripViolationCheckpointUUID(UUID.fromString(uuid))

  def randomUUID(): TripViolationCheckpointUUID = TripViolationCheckpointUUID(UUID.randomUUID())

}
