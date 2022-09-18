package com.besafx.database.checkpoint.data

import org.joda.time.DateTime

case class TripViolationCheckpointDB(
    uuid: String,
    checkDate: DateTime,
    initialEta: DateTime,
    violationType: TripViolationTypeEnum
)
