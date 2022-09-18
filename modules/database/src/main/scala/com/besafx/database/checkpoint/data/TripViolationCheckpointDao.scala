package com.besafx.database.checkpoint.data

import java.sql.Timestamp

import com.besafx.database.checkpoint.repository.CommonSlick
import org.joda.time.DateTime
import play.api.db.slick.HasDatabaseConfig
import slick.ast.BaseTypedType
import slick.jdbc.{JdbcProfile, JdbcType}
import slick.lifted.ProvenShape

trait TripViolationCheckpointDao extends CommonSlick {
  self: HasDatabaseConfig[JdbcProfile] =>

  import profile.api._

  val timestampJodaDateTimeWithMinutesOnly: JdbcType[DateTime] with BaseTypedType[DateTime] =
    MappedColumnType.base[DateTime, Timestamp](
      dateTime => new Timestamp(dateTime.withSecondOfMinute(0).withMillisOfSecond(0).getMillis),
      timestamp => new DateTime(timestamp)
    )

  class TripViolationCheckpointTable(tag: Tag) extends Table[TripViolationCheckpointDB](tag, "trip_violation_checkpoint") {

    def uuid: Rep[String]                         = column[String]("uuid")
    def checkDate: Rep[DateTime]                  = column[DateTime]("check_date")(timestampJodaDateTimeWithMinutesOnly)
    def initialEta: Rep[DateTime]                 = column[DateTime]("initial_eta")
    def violationType: Rep[TripViolationTypeEnum] = column[TripViolationTypeEnum]("violation_type")

    override def * : ProvenShape[TripViolationCheckpointDB] =
      (
        uuid,
        checkDate,
        initialEta,
        violationType
      ) <> (TripViolationCheckpointDB.tupled, TripViolationCheckpointDB.unapply)
  }

  lazy val TripViolationCheckpointTable = TableQuery[TripViolationCheckpointTable]
}
