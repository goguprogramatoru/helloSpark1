import java.io.{File, PrintWriter}

import org.apache.spark.{SparkContext, SparkConf}

object Main {

	val STORY_URL = "http://textfiles.com/stories/3lpigs.txt"
	val STORY_FILE = "/opt/data/junk/sparkTraining/story.txt"

	def main (args: Array[String]) {
		this.downloadStory()
		this.sparkWordCount()
	}

	private def downloadStory(): Unit ={
		val pw = new PrintWriter(new File(STORY_FILE))
		pw.write(scala.io.Source.fromURL(STORY_URL).mkString)
		pw.close()
	}

	private def sparkWordCount():Unit = {
		val conf = new SparkConf().setAppName("Word count").setMaster("local[4]")	//local[4] = it will use spark as lib, with 4 cores
		val sc = new SparkContext(conf)
		val resultRdd = sc
			.textFile(STORY_FILE)									//instruct the DAG that it should read from this text file
				.map(line => line.replaceAll("[^A-Za-z0-9 ]"," ")) 	//textFile(..) will give you a stream of lines. Replace all non alphanumeric chars to space
					.flatMap(line => line.split(" "))				//split by words
						.filter(word => word!="")					//no need to count empty word
						.map(word => (word,1))						//make a touple (word,1). the word will be the key, and 1 will be the value
							.reduceByKey(_+_)						//sum up all the values (1) for each key (grouped)
								.sortBy(_._2,false)					//sort by value descending
									.foreach(println _)				//print each line from the result (grouped, summed, ordered)
		
	}
}
