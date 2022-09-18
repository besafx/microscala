package com.besafx.database.controller

import java.time.ZoneOffset

import com.besafx.database.checkpoint.data.TripViolationTypeEnum
import com.besafx.database.checkpoint.data.TripViolationTypeEnum.{FM_Detection, FM_Prevention}
import com.besafx.database.checkpoint.model.{TripViolationCheckpoint, TripViolationCheckpointUUID}
import com.besafx.database.checkpoint.repository.TripViolationCheckpointRepository
import org.joda.time.{DateTime, DateTimeZone, LocalDateTime}
import org.joda.time.format.{DateTimeFormat, DateTimeFormatter}
import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneAppPerSuite
import play.api.inject.guice.GuiceApplicationBuilder
import play.api.{Application, Logger}

import scala.concurrent.duration._
import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global

class TestGeneral extends PlaySpec with GuiceOneAppPerSuite {

  val logger: Logger = Logger(classOf[TestGeneral])

  // Override fakeApplication if you need a Application with other than
  // default parameters.
  override def fakeApplication(): Application =
    GuiceApplicationBuilder().configure(Map("ehcacheplugin" -> "disabled")).build()

  "Test 1" must {
    "Test 1 Description" in {
      app.configuration.getOptional[String]("ehcacheplugin") mustBe Some("disabled")
      val repo: TripViolationCheckpointRepository = app.injector.instanceOf[TripViolationCheckpointRepository]
      assert(repo != null)

      Await.result(
        repo
          .add(
            TripViolationCheckpoint(
              TripViolationCheckpointUUID.randomUUID(),
              DateTime.now(),
              DateTime.now(),
              TripViolationTypeEnum.FM_Prevention
            ))
          .map { result =>
            logger.error(result.toString)
          },
        5.second
      )
    }
  }

  "Test 2" must {
    "Test 2 Description" in {
      app.configuration.getOptional[String]("ehcacheplugin") mustBe Some("disabled")
      val repo: TripViolationCheckpointRepository = app.injector.instanceOf[TripViolationCheckpointRepository]
      assert(repo != null)

      val format: DateTimeFormatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")
      val dateTime: LocalDateTime   = format.parseLocalDateTime("2022-09-13 03:20:00")
      val now: DateTime             = dateTime.toDateTime(DateTimeZone.UTC)

      Await.result(
        repo
          .getAllByCheckDateEquals(now)
          .map { result =>
            logger.error(result.toString)
          },
        5.second
      )
    }
  }

  "Test 3" must {
    "Test 3 Description" in {
      app.configuration.getOptional[String]("ehcacheplugin") mustBe Some("disabled")
      val repo: TripViolationCheckpointRepository = app.injector.instanceOf[TripViolationCheckpointRepository]
      assert(repo != null)

      val format: DateTimeFormatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")
      val dateTime: LocalDateTime   = format.parseLocalDateTime("2022-09-14 12:06:00")
      val now: DateTime             = dateTime.toDateTime(DateTimeZone.UTC)

      Await.result(
        repo
          .add(
            TripViolationCheckpoint(
              TripViolationCheckpointUUID.randomUUID(),
              now,
              now,
              TripViolationTypeEnum.FM_Detection
            ))
          .map { result =>
            logger.error(result.toString)
          },
        5.second
      )

      Await.result(
        repo
          .add(
            TripViolationCheckpoint(
              TripViolationCheckpointUUID.randomUUID(),
              now,
              now,
              TripViolationTypeEnum.FM_Prevention
            ))
          .map { result =>
            logger.error(result.toString)
          },
        5.second
      )

      Await.result(
        repo
          .getAllByCheckDateEquals(now)
          .map { result =>
            logger.error(result.toString)
          },
        5.second
      )

      Await.result(
        repo
          .deleteAllByTripAndCheckDateAfter(
            now.minusMinutes(5),
            Some(Seq(FM_Prevention, FM_Detection))
          )
          .map { result =>
            logger.error(result.toString)
          },
        5.second
      )
    }
  }

}
