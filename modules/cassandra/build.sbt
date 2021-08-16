import Dependencies._

Settings.buildSettings
ThisBuild / version := "0.1"
PlayKeys.playDefaultPort := 8000

libraryDependencies ++= Seq(
  guice,
  scalaTestPlus,
  scalaMockito,
  playJson,
  log4J,
  jodaTime,
  cats,
  playSlick,
  phantom
)
