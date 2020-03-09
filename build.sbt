name := "scala-samples"
version := "0.1"
scalaVersion := "2.11.12"
val mockitoVersion = "1.10.19"

libraryDependencies ++= Seq(
  "org.scalacheck" %% "scalacheck" % "1.14.0" % Test,
  "org.scalatest" %% "scalatest" % "3.0.5" % Test, // ApacheV2
  "com.novocode" % "junit-interface" % "0.11" % Test, // BSD-style
  "junit" % "junit" % "4.12" % Test, // Eclipse Public License 1.0
  "org.scalatestplus.play" %% "scalatestplus-play" % "3.1.2" % Test,
  "org.mockito" % "mockito-all" % mockitoVersion % Test
)

libraryDependencies += "com.typesafe.akka" %% "akka-actor" % "2.5.26"
libraryDependencies += "com.typesafe.akka" %% "akka-http"   % "10.1.11"
libraryDependencies += "com.typesafe.akka" %% "akka-stream" % "2.5.26"
