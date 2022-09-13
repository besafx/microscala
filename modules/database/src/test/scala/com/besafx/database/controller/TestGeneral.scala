package com.besafx.database.controller

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
              DateTime.now()
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
      val dateTime: LocalDateTime   = format.parseLocalDateTime("2022-09-13 18:57:00")
      val now: DateTime             = dateTime.toDateTime

      Await.result(
        repo
          .getAllByCheckDateEquals(DateTime.now(DateTimeZone.UTC))
          .map { result =>
            logger.error(result.toString)
          },
        5.second
      )
    }
  }

}
