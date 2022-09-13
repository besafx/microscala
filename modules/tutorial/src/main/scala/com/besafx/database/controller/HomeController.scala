package com.besafx.database.controller

import javax.inject.{Inject, Singleton}
import play.api.mvc.{AbstractController, Action, AnyContent, ControllerComponents, Request}

@Singleton
class HomeController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  def healthCheck: Action[AnyContent] =
    Action { implicit request: Request[AnyContent] =>
      Ok("Service is up and running!")
    }
}
