name := """label-service"""
organization := "org.hyperdiary"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "3.3.1"

libraryDependencies ++= Seq(
  guice,
  "org.eclipse.rdf4j" % "rdf4j-repository-http" % "4.3.8",
  "io.lemonlabs" %% "scala-uri" % "4.0.3",
  "org.apache.commons" % "commons-rdf-rdf4j" % "0.5.0",
  "com.inrupt.client" % "inrupt-client-solid" % "1.1.0",
  "com.inrupt.client" % "inrupt-client-core" % "1.1.0",
  "com.inrupt.client" % "inrupt-client-httpclient" % "1.1.0",
  "com.inrupt.client" % "inrupt-client-jackson" % "1.1.0",
  "com.inrupt.client" % "inrupt-client-openid" % "1.1.0",
  "com.inrupt.client" % "inrupt-client-rdf4j" % "1.1.0",
  "com.inrupt.client" % "inrupt-client-webid" % "1.1.0",
  "org.scalatestplus.play" %% "scalatestplus-play" % "7.0.0" % Test
)
dependencyOverrides ++= Seq(
  "com.fasterxml.jackson.core" % "jackson-annotations" % "2.14.3",
  "com.fasterxml.jackson.core" % "jackson-core" % "2.14.3",
  "com.fasterxml.jackson.core" % "jackson-databind" % "2.14.3"
)


// Adds additional packages into Twirl
//TwirlKeys.templateImports += "org.hyperdiary.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "org.hyperdiary.binders._"
