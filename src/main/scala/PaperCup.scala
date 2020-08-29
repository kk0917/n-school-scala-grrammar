trait Fillable {
  def fill(): Unit = println("Fill!")
}

trait Disposable {
  def dispose(): Unit = println("Dispose!")
}

class Paper

class PaperCup extends Paper with Fillable with Disposable