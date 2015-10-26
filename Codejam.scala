import java.io.PrintWriter

object Codejam {
	// Config.
	val inputName = "example"
	val inputFilePath = inputName + ".in"
	val outputFilePath = inputName + ".out"
	val isConsole = true

	// Main procedure.
	def main(args: Array[String]): Unit = {
		val inputLines = scala.io.Source.fromFile(inputFilePath).getLines()
		val writer = if (isConsole) new PrintWriter(Console.out)
			else new PrintWriter(outputFilePath)

		try {
			// 입력 케이스 수 T 만큼 입력 처리.
			(1 to inputLines.next().toInt).foreach(process(_, inputLines)(writer.println))
		}
		finally {
			writer.flush()
			writer.close()
		}
	}

	// Process input value.
	def process(num: Int, inputLines: Iterator[String])(lineOut: String => Unit) = {
		val result = new StringBuilder

		lineOut(s"Case #$num: $result")
	}

}
