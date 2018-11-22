package com.adaltas.ece.spark

import com.adaltas.ece.spark.model.{TaxiFare, TaxiRide}
import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.functions.col

/**
  * @author Cesar Berezowski
  */
class TranformersTest extends EceTestSuite {

  def rides: DataFrame = spark.read.json(getClass.getResource("/nycTaxiRidesSample.json").getFile)

  def parsedRides: DataFrame = spark.read.json(getClass.getResource("/expectedParsedRides.json").getFile)

  def filteredRides: DataFrame = spark.read.json(getClass.getResource("/expectedFilteredRides.json").getFile)

  def fares: DataFrame = spark.read.json(getClass.getResource("/nycTaxiFaresSample.json").getFile)

  def droppedColumnsFares: DataFrame = spark.read.json(getClass.getResource("/expectedDroppedColumnsFares.json").getFile)

  def joined: DataFrame = spark.read.json(getClass.getResource("/expectedJoined.json").getFile)

  "This test" should "do nothing" in {
    // TODO: experiment
  }

  "parseIsStartColumn" should "parse isStart column to BooleanType" in {
    Transformers.parseIsStartColumn(rides).collect() should contain theSameElementsAs parsedRides.collect()
  }

  "filtersStartRides" should "filter Rides to keep only starting" in {
    Transformers.filterStartRides(parsedRides).collect() should contain theSameElementsAs filteredRides.collect()
  }

  "dropColumns" should "remove columns from a dataframe" in {
    Transformers.dropColumns(
      fares,
      Seq(TaxiFare.PAYMENT_TYPE_COL_NAME, TaxiFare.TAXI_ID_COL_NAME)
    ).collect() should contain theSameElementsAs droppedColumnsFares.collect()
  }

  "joinsOnColumns" should "join two dataframe on one column" in {
    val df = Transformers.joinOnColumns(
      filteredRides,
      droppedColumnsFares,
      Seq(TaxiRide.DRIVER_ID_COL_NAME, TaxiRide.RIDE_ID_COL_NAME, TaxiRide.START_TIME_COL_NAME)
    )

    df
      .select(df.columns.sorted.map(col): _*)
      .collect() should contain theSameElementsAs joined.collect()
  }
}
