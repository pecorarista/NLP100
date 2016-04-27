name := "NLP100"

version := "0.0.0"

lazy val root = (project in file("."))

scalaVersion := "2.11.7"

scalacOptions ++= Seq("-deprecation", "-unchecked", "-Xlint", "-Ywarn-unused-import")

scalacOptions in (Compile, console) ~= (_ filterNot (_ == "-Ywarn-unused-import"))

resolvers ++= Seq(
  "Sonatype Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/",
  "Sonatype Releases" at "https://oss.sonatype.org/content/repositories/releases/"
)

libraryDependencies ++= Seq(
  "ch.qos.logback"             %  "logback-classic"         % "1.1.3",
  "com.typesafe.scala-logging" %% "scala-logging"           % "3.1.0",
  "com.typesafe.play"          %  "play-json_2.11"          % "2.5.0-M1",
  "com.typesafe"               %  "config"                  % "1.3.0",
  "net.debasishg"              %% "redisclient"             % "3.0",
  "org.reactivemongo"          %% "reactivemongo"           % "0.11.7",
  "org.reactivemongo"          %% "play2-reactivemongo"     % "0.11.7.play24",
  "org.scalaz"                 %% "scalaz-core"             % "7.1.4",
  "org.apache.lucene"          %  "lucene-core"             % "5.3.1",
  "org.apache.lucene"          %  "lucene-analyzers-common" % "5.3.1",
  "org.scala-lang.modules"     %% "scala-xml"               % "1.0.3"
)
