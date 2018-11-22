name := "spark-tp-batch"

// Project version
version := "0.1"

scalaVersion := "2.11.12"

// Add maven repository
resolvers ++= Seq(Opts.resolver.sonatypeReleases)

// Assembly settings
test in assembly := {}
assemblyOption in assembly := (assemblyOption in assembly).value.copy(includeScala = false)
mainClass in assembly := Some("com.adaltas.ece.spark.Main")

// Dependencies

val sparkVersion = "2.3.0"

val commonDependencies: Seq[ModuleID] = Seq(
  "commons-cli" % "commons-cli" % "1.4"
)

val sparkDependencies: Seq[ModuleID] = Seq(
  "org.apache.spark" %% "spark-core" % sparkVersion,
  "org.apache.spark" %% "spark-sql" % sparkVersion
).map(_ % Provided)

val testDependencies: Seq[ModuleID] = Seq(
  "org.scalatest" %% "scalatest" % "3.0.5"
).map(_ % Test)

libraryDependencies ++= commonDependencies ++
  sparkDependencies ++
  testDependencies
