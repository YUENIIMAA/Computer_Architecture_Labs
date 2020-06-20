import com.google.common.primitives.UnsignedInteger
import org.apache.spark.graphx.{GraphLoader, VertexId}
import org.apache.spark.sql.SparkSession

object SSSP {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession
      .builder
      .master("local[*]")
      .appName("Single Source Shortest Path")
      .getOrCreate()
    val sc = spark.sparkContext

    /* load graph from customized file */
    val iptGraph = GraphLoader.edgeListFile(sc, "data/Graph.txt")

    /* confirm the correctness of imported graph */
    println("[info]<--------Edge Information------>")
    iptGraph.edges.collect.foreach(println(_))
    println("[info]<-------Vertex Information----->")
    iptGraph.vertices.collect.foreach(println(_))

    spark.stop()
  }
}
