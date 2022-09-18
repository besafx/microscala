package com.besafx.database.checkpoint.data

import slick.ast.BaseTypedType
import slick.jdbc.JdbcType
import slick.jdbc.MySQLProfile.api._

sealed trait TripViolationTypeEnum

object TripViolationTypeEnum {

  case object FM_Prevention extends TripViolationTypeEnum
  case object FM_Detection  extends TripViolationTypeEnum
  case object LM_Prevention extends TripViolationTypeEnum
  case object LM_Detection  extends TripViolationTypeEnum

  def apply(name: String): TripViolationTypeEnum =
    name match {
      case "FM_PREVENTION" => FM_Prevention
      case "FM_DETECTION"  => FM_Detection
      case "LM_PREVENTION" => LM_Prevention
      case "LM_DETECTION"  => LM_Detection
    }

  def to(value: TripViolationTypeEnum): String =
    value match {
      case FM_Prevention => "FM_PREVENTION"
      case FM_Detection  => "FM_DETECTION"
      case LM_Prevention => "LM_PREVENTION"
      case LM_Detection  => "LM_DETECTION"
    }

  def isFM(value: TripViolationTypeEnum): Boolean =
    value == FM_Prevention || value == FM_Detection

  def isLM(value: TripViolationTypeEnum): Boolean =
    value == LM_Prevention || value == LM_Detection

  implicit val tripViolationTypeToStringConverter: JdbcType[TripViolationTypeEnum] with BaseTypedType[TripViolationTypeEnum] =
    MappedColumnType.base[TripViolationTypeEnum, String](TripViolationTypeEnum.to, TripViolationTypeEnum.apply)
}
