package com.besafx.cassandra.database

import com.outworkers.phantom.connectors.{CassandraConnection, ContactPoint, KeySpace, KeySpaceBuilder}
import com.outworkers.phantom.dsl._
import com.typesafe.config.ConfigFactory
import javax.inject.Singleton

@Singleton
class CassandraContext {

  private val config            = ConfigFactory.load()
  def CassandraEnabled: Boolean = true

  // Cassandra
  lazy val keySpace: KeySpace = KeySpace(config.getString("cassandra.keyspace"))
  lazy val contactPoint: KeySpaceBuilder = ContactPoint(
    host = config.getString("cassandra.host"),
    port = config.getInt("cassandra.port")
  )

  private lazy val cassandraConnectionOptional: Option[CassandraConnection] =
    if (CassandraEnabled)
      Some(
        contactPoint
          .keySpace(
            KeySpace(keySpace.name)
              .ifNotExists()
              .`with`(
                replication eqs SimpleStrategy.replication_factor(1)
              )
          ))
    else None

  def cassandraConnection: CassandraConnection =
    cassandraConnectionOptional match {
      case Some(connection) => connection
      case None             => throw UnInitializedCassandraContext("Cassandra connection not initialized")
    }

  def shutdownCassandraClient(): Unit =
    if (CassandraEnabled) {
      val session = cassandraConnection.session
      session.getCluster.close()
      session.close()
    }

}
