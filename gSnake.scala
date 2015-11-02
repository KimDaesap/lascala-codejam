import java.io.PrintWriter
import scala.collection._

object Codejam {
	// Config.
	val inputName = "D-large-practice"
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
			(1 to inputLines.next().toInt).foreach(num => process(num, inputLines) (writer.println))
		}
		finally {
			writer.flush()
			writer.close()
		}
	}

	/*
		문제풀이.
		- game board는 가로 R, 세로 C의 크기를 가진다.
		  좌상단의 좌표는 (1, 1)이 되고 우하단의 좌표는 (R, C)가 된다.
		- 좌표 r + c가 홀수가 되는 cell에는 food가 놓여진다. ex: (1,2) (2,1) (2,3) (3, 2)
		- game board의 각 바깥면은 반대쪽 면으로 순환된다.
		  ( 3x3 스테이지일 경우 좌표 1,1 에서 좌측으로 이동하면 3,1이 된다.)
		- Snake는 좌상단 (1,1) 좌표에서 우측을 바라본 상태에서 시작.
		- 1초가 지날 때 마다 뱀은 머리가 향하고 있는 인접 cell로 이동한다.
		- 뱀의 머리가 먹이가 있는 cell로 이동하게 되면 음식을 먹고 (음식은 사라진다) body가 성장한다.
		  새로운 head는 음식이 있던 cell에 생성되고 머리는 snake의 두번째 cell이 된다.
		- player는 snake가 turn하는 action을 할 수 있다. 각 action Ai는 Ti와 Ti + 1 초 사이에 이루어진다.
		  action은 두가지가 가능한데 head를 기준으로 L 방향으로 90회전, 혹은 R 방향으로 90도 회전이다.
		- 게임은 뱀의 머리가 몸통에 부딪치거나 10^9 만큼의 시간이 지나면 끝난다.
	 */

	case class Cell(x: Int, y: Int)
	case class Action(time: Int, turn: String)
	case class Stage(rows: Int, columns: Int, snake: Snake) {
		val map = mutable.Map[Cell, Int]()

		for {
			x <- 0 until rows
			y <- 0 until columns
		} if ( (x + y) % 2 == 0) map += (Cell(x,y) -> Empty) else map += (Cell(x,y) -> Apple)

		snake.body.foreach(b => map(Cell(b.x, b.y)) = Body)
	}
	case class Snake(startDirection: (Int, Int), startBody: List[Cell]) {
		var direction = startDirection
		var body = startBody
	}

	val LimitTime = Math.pow(10, 9).toLong
	val Up = (0, -1); val Down = (0, 1)
	val Left = (-1, 0); val Right = (1, 0)
	val Empty = 0; val Apple = 1; val Head = 10; val Body = 11 ;val Tail = 12

	def process(num: Int, inputLines: Iterator[String])(lineOut: String => Unit): Unit = {
		// 입력 값 parsing.
		val Array(s, r, c) = inputLines.next().split(" ").map(_.toInt)
		val actions = mutable.Queue[Action]() ++ (1 to s).map(
			_ => inputLines.next().split(" ") match { case Array(t, d) => Action(t.toInt, d) } )

		// 게임 초기화.
		val snake = Snake(Right, List(Cell(0, 0)))
		val gameBoard = Stage(r, c, snake)
		var elapseTime = 0
		var isDead = false

		//---------------------------------------------------------------------------------------------//

		def facingToward(point: Cell): Unit = {
			val head = snake.body.head
			val tail = snake.body.last

			snake.body = (point :: snake.body).init
			gameBoard.map(tail) = Empty

			val newTail = snake.body.last
			gameBoard.map(point) = Head
			gameBoard.map(head) = Body
			gameBoard.map(newTail) = Tail
		}

		def grow(point: Cell) = {
			val head = snake.body.head
			val tail = snake.body.last
			snake.body = point :: snake.body
			gameBoard.map(point) = Head
			gameBoard.map(head) = Body
			gameBoard.map(tail) = Tail
		}

		def turnAction(t: String) = t match {
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

		def gameTick(time: Int) = {
			while (time > elapseTime && isDead == false) {
				elapseTime += 1

				// 이동 포인트 얻기.
				val fowardPoint = {
					val x = (snake.body.head.x + snake.direction._1) % r
					val y = (snake.body.head.y + snake.direction._2) % c
					Cell(if (x >= 0) x else r + x, if (y >= 0) y else c + y)
				}

				// 전진! 포인트에 사과 있으면 성장, 몸에 부딛히면 게임 오버.
				gameBoard.map(fowardPoint) match {
					case Apple => grow(fowardPoint)
					case Body => isDead = true
					case _ => facingToward(fowardPoint) // Empty or Tail.
				}
			}
		}

		//---------------------------------------------------------------------------------------------//

		var action: Action = null
		var isGrow = false

		while (actions.isEmpty == false && isDead == false) {
			action = actions.dequeue
			gameTick(action.time)
			turnAction(action.turn)
		}

		while (isGrow == false && isDead == false) {
			val oldLength = snake.body.length
			gameTick(elapseTime + oldLength)
			isGrow = (snake.body.length - oldLength) == 0
		}

		// 결과.
		lineOut(s"Case #$num: ${snake.body.length.toString}")
	}

}
