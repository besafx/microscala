package com.besafx.database.checkpoint.repository

import java.sql.Timestamp
import java.time.LocalTime

import org.joda.time.DateTime
import slick.ast.BaseTypedType
import slick.jdbc.MySQLProfile.api._
import slick.jdbc.{GetResult, JdbcType}

trait CommonSlick {

  implicit val timestampJodaDateTime: JdbcType[DateTime] with BaseTypedType[DateTime] =
    MappedColumnType.base[DateTime, Timestamp](
      dateTime => new Timestamp(dateTime.getMillis),
      timestamp => new DateTime(timestamp)
    )
  implicit val GetLocalTime: AnyRef with GetResult[LocalTime] =
    GetResult[LocalTime] { r =>
      LocalTime.parse(r.nextTime().toString)
    }

}
