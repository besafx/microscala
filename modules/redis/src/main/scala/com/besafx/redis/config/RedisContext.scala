package com.besafx.redis.config

import akka.actor.ActorSystem
import com.besafx.redis.error.UnInitializedRedisContext
import elmenus.infrastructure.db.cache.redis.RedisAdapter
import javax.inject.{Inject, Singleton}
import play.api.Configuration

import scala.concurrent.{ExecutionContext, Future}

@Singleton()
class RedisContext @Inject()(config: Configuration)(implicit val actorSystem: ActorSystem,
                                                    implicit val executionContext: ExecutionContext) {

  def RedisEnabled: Boolean = true

  lazy val redisConnection: Option[RedisAdapter] =
    if (RedisEnabled) {
      val wrapper = new RedisAdapter(config.get[String]("redis.host"), config.get[Int]("redis.port"))
      Some(wrapper)
    } else
      None

  def redisClient: RedisAdapter =
    redisConnection match {
      case Some(connection) => connection
      case None             => throw UnInitializedRedisContext("Redis connection not initialized")
    }

  def shutdownRedisClient(): Future[Boolean] =
    if (RedisEnabled)
      redisClient.quit
    else
      Future.successful(false)

}
