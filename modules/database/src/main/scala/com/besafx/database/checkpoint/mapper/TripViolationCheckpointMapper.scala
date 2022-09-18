package com.besafx.database.checkpoint.mapper

import com.besafx.database.checkpoint.data
import com.besafx.database.checkpoint.data.TripViolationCheckpointDB
import com.besafx.database.checkpoint.model.{TripViolationCheckpoint, TripViolationCheckpointUUID}
import javax.inject.Singleton

@Singleton
class TripViolationCheckpointMapper {

  def toDomain(tripViolationCheckpointDB: TripViolationCheckpointDB): TripViolationCheckpoint =
    TripViolationCheckpoint(
      TripViolationCheckpointUUID(tripViolationCheckpointDB.uuid),
      tripViolationCheckpointDB.checkDate,
      tripViolationCheckpointDB.initialEta,
      tripViolationCheckpointDB.violationType
    )

  def fromDomain(tripViolationCheckpoint: TripViolationCheckpoint): TripViolationCheckpointDB =
    data.TripViolationCheckpointDB(
      tripViolationCheckpoint.uuid.value.toString,
      tripViolationCheckpoint.checkDate,
      tripViolationCheckpoint.initialEta,
      tripViolationCheckpoint.violationType
    )
}
