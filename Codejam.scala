import java.io.PrintWriter

object Codejam {

	// Config.
	val inputFilePath = "example.in"
	val outputFilePath = "example.out"
	val isConsole = true

	// Main procedure.
	def main(args: Array[String]): Unit = {
		val inputLines = scala.io.Source.fromFile(inputFilePath).getLines()
		val writer = if (isConsole) new PrintWriter(Console.out)
		else new PrintWriter(outputFilePath)

		try {
			process(inputLines)(writer.println)
		} finally {
			writer.flush()
			writer.close()
		}
	}

	// Process input value.
	def process(inputLines: Iterator[String])(lineOut: String => Unit) = {
		val inputCount = inputLines.next.toInt

		for (num <- 1 to inputCount) {
			val data = inputLines.next()
			val result = data

			lineOut(s"Case#$num: $result")
        }
    }

}
