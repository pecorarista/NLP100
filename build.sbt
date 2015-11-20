lazy val root = (project in file("."))
  .settings(
    organization := "com.pecorarista",
    version := "0.0.0",
    scalaVersion := "2.11.7",
    name := "NLP100",
    scalacOptions ++= Seq(
      "-Ywarn-unused-import",
      "-Xlint",
      "-deprecation"
    ),
    scalacOptions in (Compile, console) ~= (_ filterNot (_ == "-Ywarn-unused-import")),
    libraryDependencies ++= Seq(
      "org.scalaz"                 %% "scalaz-core"         % "7.1.4",
      "org.scala-lang.modules"     %% "scala-xml"           % "1.0.3",
      "net.debasishg"              %% "redisclient"         % "3.0",
      "com.typesafe.scala-logging" %% "scala-logging"       % "3.1.0",
      "ch.qos.logback"             %  "logback-classic"     % "1.1.3",
      "com.typesafe.play"          %  "play-json_2.11"      % "2.5.0-M1",
      "com.typesafe"               %  "config"              % "1.3.0",
      "org.reactivemongo"          %% "reactivemongo"       % "0.11.7",
      "org.reactivemongo"          %% "play2-reactivemongo" % "0.11.7.play24"
    )
  )

