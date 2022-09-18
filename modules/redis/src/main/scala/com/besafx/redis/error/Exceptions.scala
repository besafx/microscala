package com.besafx.redis.error

case class UnInitializedRedisContext(msg: String) extends Exception(msg)
case class RedisProcessException(msg: String)     extends Exception(msg)
