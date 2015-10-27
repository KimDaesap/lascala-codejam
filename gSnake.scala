import java.io.PrintWriter

import scala.collection.immutable.Stream.Empty

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
			// 입력 케이스의 수 T의 횟수만큼 입력 처리.
			(1 to inputLines.next().toInt).foreach(
				(num) => writer.println(s"Case #$num: ${process(inputLines)}"))
		}
		finally {
			writer.flush()
			writer.close()
		}
	}

	/*
		문제풀이.
		- 스테이지는 가로 r, 세로 c의 크기를 가진다.
		- 스테이지의 각 바깥면은 반대쪽 면으로 이어지는 무한 구조.
		 ( 3x3 스테이지일 경우 좌표 1,1 에서 좌측으로 이동하면 3,1이 된다.)
		- 사과는 (r + c)가 홀수가 되는 좌표에 놓여진다. (먹으면 사라짐)
		- Snake는 좌상단 (1,1) 좌표에서 우측을 바라본 상태에서 시작.
		- 1초가 지날 때 마다 뱀은 뱀이 향하고 있는 방향으로 1칸 이동한다.
		- 뱀이 사과를 먹으면 몸 길이가 1 증가한다.
		- 게임은 뱀의 머리가 몸통에 부딪치거나 10^9 만큼의 시간이 지나면 끝난다.
	 */

	case class Point(x: Int, y: Int)
	case class Snake(direction: String, body: List[Point])
	case class Command(second: Int, direction: String)

	def process(inputLines: Iterator[String]): String = {
		val Array(s, r, c) = inputLines.next().split(" ").map(_.toInt)
		val commands =  (1 to s).map(_ => {
			inputLines.next().split(" ") match { case Array(t, d) => Command(t.toInt, d) }
		}).toList

		// 초기화.
		val stage = Array.tabulate(r, c) ((x, y) => (x + y) % 2 != 0)
		val snake = Snake("R", List(Point(1, 1)))
		var gameTime = 0

		def move(c: Command, cs: List[Command]) = {

		}


		/* loop s <- 0 to 10^9

			command.second - s 만큼 현재 방향으로 전진.


		 */

		// Debug print.
		"\n\t" +
		  (for (row <- stage) yield row.mkString("{", ",", "}")).mkString("\n\t") +
		  "\n\t" + snake.toString +
		  "\n\t" + commands.mkString(", ")
	}

}
