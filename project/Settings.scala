import MavenResolvers._
import com.lucidchart.sbt.scalafmt.ScalafmtCorePlugin.autoImport._
import sbt.Keys._

// Build Settings shared with all sub-projects
object Settings {
  lazy val buildSettings = Seq(
    scalaVersion := "2.13.5",
    organization := "com.besafx",
    organizationName := "besafx",
    javacOptions ++= Seq("-source", "8", "-target", "8", "-Xlint"),
    scalacOptions ++= compilerOptions,
    resolvers ++= elmenusResolver(sys.env.getOrElse("BUILD_ENV", "PRODUCTION"))
  )
  lazy val compilerOptions = Seq(
    "-deprecation", // Emit warning and location for usages of deprecated APIs.
    "-encoding",
    "utf-8", // Specify character encoding used by source files.
    "-explaintypes", // Explain type errors in more detail.
    "-feature", // Emit warning and location for usages of features that should be imported explicitly.
    "-language:existentials", // Existential types (besides wildcard types) can be written and inferred
    "-language:experimental.macros", // Allow macro definition (besides implementation and application)
    "-language:higherKinds", // Allow higher-kinded types
    "-language:implicitConversions", // Allow definition of implicit functions called views
    "-unchecked" // Enable additional warnings where generated code depends on assumptions.
  )
  lazy val scalafmtSettings =
    Seq(
      scalafmtOnCompile := true,
      scalafmtTestOnCompile := true,
      scalafmtVersion := "1.2.0"
    )
}
