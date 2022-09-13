package com.besafx.database.checkpoint.model

import org.joda.time.DateTime

case class TripViolationCheckpoint(
    uuid: TripViolationCheckpointUUID,
    checkDate: DateTime,
    initialEta: DateTime
)
