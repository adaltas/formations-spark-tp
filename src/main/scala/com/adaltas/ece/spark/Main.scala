package com.adaltas.ece.spark

import com.adaltas.ece.spark.model.{JobArguments, TaxiFare, TaxiRide}
import org.apache.spark.sql.{DataFrame, SparkSession}

/**
  * @author Cesar Berezowski
  */
object Main {
  def main(args: Array[String]): Unit = {
    val arguments: JobArguments = JobArguments(args)

    val spark: SparkSession = SparkSession.builder()
      .appName("spark-tp-batch-XXX")
      .getOrCreate()

    val ridesDf: DataFrame = IoUtils.readCsv(spark, arguments.ridesPath, TaxiRide.schema)
    val faresDf: DataFrame = IoUtils.readCsv(spark, arguments.faresPath, TaxiFare.schema)

    val parsedRidesDf: DataFrame = Transformers.parseIsStartColumn(ridesDf)
    val filteredRidesDf: DataFrame = Transformers.filterStartRides(parsedRidesDf)

    val noPaymentTypeFaresDf: DataFrame = Transformers.dropColumns(
      faresDf,
      Seq(TaxiFare.PAYMENT_TYPE_COL_NAME, TaxiRide.TAXI_ID_COL_NAME)
    )

    val resultDf: DataFrame = Transformers.joinOnColumns(
      filteredRidesDf,
      noPaymentTypeFaresDf,
      Seq(TaxiRide.DRIVER_ID_COL_NAME, TaxiRide.RIDE_ID_COL_NAME, TaxiRide.START_TIME_COL_NAME)
    )

    IoUtils.writeToHive(resultDf, arguments.targetDb, arguments.targetTable)

    spark.stop()
  }
}
