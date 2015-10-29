import java.io.PrintWriter

import scala.collection.mutable.Queue

object Codejam {
	// Config.
	val inputName = "D-small-practice"
	val inputFilePath = inputName + ".in"
	val outputFilePath = inputName + ".out"
	val isConsole = false

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
	case class Command(second: Int, turn: String)
	case class Stage(rows: Int, columns: Int, snake: Snake) {
		val array = Array.tabulate(rows, columns) ((x, y) => {
			if ((x + y) % 2 == 0) Empty else Apple
		})

		snake.body.foreach(b => array(b.x)(b.y) = Body)
	}
	case class Snake(startDirection: (Int, Int), startBody: List[Point]) {
		var direction = startDirection
		var body = startBody
	}

	val LimitTime = Math.pow(10, 9).toLong
	val Up = (0, -1); val Down = (0, 1)
	val Left = (-1, 0); val Right = (1, 0)
	val Empty = 0; val Apple = 1; val Body = 3

	def process(inputLines: Iterator[String]): String = {
		// 입력 값.
		val Array(s, r, c) = inputLines.next().split(" ").map(_.toInt)
		val commands = Queue[Command]() ++
			(1 to s).map(_ => {inputLines.next().split(" ") match {case Array(t, d) => Command(t.toInt, d)}})

		// 게임 초기화.
		val snake = Snake(Right, List(Point(0, 0)))
		val stage = Stage(r, c, snake)
		var elapseTime = 0
		var command = commands.dequeue
		var isGameOver = false

		def foward(point: Point): Unit = {
			val tail = snake.body.last
			snake.body = (point :: snake.body).init
			stage.array(tail.x)(tail.y) = Empty
			stage.array(point.x)(point.y) = Body
		}

		def turn(t: String) = t match {
			case "L" => snake.direction = snake.direction match {
				case Right => Up; case Up => Left
				case Left => Down; case Down => Right
			}
			case "R" => snake.direction = snake.direction match {
				case Right => Down; case Down => Left
				case Left => Up; case Up => Right
			}
			case "N" => Unit
		}

		def grow() = snake.body = snake.body :+ snake.body.last

		do {
			elapseTime += 1

			// 이동 포인트 얻기.
			val point = {
				val x = (snake.body.head.x + snake.direction._1) % r
				val y = (snake.body.head.y + snake.direction._2) % c
				Point(if(x >= 0) x else r + x, if (y >= 0) y else  c + y)
			}

			// 전진! 포인트에 사과 있으면 성장, 몸에 부딛히면 게임 오버.
			stage.array(point.x)(point.y) match {
				case Empty => foward(point)
				case Apple => grow; foward(point)
				case Body => isGameOver = true
			}

			// 명령을 내릴 시간이 됬다면~
			if (command != null && command.second == elapseTime) {
				// 뱀의 진행 방향을 변경.
				turn(command.turn)
				// 다음 명령 가져오기.
				command = commands match {
					case cs if (cs.isEmpty == false) => cs.dequeue;
					case _ => null
				}
			}

			// 시간 만료 검사.
			if (elapseTime >= LimitTime) isGameOver = true

		} while(isGameOver == false)

		// 결과.
		return snake.body.length.toString
	}

}
