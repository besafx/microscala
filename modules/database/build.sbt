import Dependencies._
import MavenResolvers.{elmenusDev, elmenusProduction, elmenusTest}

Settings.buildSettings
Settings.scalafmtSettings
scalaVersion in ThisBuild := "2.13.5"
name := "database"

//Maven Publishing
publishMavenStyle := true
publishArtifact in (Test, packageBin) := false
publishArtifact in (Test, packageDoc) := false
publishArtifact in (Test, packageSrc) := false
publishConfiguration := publishConfiguration.value.withOverwrite(true)
publishLocalConfiguration := publishLocalConfiguration.value.withOverwrite(true)
val BUILD_ENV = sys.env.getOrElse("BUILD_ENV", "PRODUCTION")
publishTo := {
  if (BUILD_ENV == "DEV")
    Some(elmenusDev)
  else if (BUILD_ENV == "TEST")
    Some(elmenusTest)
  else if (BUILD_ENV == "PRODUCTION")
    Some(elmenusProduction)
  else
    throw new IllegalStateException(
      s"BUILD_ENV enviroment variable not correct $BUILD_ENV"
    )
}

libraryDependencies ++= Seq(guice, scalaTestPlus, scalaMockito, playSlick, mysql, playSlickEvolutions, slickJodaMapper)
