package com.adaltas.ece.spark

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.scalatest.{BeforeAndAfterAll, FlatSpec, Matchers}

/**
  * Creates a [[SparkSession]] and adds scalatest interfaces
  *
  * @author Cesar Berezowski
  */
trait EceTestSuite extends FlatSpec with Matchers with BeforeAndAfterAll {
  implicit var spark: SparkSession = _

  def name: String = s"spark-batch-tp-${getClass.getSimpleName}"

  def master: String = "local[*]"

  def configs: Map[String, String] = Map(
    "spark.ui.enabled" -> "false",
    "spark.sql.warehouse.dir" -> "/tmp/spark-warehouse"
  )

  override def beforeAll(): Unit = {
    val sc: SparkConf = new SparkConf()
      .setAppName(name)
      .setMaster(master)
      .setAll(configs)

    spark = SparkSession
      .builder()
      .config(sc)
      .getOrCreate()
  }

  override def afterAll(): Unit = {
    spark.stop()
  }
}
