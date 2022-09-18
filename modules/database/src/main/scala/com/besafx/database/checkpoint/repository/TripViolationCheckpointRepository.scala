package com.besafx.database.checkpoint.repository

import com.besafx.database.checkpoint.data.TripViolationTypeEnum
import com.besafx.database.checkpoint.model.TripViolationCheckpoint
import org.joda.time.DateTime

import scala.concurrent.Future

trait TripViolationCheckpointRepository {

  def add(tripViolationCheckpoint: TripViolationCheckpoint): Future[Boolean]

  def getAllByCheckDateEquals(date: DateTime): Future[Seq[TripViolationCheckpoint]]

  def deleteAllByTripAndCheckDateAfter(date: DateTime, tripViolationTypeEnumOpt: Option[Seq[TripViolationTypeEnum]]): Future[Boolean]
}
