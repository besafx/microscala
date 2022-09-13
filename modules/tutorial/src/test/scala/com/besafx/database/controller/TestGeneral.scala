package com.besafx.database.controller

import org.scalatest.BeforeAndAfterAll
import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneAppPerSuite
import play.api.Logger
import play.api.db.DBApi
import play.api.db.evolutions.Evolutions
import play.api.inject.guice.GuiceApplicationBuilder

class TestGeneral extends PlaySpec with GuiceOneAppPerSuite with BeforeAndAfterAll {

  val logger: Logger = Logger(classOf[TestGeneral])

  lazy val appBuilder  = new GuiceApplicationBuilder()
  lazy val injector    = appBuilder.injector()
  lazy val databaseApi = injector.instanceOf[DBApi]

  override def beforeAll() =
    Evolutions.applyEvolutions(databaseApi.database("tutorial"))

  override def afterAll() =
    Evolutions.cleanupEvolutions(databaseApi.database("tutorial"))

}
