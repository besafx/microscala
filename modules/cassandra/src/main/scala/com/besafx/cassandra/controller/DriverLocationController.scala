package com.besafx.cassandra.controller

import java.util.UUID

import com.besafx.cassandra.model.DriverUUID
import com.besafx.cassandra.service.DriverLocationService
import javax.inject._
import play.api.mvc._

@Singleton
class DriverLocationController @Inject() (cc: ControllerComponents,
                                          driverLocationService: DriverLocationService) extends AbstractController(cc) {

  def printMessage() =
    Action { implicit request: Request[AnyContent] =>
      driverLocationService.setDriverLocation(DriverUUID(UUID.randomUUID()), 10, 20)
      Ok("Hello World")
    }
}
