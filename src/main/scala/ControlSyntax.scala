object ControlSyntax {
  def bar(): String = {
    "bar"
  }

  def printOver18(age: Int) = {
    val message = if (age < 18) {
      "18歳未満です"
    } else {
      "18歳以上です"
    }
    println(message)
  }

  def outputIterate(): Unit = {
    var i = 0
    while(i <= 10) {
      println("i = " + i)
      i = i + 1
    }
  }

  def doubleLoop(): Unit = {
    for (x <- 1 to 5; y <- 1 until 5 if x != y) {
      println("x = " + x + " y = " + y)
    }
  }

  def collectionLoop(): Unit =
    for(e <- Seq("A", "B", "C", "D", "E")) println(e)

  def generateCollection(): Seq[String] =
    for(e <- Seq("A", "B", "C", "D", "E")) yield "Pre" + e
}
