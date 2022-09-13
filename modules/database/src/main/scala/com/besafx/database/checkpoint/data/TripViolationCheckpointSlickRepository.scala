package com.besafx.database.checkpoint.data

import com.besafx.database.checkpoint.mapper.TripViolationCheckpointMapper
import com.besafx.database.checkpoint.model.TripViolationCheckpoint
import com.besafx.database.checkpoint.repository.{SlickRepository, TripViolationCheckpointRepository}
import javax.inject.{Inject, Singleton}
import org.joda.time.DateTime
import play.api.db.slick.DatabaseConfigProvider

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class TripViolationCheckpointSlickRepository @Inject() (
    databaseConfigProvider: DatabaseConfigProvider,
    tripViolationCheckpointMapper: TripViolationCheckpointMapper
)(implicit val executionContext: ExecutionContext)
    extends SlickRepository(databaseConfigProvider)
    with TripViolationCheckpointRepository
    with TripViolationCheckpointDao {

  import profile.api._

  override def add(tripViolationCheckpoint: TripViolationCheckpoint): Future[Boolean] =
    (TripViolationCheckpointTable += tripViolationCheckpointMapper.fromDomain(tripViolationCheckpoint))
      .map(_ == 1)
      .run

  override def getAllByCheckDateEquals(date: DateTime): Future[Seq[TripViolationCheckpoint]] =
    TripViolationCheckpointTable
      .filter(_.initialEta === date)
      .result
      .map(_.map(tripViolationCheckpointMapper.toDomain))
      .run
}
