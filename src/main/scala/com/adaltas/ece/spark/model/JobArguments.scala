package com.adaltas.ece.spark.model

import org.apache.commons.cli._

/**
  * Holds the job's arguments
  *
  * @author Cesar Berezowski
  */
case class JobArguments(ridesPath: String, faresPath: String, targetDb: String, targetTable: String)

object JobArguments {
  /**
    * Validates job's arguments presence and parses them to a [[JobArguments]] object
    */
  def apply(args: Array[String]): JobArguments = {
    val opts: Options = new Options()
    opts.addOption("rides", true, "Path to TaxiRides dataset")
    opts.addOption("fares", true, "Path to TaxiFares dataset")
    opts.addOption("db", "database", true, "Target Hive database, default = default")
    opts.addOption("table", true, "Target Hive table")

    val parser: CommandLineParser = new BasicParser
    val cmd: CommandLine = parser.parse(opts, args)

    Seq("rides", "fares", "table").foreach(o => if (!cmd.hasOption(o)) {
      throw new MissingArgumentException(s"Argument --$o is missing")
    })

    JobArguments(
      cmd.getOptionValue("rides"),
      cmd.getOptionValue("fares"),
      cmd.getOptionValue("db", "default"),
      cmd.getOptionValue("table")
    )
  }
}