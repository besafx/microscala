package com.besafx.cassandra.database

case class InvalidEnum(msg: String)                   extends RuntimeException(msg)
case class LockedException(msg: String)               extends RuntimeException(msg)
case class UnInitializedRedisContext(msg: String)     extends Exception(msg)
case class UnInitializedCassandraContext(msg: String) extends Exception(msg)
case class RedisProcessException(msg: String)         extends Exception(msg)
case class ParsingError(msg: String)                  extends Exception(msg)
case class InvalidState(msg: String)                  extends Exception(msg)
case class NotFoundException(msg: String)             extends Exception(msg)
