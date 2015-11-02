import java.io.PrintWriter

/*
	Problem A. Travel
	https://code.google.com/codejam/contest/10214486/dashboard
 */

object Travel {
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

	/* 문제 설명.
		- N개의 도시, M개의 도로가 있다.
		- 이동에 소요되는 시간은 24시간 다름.
		- 출발점과 도착점을 정했을 때 시간을 구한다?
		- Cost[t] (0 ≤ t ≤ 23)
		- 도로는 양방향 이동 가능

		* Input
		- N M K (도시 수, 도로 수(도시 사이의 길), 질문 수)
		- 2M 어느 도시에서 어느 도시로 가는 도로인가
		- 각 시각 별로 걸리는 시간(Cost)
		- S D 질문: S 도시에서 D 도시로 가는데 가장 적게갈 수 있는 비용은?

		* Output
		- 각 질문에 대해 걸리는 최단 시간
	 */

	// Process input value.
	def process(num: Int, inputLines: Iterator[String]): String = {
		val result = new StringBuilder
		result.toString
	}

}
