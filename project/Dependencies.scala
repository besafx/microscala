import sbt._

object Dependencies {
  val scalaTestPlus       = "org.scalatestplus.play" %% "scalatestplus-play"     % "5.1.0"
  val akkaKafkaStream     = "com.typesafe.akka"      %% "akka-stream-kafka"      % "2.1.0"
  val akkaStream          = "com.typesafe.akka"      %% "akka-stream"            % "2.6.14"
  val mysql               = "mysql"                  % "mysql-connector-java"    % "8.0.15"
  val playSlick           = "com.typesafe.play"      %% "play-slick"             % "5.0.0"
  val playSlickEvolutions = "com.typesafe.play"      %% "play-slick-evolutions"  % "5.0.0"
  val playJson            = "com.typesafe.play"      %% "play-json"              % "2.9.2"
  val redisScala          = "com.github.etaty"       %% "rediscala"              % "1.9.0"
  val log4J               = "org.slf4j"              % "slf4j-log4j12"           % "1.2" % Test
  val jodaTime            = "joda-time"              % "joda-time"               % "2.10.10"
  val cats                = "org.typelevel"          %% "cats-core"              % "2.1.1"
  val scalaMockito        = "org.scalatestplus"      %% "mockito-3-4"            % "3.2.9.0"
  val sharedKernel        = "com.elmenus"            %% "shared-kernel-common"   % "0.0.4"
  val hashIds             = "com.github.ancane"      %% "hashids-scala"          % "1.4"
  val phantom             = "com.outworkers"         %% "phantom-dsl"            % "2.59.0"
  val distanceMatrix      = "com.elmenus"            % "elmenus-distance-matrix" % "0.0.6"
  val slickJodaMapper     = "com.github.tototoshi"     %% "slick-joda-mapper"       % "2.4.2"

}
