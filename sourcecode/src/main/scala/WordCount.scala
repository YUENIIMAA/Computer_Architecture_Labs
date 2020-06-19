import org.apache.spark.sql.SparkSession

/*
 * This is a demo app to check whether the lab project is initiated correctly,
 * since I failed so many times on Windows,
 * I have to make sure nothing went wrong before continue
 */
object WordCount {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession
      .builder
      .master("local[*]")
      .appName("Word Count")
      .getOrCreate()

    val lines = spark.sparkContext.parallelize(
      Seq("This is a demo app to check whether",
        "the lab project is initiated correctly",
        "if nothing went wrong then it's time to continue"))

    val counts = lines
      .flatMap(line => line.split(" "))
      .map(word => (word, 1))
      .reduceByKey(_ + _)

    counts.foreach(println)

    spark.stop()
  }
}