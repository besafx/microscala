package com.besafx.cassandra.service

import java.util.UUID

import com.besafx.cassandra.model.{Location, TripUUID}
import org.scalatest.concurrent.ScalaFutures
import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneServerPerSuite

class LocationServiceTest extends PlaySpec with GuiceOneServerPerSuite with ScalaFutures {
  val service = app.injector.instanceOf(classOf[LocationService])

  service.printMessage()
  val startLocation: Location = Location(55.713066579284686, 13.209229870373779);
  val endLocation: Location = Location(0, 0);
  service.getEstimatedDistanceAndDuration(TripUUID(UUID.randomUUID()), startLocation, endLocation)
}
