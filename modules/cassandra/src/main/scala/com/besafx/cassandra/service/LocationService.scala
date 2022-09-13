package com.besafx.cassandra.service

import com.besafx.cassandra.model.{Location, TripUUID}
import com.elmenus.distancematrix.model.{TripDistanceRequest, TripDistanceResponse, TripDurationInfo, TripInfo, Location => LocationMatrix}
import com.elmenus.distancematrix.service.impl.DistanceMatrixFactory
import com.elmenus.distancematrix.utils.enums.TravelMode
import javax.inject.{Inject, Singleton}
import play.api.Logger

import scala.collection.mutable.ArrayBuffer
import scala.jdk.CollectionConverters._

@Singleton
class LocationService @Inject() (distanceMatrixFactory: DistanceMatrixFactory) {

  val logger: Logger = Logger(classOf[LocationService])

  def printMessage() = print("Hello")

  def getEstimatedDistanceAndDuration(tripUUID: TripUUID, startLocation: Location, endLocation: Location): Option[TripDurationInfo] =
    try {
      val request: TripDistanceRequest = new TripDistanceRequest()

      val startPoint: TripInfo                = new TripInfo()
      val startLocationMatrix: LocationMatrix = new LocationMatrix()
      startLocationMatrix.setLat(startLocation.lat)
      startLocationMatrix.setLon(startLocation.lon)
      startPoint.setLocation(startLocationMatrix)
      startPoint.setUuid(tripUUID.value.toString)
      startPoint.setTravelMode(TravelMode.DRIVING)

      val endPoint: TripInfo                = new TripInfo()
      val endLocationMatrix: LocationMatrix = new LocationMatrix()
      endLocationMatrix.setLat(endLocation.lat)
      endLocationMatrix.setLon(endLocation.lon)
      endPoint.setLocation(endLocationMatrix)
      endPoint.setUuid(tripUUID.value.toString)
      endPoint.setTravelMode(TravelMode.DRIVING)

      request.setTripOriginInfo(ArrayBuffer(startPoint).asJava)
      request.setTripDestinationInfo(ArrayBuffer(endPoint).asJava)

      val response: TripDistanceResponse = distanceMatrixFactory.getDistanceMatrixInstance
        .getTripDistanceDurationInfo(request)

      val estimated = response.getTripDurationInfo.entrySet().iterator().next().getValue.get(0)
      logger.error(
        s"Estimated distance: ${estimated.getDistanceInText}" +
            s" and Estimated duration: ${estimated.getDurationInText}" +
            s" for trip: ${tripUUID.value.toString}")
      Some(estimated)

    } catch {
      case e: Exception =>
        logger.error(s"error on measuring estimated distance between locations: ${e.getLocalizedMessage}")
        None
    }

}
