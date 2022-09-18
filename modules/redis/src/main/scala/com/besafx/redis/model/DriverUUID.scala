package com.besafx.redis.model

import java.util.UUID

import play.api.libs.json._

case class DriverUUID(value: UUID) extends AnyVal

object DriverUUID {
  implicit object UUIDFormat extends Format[DriverUUID] {
    def writes(uuid: DriverUUID): JsValue = JsString(uuid.value.toString)
    def reads(json: JsValue): JsResult[DriverUUID] =
      json match {
        case JsString(id) => JsSuccess(DriverUUID(UUID.fromString(id)))
        case _            => JsError("Expected UUID as JsString")
      }
  }
}
