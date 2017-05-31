name := """water-admin"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  jdbc,
  cache,
  ws,
  "com.typesafe.play" %% "anorm" % "2.5.1",
  "org.scalatestplus.play" %% "scalatestplus-play" % "1.5.1" % Test
)