package com.besafx.database

import com.besafx.database.checkpoint.data.TripViolationCheckpointSlickRepository
import com.besafx.database.checkpoint.repository.TripViolationCheckpointRepository
import play.api.inject.{Binding, Module}
import play.api.{Configuration, Environment}

class BaseModule extends Module {

  override def bindings(environment: Environment, configuration: Configuration): Seq[Binding[_]] =
    Seq(
      bind[TripViolationCheckpointRepository].to[TripViolationCheckpointSlickRepository].eagerly()
    )
}
