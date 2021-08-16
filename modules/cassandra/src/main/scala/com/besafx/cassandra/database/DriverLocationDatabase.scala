package com.besafx.cassandra.database

import com.besafx.cassandra.model.DriverLocationEntity
import com.outworkers.phantom.connectors.CassandraConnection
import com.outworkers.phantom.database.DatabaseProvider
import com.outworkers.phantom.dsl.{Database, _}

// This class will encapsulate all the valid database instances
class DriverLocationDatabase(override val connector: CassandraConnection)
  extends Database[DriverLocationDatabase](connector) {
  object driverLocation extends DriverLocationEntity with Connector
}

object Connector extends CassandraContext

object Database extends DriverLocationDatabase(Connector.cassandraConnection)

trait DriverLocationDBProvider extends DatabaseProvider[DriverLocationDatabase] {
  override def database: DriverLocationDatabase = Database
  database.create()
}
