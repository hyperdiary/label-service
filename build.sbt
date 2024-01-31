name := """label-service"""
organization := "org.hyperdiary"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "3.3.1"

libraryDependencies ++= Seq(
  guice,
  "org.eclipse.rdf4j" % "rdf4j-repository-http" % "4.3.9",
  "io.lemonlabs" %% "scala-uri" % "4.0.3",
  "com.inrupt.client" % "inrupt-client-solid" % "1.1.0",
  "org.scalatestplus.play" %% "scalatestplus-play" % "7.0.0" % Test
)

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "org.hyperdiary.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "org.hyperdiary.binders._"
