import com.typesafe.startscript.StartScriptPlugin

seq(StartScriptPlugin.startScriptForClassesSettings: _*)

name := "hello"

version := "1.0"

scalaVersion := "2.9.2"

resolvers += "twitter-repo" at "http://maven.twttr.com"

libraryDependencies ++= Seq("com.twitter" % "finagle-core" % "1.9.0",
                            "com.twitter" % "finagle-http" % "1.9.0",
                            "org.scala-tools.testing" % "specs_2.9.1" % "1.6.9",
                            "org.mockito" % "mockito-all" % "1.9.0")
