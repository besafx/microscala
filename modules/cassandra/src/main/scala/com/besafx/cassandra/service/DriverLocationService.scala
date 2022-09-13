package com.besafx.cassandra.service

import com.besafx.cassandra.database.DriverLocationDBProvider
import com.besafx.cassandra.model.{DriverLocation, DriverUUID}
import javax.inject.Singleton
import org.joda.time.DateTime

import scala.concurrent.Future

// Orchestrate low level queries for DriverLocation
@Singleton
class DriverLocationService extends DriverLocationDBProvider {

  def setDriverLocation(driverUUID: DriverUUID, lat: Double, lon: Double) =
    database.driverLocation.save(DriverLocation(driverUUID, lat, lon, DateTime.now()))

  def getDriverLastLocation(driverUUID: DriverUUID): Future[_] =
    database.driverLocation.getLastLocation(driverUUID)

  def getDriverLastLocationTimestamp(driverUUID: DriverUUID): Future[_] =
    database.driverLocation.getLastLocationTimestamp(driverUUID)
}
