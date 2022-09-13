package com.besafx.database.checkpoint.repository

import com.github.tototoshi.slick.GenericJodaSupport
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfig}
import slick.jdbc.JdbcProfile

import scala.concurrent.Future

abstract class SlickRepository(databaseConfigProvider: DatabaseConfigProvider) extends HasDatabaseConfig[JdbcProfile] {

  import profile.api._

  override protected val dbConfig = databaseConfigProvider.get

  protected val jodaSupport: GenericJodaSupport = new GenericJodaSupport(profile)

  implicit class DBIOActionOps[A](action: DBIO[A]) {
    def run: Future[A] = db.run(action)
  }
}
