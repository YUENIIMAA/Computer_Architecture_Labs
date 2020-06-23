import org.apache.spark.graphx.GraphLoader
import org.apache.spark.sql.SparkSession

object PageRank {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession
      .builder
      .master("local[*]")
      .appName("PageRank")
      .getOrCreate()
    val sc = spark.sparkContext

    /* load graph from customized file
     *
     * Lab1: use data/Graph.txt
     * Lab2: use data/Wikipedia_vote_network.txt
     * Lab3: use data/Google_web_graph.txt
     */
    val graph = GraphLoader.edgeListFile(sc, "data/Google_web_graph.txt")

    val ranks = graph.pageRank(0.0001).vertices

    println("[info]<--------PageRank Result------>")
    ranks.collect.foreach(println(_))

    spark.stop()
  }
}
