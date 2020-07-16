import org.apache.spark.{SparkConf, SparkContext}

object SparkWordCount {

  def main(args: Array[String]) {

    val conf = new SparkConf().setAppName("wordCount").setMaster("local[*]")
    // Create a Scala Spark Context.
    val sc = new SparkContext(conf)
    // Load our input data.
    val input = sc.textFile("input.txt")
    // Split up into words.
    val words = input.flatMap(line => line.split(" "))
    // Transform into word and count.
    val counts = words.map(word => (word, 1)).reduceByKey{case (x, y) => x + y}
    // Save the word count back out to a text file, causing evaluation.
    counts.saveAsTextFile("output")
  }

}