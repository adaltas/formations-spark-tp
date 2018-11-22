package com.adaltas.ece.spark.model

import org.apache.spark.sql.types._

/**
  * Spark schema for TaxiFares.gz source
  *
  * @author Cesar Berezowski
  */
object TaxiFare {
  val RIDE_ID_COL_NAME: String = "rideId"
  val TAXI_ID_COL_NAME: String = "taxiId"
  val DRIVER_ID_COL_NAME: String = "driverId"
  val START_TIME_COL_NAME: String = "startTime"
  val PAYMENT_TYPE_COL_NAME: String = "paymentType"
  val TIP_COL_NAME: String = "tip"
  val TOLLS_COL_NAME: String = "tolls"
  val TOTAL_FARE_COL_NAME: String = "totalFare"

  val schema: StructType = StructType(Seq(
    StructField("rideId", LongType, nullable = true),
    StructField("taxiId", LongType, nullable = true),
    StructField("driverId", LongType, nullable = true),
    StructField("startTime", TimestampType, nullable = true),
    StructField("paymentType", StringType, nullable = true),
    StructField("tip", FloatType, nullable = true),
    StructField("tolls", FloatType, nullable = true),
    StructField("totalFare", FloatType, nullable = true)
  ))

}
