package com.adaltas.ece.spark

import org.apache.spark.sql.types.StructType
import org.apache.spark.sql.{DataFrame, SparkSession}

/**
  * @author Cesar Berezowski
  */
object IoUtils {

  /**
    * Reads a CSV [[DataFrame]] from the given path
    */
  def readCsv(spark: SparkSession, path: String, schema: StructType): DataFrame = {
    ???
  }

  /**
    * Writes a [[DataFrame]] to given hive table
    */
  def writeToHive(df: DataFrame, database: String, table: String): Unit = {
    ???
  }
}
