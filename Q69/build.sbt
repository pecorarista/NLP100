name := "NLP100-Q69"

version := "1.0"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(cache, ws)

resolvers += "Sonatype Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/"

libraryDependencies ++= Seq(
  "org.reactivemongo" %% "play2-reactivemongo" % "0.11.2.play24",
  "org.webjars" %% "webjars-play" % "2.4.0",
  "org.webjars" % "bootswatch-united" % "3.3.4+1",
  "org.webjars" % "font-awesome" % "4.5.0",
  // "org.webjars" % "html5shiv" % "3.7.0",
  // "org.webjars" % "respond" % "1.4.2",
  "org.webjars" % "jquery" % "2.2.1"
)

routesGenerator := InjectedRoutesGenerator

pipelineStages := Seq(rjs)