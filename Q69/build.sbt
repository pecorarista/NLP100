name := "NLP100-Q69"

version := "1.0"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(cache, ws)

resolvers += "Sonatype Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/"

libraryDependencies ++= Seq(
  "org.reactivemongo" %% "play2-reactivemongo" % "0.12.0-SNAPSHOT" changing(),
  "org.webjars" %% "webjars-play" % "2.4.0",
  "org.webjars" % "bootswatch-united" % "3.3.4+1",
  "org.webjars" % "font-awesome" % "4.5.0",
  "org.webjars" % "jquery" % "2.2.1"
)

routesGenerator := InjectedRoutesGenerator
