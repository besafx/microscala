package com.besafx.redis.model

import java.util.UUID

import play.api.libs.json.{Format, JsError, JsResult, JsString, JsSuccess, JsValue}

case class TripUUID(value: UUID) extends AnyVal

object TripUUID {
  implicit object UUIDFormat extends Format[TripUUID] {
    def writes(uuid: TripUUID): JsValue = JsString(uuid.value.toString)
    def reads(json: JsValue): JsResult[TripUUID] =
      json match {
        case JsString(id) => JsSuccess(TripUUID(UUID.fromString(id)))
        case _            => JsError("Expected UUID as JsString")
      }
  }
}
