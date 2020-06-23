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

    /* load graph from customized file
     *
     * Lab1: use data/Graph.txt
     * Lab2: use data/Wikipedia_vote_network.txt
     * Lab3: use data/Google_web_graph.txt
     */
    val iptGraph = GraphLoader.edgeListFile(sc, "data/Graph.txt")

    /* confirm the correctness of imported graph
     *
     * println("[info]<--------Edge Information------>")
     * iptGraph.edges.collect.foreach(println(_))
     * println("[info]<-------Vertex Information----->")
     * iptGraph.vertices.collect.foreach(println(_))
     */

    /* specify the ultimate source */
    val sourceId: VertexId = 4

    /* set distance to 0 if the vertex is the source and INF otherwise */
    val graph = iptGraph.mapVertices((id, _) => {
      if (id == sourceId) 0
      else Int.MaxValue
    })

    /* initialMsg: set every vertex's value to INF */
    val sssp = graph.pregel(Int.MaxValue)(
      /* vprog: receive INF from initialMsg and use min to skip the source */
      (id, dist, newDist) => math.min(dist, newDist),
      /* sendMsg: if src->self->dst is shorter, send the shorter distance to dst */
      triplet => {
        /* note that the iteration doesn't necessarily start from the source */
        /* thus we have to take care of overflow */
        if (triplet.srcAttr == Int.MaxValue) {
          Iterator.empty
        }
        /* case mentioned above */
        else if (triplet.srcAttr + triplet.attr < triplet.dstAttr) {
          Iterator((triplet.dstId, triplet.srcAttr + triplet.attr))
        }
        /* if src->self->dst is larger, ignore */
        else {
          Iterator.empty
        }
      },
      /* mergeMsg: if there are multiple path shorter than the current one, pick the shortest */
      (a,b) => math.min(a, b)
    )

    println("[info]<---------Shortest Path-------->")
    sssp.vertices.collect.foreach(println(_))

    spark.stop()
  }
}
