lazy val root = (project in file("."))
  .settings(
    organization := "com.pecorarista",
    version := "0.0.0",
    scalaVersion := "2.11.7",
    name := "NLP100",
    libraryDependencies ++= Seq(
      "org.scalaz" %% "scalaz-core" % "7.1.4",
      "io.argonaut" %% "argonaut" % "6.1",
      "net.debasishg" %% "redisclient" % "3.0",
      "com.typesafe.scala-logging" %% "scala-logging" % "3.1.0",
      "ch.qos.logback" % "logback-classic" % "1.1.3"
    )
  )
