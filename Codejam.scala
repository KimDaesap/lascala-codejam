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
			(1 to inputLines.next().toInt).foreach(
				(num) => writer.println(s"Case #$num: ${process(num, inputLines)}"))
		}
		finally {
			writer.flush()
			writer.close()
		}
	}

	/*
		문제풀이.
	 */

	case class Command(t: Int, d: String)

	// Process input value.
	def process(num: Int, inputLines: Iterator[String]): String = {
		val result = new StringBuilder
		result.toString
	}

}
