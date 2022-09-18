package com.besafx.database.checkpoint.model

import com.besafx.database.checkpoint.data.TripViolationTypeEnum
import org.joda.time.DateTime

case class TripViolationCheckpoint(
    uuid: TripViolationCheckpointUUID,
    checkDate: DateTime,
    initialEta: DateTime,
    violationType: TripViolationTypeEnum
)
