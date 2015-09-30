import java.io.PrintWriter

object Codejam {

	// Config.
	val inputFilePath = "example.in"
	val outputFilePath = "example.out"
	val isConsole = true

	// Main procedure.
	def main(args: Array[String]): Unit = {
		val inputLines = scala.io.Source.fromFile(inputFilePath).getLines()
		val inputCount = inputLines.next.toInt
		val writer = if (isConsole) new PrintWriter(Console.out)
			else new PrintWriter(outputFilePath)

		try {
			for (num <- 1 to inputCount) {
				val result = process(inputLines.next())

				writer.println(s"Case#$num: $result")
			}
		} finally {
			writer.flush()
			writer.close()
		}
	}

	// Process input value.
	def process(input: String): String = {
		val result = input

		result
	}

}
