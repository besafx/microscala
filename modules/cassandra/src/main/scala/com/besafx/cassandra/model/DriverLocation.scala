package com.besafx.cassandra.model

import com.outworkers.phantom.dsl._
import org.joda.time.DateTime

import scala.concurrent.Future
import scala.language.postfixOps

// Base model used as DTO
case class DriverLocation(
    driverUUID: DriverUUID,
    lat: Double,
    lon: Double,
    timestamp: DateTime
)

// Query based model
abstract class DriverLocationEntity extends Table[DriverLocationEntity, DriverLocation] {
  // Table metadata
  override def tableName: String = "driver_location"

  // Table schema
  object driverUUID extends UUIDColumn with PartitionKey
  object lat        extends DoubleColumn
  object lon        extends DoubleColumn
  object timestamp  extends DateTimeColumn with ClusteringOrder with Descending

  // Low level queries to store driver location
  def save(driverLocation: DriverLocation): Future[ResultSet] =
    insert()
      .value(_.driverUUID, driverLocation.driverUUID.value)
      .value(_.lat, driverLocation.lat)
      .value(_.lon, driverLocation.lon)
      .value(_.timestamp, driverLocation.timestamp)
      .future()

  // Low level queries to get driver last location
  def getLastLocation(driverUUID: DriverUUID): Future[_] =
    select
      .all()
      .where(_.driverUUID eqs driverUUID.value)
      .orderBy(_.timestamp desc)
      .limit(1)
      .fetch()

  // Low level queries to get last location with timestamp
  def getLastLocationTimestamp(driverUUID: DriverUUID): Future[_] =
    select(_.driverUUID, _.timestamp)
      .where(_.driverUUID eqs driverUUID.value)
      .orderBy(_.timestamp desc)
      .limit(1)
      .fetch()
}
