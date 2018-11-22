package com.adaltas.ece.spark.model

import org.apache.spark.sql.types._

/**
  * Spark schema for TaxiRides.gz source
  *
  * @author Cesar Berezowski
  */
object TaxiRide {
  val RIDE_ID_COL_NAME: String = "rideId"
  val IS_START_COL_NAME: String = "isStart"
  val START_TIME_COL_NAME: String = "startTime"
  val END_TIME_COL_NAME: String = "endTime"
  val START_LON_COL_NAME: String = "startLon"
  val START_LAT_COL_NAME: String = "startLat"
  val END_LON_COL_NAME: String = "endLon"
  val END_LAT_COL_NAME: String = "endLat"
  val PASSENGER_CNT_COL_NAME: String = "passengerCnt"
  val TAXI_ID_COL_NAME: String = "taxiId"
  val DRIVER_ID_COL_NAME: String = "driverId"

  val schema: StructType = StructType(Seq(
    StructField(RIDE_ID_COL_NAME, LongType, nullable = true),
    StructField(IS_START_COL_NAME, StringType, nullable = true),
    StructField(START_TIME_COL_NAME, TimestampType, nullable = true),
    StructField(END_TIME_COL_NAME, TimestampType, nullable = true),
    StructField(START_LON_COL_NAME, FloatType, nullable = true),
    StructField(START_LAT_COL_NAME, FloatType, nullable = true),
    StructField(END_LON_COL_NAME, FloatType, nullable = true),
    StructField(END_LAT_COL_NAME, FloatType, nullable = true),
    StructField(PASSENGER_CNT_COL_NAME, ShortType, nullable = true),
    StructField(TAXI_ID_COL_NAME, LongType, nullable = true),
    StructField(DRIVER_ID_COL_NAME, LongType, nullable = true)
  ))
}
