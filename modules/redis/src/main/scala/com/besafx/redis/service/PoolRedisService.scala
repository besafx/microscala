package com.besafx.redis.service

import java.util.UUID

import com.besafx.redis.config.RedisContext
import com.besafx.redis.model.{DriverUUID, PoolDeliveryDuration, PoolUUID}
import javax.inject.{Inject, Singleton}
import play.api.Logger
import play.api.libs.json.Json

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

@Singleton
class PoolRedisService @Inject() (redisContext: RedisContext) {

  val logger: Logger = Logger(classOf[PoolRedisService])

  private val POOL_READY_KEY_PREFIX    = "driver:ready:"
  private val POOL_BUSY_KEY_PREFIX     = "driver:busy:"
  private val POOL_DURATION_KEY_PREFIX = "pool:duration:"
  private val POOL_FEES_KEY_PREFIX     = "pool:fees:"

  def setPoolDurations(list: Seq[PoolDeliveryDuration]): Future[Boolean] =
    redisContext.redisClient.set[Seq[PoolDeliveryDuration]](POOL_DURATION_KEY_PREFIX + list.head.poolUUID.value.toString, list)

  def deleteCachePoolDurations(poolUUID: PoolUUID): Future[Long] =
    redisContext.redisClient.del(POOL_DURATION_KEY_PREFIX + poolUUID.value.toString)

  def getPoolDurations(poolUUID: PoolUUID): Future[Seq[PoolDeliveryDuration]] =
    redisContext.redisClient
      .get(POOL_DURATION_KEY_PREFIX + poolUUID.value.toString)
      .map {
        case Some(byteString) => PoolDeliveryDuration.byteStringFormatterList.deserialize(byteString)
        case None             => Seq.empty
      }

  def addToReadyPool(poolUUID: PoolUUID, driverUUID: DriverUUID): Future[Long] = {
    val driverUUIDString = driverUUID.value.toString
    val poolUUIDString   = poolUUID.value.toString
    logger.debug(s"adding driver $driverUUIDString to ready pool $poolUUIDString")
    redisContext.redisClient.zaddWithTimestamp(POOL_READY_KEY_PREFIX + poolUUIDString, driverUUIDString)
  }

  def addToBusyPool(poolUUID: PoolUUID, driverUUID: DriverUUID): Future[Long] = {
    val driverUUIDString = driverUUID.value.toString
    val poolUUIDString   = poolUUID.value.toString
    logger.debug(s"add driver $driverUUIDString to busy pool $poolUUIDString")
    redisContext.redisClient.zaddWithTimestamp(POOL_BUSY_KEY_PREFIX + poolUUIDString, driverUUIDString)
  }

  def getReadyPoolsKeys: Future[Seq[String]] =
    redisContext.redisClient.keys(POOL_READY_KEY_PREFIX + "*")

  def getBusyPoolsKeys: Future[Seq[String]] =
    redisContext.redisClient.keys(POOL_BUSY_KEY_PREFIX + "*")

  def getPoolReadyDrivers(poolUUID: PoolUUID): Future[Seq[DriverUUID]] = {
    val poolUUIDString = poolUUID.value.toString
    logger.debug(s"get drivers ready for pool $poolUUIDString")
    redisContext.redisClient
      .zrange[String](POOL_READY_KEY_PREFIX + poolUUIDString, 0, -1)
      .map(uuids => uuids.map(uuid => DriverUUID(UUID.fromString(uuid))))
  }
}
