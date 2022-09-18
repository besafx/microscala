package com.besafx.redis.model

import java.util.UUID

import play.api.libs.json._

case class PoolUUID(value: UUID) extends AnyVal

object PoolUUID {
  implicit object UUIDFormat extends Format[PoolUUID] {
    def writes(uuid: PoolUUID): JsValue = JsString(uuid.value.toString)
    def reads(json: JsValue): JsResult[PoolUUID] =
      json match {
        case JsString(id) => JsSuccess(PoolUUID(UUID.fromString(id)))
        case _            => JsError("Expected UUID as JsString")
      }
  }
}
