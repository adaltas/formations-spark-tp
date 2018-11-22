# Spark Batch Exercise

The objective of this exercise is to process two given CSV datasets to extract meaningful data and persist it to Hive.

Throughout the different steps you will learn how: 

- a Spark application is created
- to read files with Spark
- a DataFrame is organized and manipulated
- to persist data to Hive
- to test Spark functions and components

## Prerequisites

You can do everything through [IntelliJ IDEA](https://jetbrains.com/idea) or you will need the following installed
on your machine: 

- [SBT](https://scala-sbt.org) 
- [Scala](https://scala-lang.org)

## The source code
 
The code base is composed of multiple parts:
 
- `build.sbt` contains the build definition
- `src/main` contains the main code
- `src/test` contains the unit tests
 
To package the project:

- from cmd `sbt assembly`
- from IntelliJ, setup an assembly task (see [here](https://stackoverflow.com/questions/25109981/how-to-run-sbt-assembly-tasks-from-within-intellij-idea))
 
To run the unit tests:

- from cmd `sbt test`
- from IntelliJ
  * setup a test task (see [here](https://stackoverflow.com/questions/25109981/how-to-run-sbt-assembly-tasks-from-within-intellij-idea))
  * right click on the test file to run and select `Run 'MyTestClass'`

## Part 1: Implementation

Using the `taxiRides` and `taxiFares` datasets available in `hdfs://...`, we want to: 
- load the datasets
- apply a schema and clean them
- join each available ride to the corresponding fare
- persist them to Hive

Open the file `com.adaltas.ece.spark.Main`, it contains the above main steps applied using Spark.

The functions defined in the objects `IoUtils` and `Transformers` are yours to implement. Implementation of `Transformers` 
functions are tested using the test class `com.adaltas.ece.spark.TransformersTest`. You can experiment before implementing 
the `IoUtils` functions using the spark-shell. You'll run it with: 

```bash
export SPARK_MAJOR_VERSION=2 && spark-shell
```

### Reading and writing

To be able to read something with Spark you will need a `DataFrameReader` that you can get with `spark.read`.

The reader exposes multiple functions that all return another `DataFrameReader` so you can chain the function calls: 

- `.option()` allows you to specify an option (such as a delimiter)
- `.schema()` allows you to specify a schema if the data you read does not provide it
- `.format()` allows you to specify a file format (csv, json, parquet, orc, ...)
- `.load()` returns a `DataFrame` containing the data

Since the schema is not specified in the CSV data, you'll need schemas defined in the `TaxiFare` and `TaxiRide` object in 
`com.adaltas.ece.spark.model`.

The two functions `.format()` and `.load()` can be replaced by other function calls doing the same thing such as `.csv()`, 
`.json()`, ...

Once you have a `DataFrame`, you can persist it using a `DataFrameWriter` that you can get with `[dataframe].write`. It 
exposes the same type of functions as the reader, and a `saveAsTable()` taking the target table name that writes to Hive. 

More on that [here](https://people.apache.org/~pwendell/spark-nightly/spark-master-docs/latest/sql-programming-guide.html#generic-loadsave-functions).

### Tranformers

Go through [the documentation](https://people.apache.org/~pwendell/spark-nightly/spark-master-docs/latest/sql-programming-guide.html) 
or [google](https://google.com) and [StackOverflow](https://stackoverflow.com). For the parsing of data, use `withColumn` with the same 
name as the column parsed to overwrite it. Check the functions' comments and usage to understand what to implement.

## Part 2: Run on the cluster

Once you have your functions implemented:

- Package your job (see source code section above) and get your jar in `target/scala-2.11/spark-tp-batch-assembly-0.1.jar`
- Upload your jar on the cluster 
- Run your job with the following command: 

```bash
spark-submit \
  --master yarn 
  --class com.adaltas.ece.spark.Main \
  spark-tp-batch-assembly-0.1.jar \
  --rides hdfs://[path/to/rides.gz] \
  --fares hdfs://[path/to/fares.gz] \
  --database [database] \
  --table [table-name] 
```

You should see the logs of your job displaying and finally when your job finishes you'll get back to the command line.

Run hive and you go check your results !
