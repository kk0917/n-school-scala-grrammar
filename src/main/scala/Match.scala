object Match {
  def intToName(num: Int): String = {
    num match {
      case 1 => "one"
      case 2 => "two"
      case _ => "other"
    }
  }

  def message(message: String): String = {
    message match {
      case "good" | "bad" => "game"
    }
  }

  def patternMatch(): Unit = {
    val seq = Seq("A", "B", "C", "D", "E")

    seq match {
      case Seq("A", b, c, d, e) if b != "B" =>
        println("b = " + b)
        println("c = " + c)
        println("d = " + d)
        println("e = " + e)
      case _ =>
        println("nothing")
    }
  }

  def patternMatchNest(): Unit = {
    val seq = Seq(Seq("B"), Seq("B", "C", "D", "E"))

    seq match {
      case Seq(a@Seq("A"), x) =>
        println(a)
        println(x)
      case _ => println("nothing")
    }
  }

  def patternMatchType(): Unit = {
    val obj: AnyRef = "String Literal"

    obj match {
      case v: java.lang.Integer => println("Integer!")
      case v: String => println(s"String! length is ${v.length}")
    }
  }

  def patternMatchGenerics(): Unit = {
    val obj: AnyRef = Seq("a", "b", "c")
    obj match {
      case v: Seq[_] => println("Seq[_]")
      case _ => println("nothing")
    }
  }

  // beginner's chalenge!
  def printRandomChars(): Unit = {
    for (i <- 1 to 1000) {
      val chars: Seq[Char] = new scala.util.Random(new java.security.SecureRandom()).alphanumeric.take(5).toList

      val result = chars match {
        case Seq(a, b, c, d, _) => Seq(a, b, c, d, a)
      }

      println(result)
    }
  }

  // intermediate's chalenge!
  def last(seq: Seq[Int]): Int = {
    seq match {
      case Seq(x) => return x
      case x::xs => last(xs)
    }
  }

  // Senior's chalenge
  def reverse(seq: Seq[Int]): Seq[Int] = {
    seq match {
      case Seq(x) => Seq(x)
      case x::xs => reverse(xs) :+ x
    }
  }
}
