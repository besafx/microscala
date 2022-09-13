package com.besafx.redis.model

import java.util.UUID

import akka.util.ByteString
import play.api.libs.json.{Format, JsError, JsResult, JsString, JsSuccess, JsValue, Json, Reads, Writes}
import redis.ByteStringFormatter

case class PoolDeliveryDurationUUID(value: UUID) extends AnyVal

object PoolDeliveryDurationUUID {

  implicit object UUIDFormat extends Format[PoolDeliveryDurationUUID] {
    def writes(uuid: PoolDeliveryDurationUUID): JsValue = JsString(uuid.value.toString)
    def reads(json: JsValue): JsResult[PoolDeliveryDurationUUID] =
      json match {
        case JsString(id) => JsSuccess(PoolDeliveryDurationUUID(UUID.fromString(id)))
        case _            => JsError("Expected UUID as JsString")
      }
  }

  def apply(uuid: String): PoolDeliveryDurationUUID = PoolDeliveryDurationUUID(UUID.fromString(uuid))

  def randomUUID(): PoolDeliveryDurationUUID = PoolDeliveryDurationUUID(UUID.randomUUID())
}

case class PoolDeliveryDuration(
    uuid: PoolDeliveryDurationUUID,
    poolUUID: PoolUUID,
    distanceUpTo: Int,
    bucketDurationInMinutes: Int
)


object PoolDeliveryDuration {

  implicit val poolDeliveryDurationReads: Reads[PoolDeliveryDuration] = Json.reads[PoolDeliveryDuration]
  implicit val poolDeliveryDurationWrites: Writes[PoolDeliveryDuration] = Json.writes[PoolDeliveryDuration]

  implicit val byteStringFormatterList: ByteStringFormatter[Seq[PoolDeliveryDuration]] = new ByteStringFormatter[Seq[PoolDeliveryDuration]] {
    def serialize(poolDeliveryDuration: Seq[PoolDeliveryDuration]): ByteString = {
      ByteString(Json.toJson(poolDeliveryDuration).toString())
    }

    def deserialize(byteString: ByteString): Seq[PoolDeliveryDuration] = {
      Json.parse(byteString.toArray).as[Seq[PoolDeliveryDuration]]
    }
  }
}