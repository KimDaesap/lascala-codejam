import java.io.PrintWriter

/*
	Program D. Googlander
	https://code.google.com/codejam/contest/9214486/dashboard#s=p3
 */

object Googlander {

	// Config.
	val inputFilePath = "D-small-practice.in"
	val outputFilePath = "D-small-practice.out"
	val isConsole = false

	// Main procedure.
	def main(args: Array[String]): Unit = {
		val inputLines = scala.io.Source.fromFile(inputFilePath).getLines()
		val inputCount = inputLines.next.toInt
		val writer = if (isConsole) new PrintWriter(Console.out)
			else new PrintWriter(outputFilePath)

		try {
			for (num <- 1 to inputCount) {
				val result = process(inputLines.next())
				writer.println(s"Case #$num: $result")
			}
		} finally {
			writer.flush()
			writer.close()
		}
	}

	// Process input value.
	def process(input: String): String = {
		val Array(r, c) = input.split(" ").map(_.toInt)
		val perform = Array.ofDim[Boolean](r + 1, c + 1)
		var acc = 0

		def go(current: (Int, Int), direction: (Int, Int)): Boolean = {
			// 현재 위치의 유효성 검사.
			if ((0 <= current._1 && current._1 < r && 0 <= current._2 && current._2 < c) == false) {
				return false
			}

			// 지나온 경로인지 검사.
			if (perform(current._1)(current._2)) {
				return false
			}	

			// 경로에 현재 위치 등록.
			perform(current._1)(current._2) = true

			val rightDirection = direction match {
				case (0, 1) => (1, 0)
				case (1, 0) => (0, -1)
				case (0, -1) => (-1, 0)
				case (-1, 0) => (0, 1)
			}

			val isPosibleFowardtMove = go((current._1 + direction._1, current._2 + direction._2), direction)
			val isPosibleRightTurnMove = go((current._1 + rightDirection._1, current._2 + rightDirection._2), rightDirection)

			if (isPosibleFowardtMove == false && isPosibleRightTurnMove == false) {
				acc += 1
			}

			// 경로에 현재 위치 해제.
			perform(current._1)(current._2) = false

			// result
			return true
		}

		go((0, 0), (0, 1))
		acc.toString
	}

}
