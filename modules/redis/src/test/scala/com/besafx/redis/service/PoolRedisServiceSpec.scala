package com.besafx.redis.service

import java.util.UUID

import com.besafx.redis.model.{DriverUUID, PoolDeliveryDuration, PoolDeliveryDurationUUID, PoolUUID}
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.time.{Seconds, Span}
import org.scalatestplus.mockito.MockitoSugar
import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneAppPerSuite
import play.api.Logger

class PoolRedisServiceSpec extends PlaySpec with GuiceOneAppPerSuite with MockitoSugar with ScalaFutures {

  val logger: Logger = Logger(classOf[PoolRedisServiceSpec])

  val service: PoolRedisService                          = app.injector.instanceOf[PoolRedisService]
  val poolUUID: PoolUUID                                 = PoolUUID(UUID.randomUUID())
  val driverUUID: DriverUUID                             = DriverUUID(UUID.randomUUID())
  val poolDeliveryDurationUUID: PoolDeliveryDurationUUID = PoolDeliveryDurationUUID.randomUUID()
  val poolDeliveryDuration: PoolDeliveryDuration = PoolDeliveryDuration(
    poolDeliveryDurationUUID,
    poolUUID,
    20,
    30
  )

  "Random unit test" must {
    "will print some logs" in {

      service must not be null

      whenReady(service.setPoolDurations(Seq(poolDeliveryDuration)), timeout(Span(50, Seconds)))(
        res => logger.info(s"Pool Durations for Pool: ${poolUUID.value.toString}, $res"))

      whenReady(service.getPoolDurations(PoolUUID(UUID.fromString("a1e26883-5e6c-42f4-a051-4d8a0a242906"))), timeout(Span(50, Seconds)))(
        res => logger.info(s"Pool Durations for Pool: ${poolUUID.value.toString}, $res"))

    }
  }

}
