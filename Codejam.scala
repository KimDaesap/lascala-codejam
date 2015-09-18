import java.io.PrintWriter

object Codejam {
  val inputFilePath = "example.in"
  val outputFilePath = "example.out"
  val isConsole = true

  // Main procedure.
  def main(args: Array[String]): Unit = {
    val inputLines = scala.io.Source.fromFile(inputFilePath).getLines()
    val inputCount = inputLines.next.toInt
    val writer = if (isConsole) new PrintWriter(scala.Console.out)
      else new PrintWriter(outputFilePath)

    try {
      process(inputCount, inputLines)(writer.println)
    } finally {
      writer.flush()
      writer.close()
    }
  }

  // Process input value.
  def process(inputCount: Int, inputLines: Iterator[String])(lineOut: String => Unit) = {
    for (num <- 1 to inputCount) {
      val count = inputLines.next()
      val value = inputLines.next()
      val result = value.grouped(8).map(x => {(0 /: x)((acc, x) => {acc << 1 | ~(x >> 1) & 1})}.toChar).mkString
      lineOut(s"Case #$num: $result")
    }
  }

}
