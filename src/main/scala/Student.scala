class Student(
  val name: String,
  val grade: Int
) {

  override def toString = s"Student($name, $grade)"

  def canEqual(other: Any): Boolean = other.isInstanceOf[Student]

  override def equals(other: Any): Boolean = other match {
    case that: Student =>
      (that canEqual this) &&
        name == that.name &&
        grade == that.grade
    case _ => false
  }

  override def hashCode(): Int = {
    val state = Seq(name, grade)
    state.map(_.hashCode()).foldLeft(0)((a, b) => 31 * a + b)
  }
}

object Student {
  def apply(name: String, grade: Int): Student = Student(name, grade)
}