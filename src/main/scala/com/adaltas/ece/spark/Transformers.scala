package com.adaltas.ece.spark

import org.apache.spark.sql.DataFrame

/**
  * @author Cesar Berezowski
  */
object Transformers {
  /**
    * Parses content of [[com.adaltas.ece.spark.model.TaxiRide.IS_START_COL_NAME]] to get a [[Boolean]]
    * Original column is overwritten
    */
  def parseIsStartColumn(df: DataFrame): DataFrame = {
    df
  }

  /**
    * Filters a [[com.adaltas.ece.spark.model.TaxiFare]] [[DataFrame]] to get only starting rides using
    * [[com.adaltas.ece.spark.model.TaxiFare.RIDE_ID_COL_NAME]] column
    */
  def filterStartRides(df: DataFrame): DataFrame = {
    df
  }

  /**
    * Drops columns from [[DataFrame]]
    */
  def dropColumns(df: DataFrame, columns: Seq[String]): DataFrame = {
    df
  }

  /**
    * Joins the two [[DataFrame]] on given column names
    */
  def joinOnColumns(left: DataFrame, right: DataFrame, columns: Seq[String]): DataFrame = {
    left
  }
}
