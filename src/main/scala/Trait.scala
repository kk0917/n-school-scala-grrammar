trait TraitA {
  def greet(): Unit
}

trait TraitB extends TraitA {
  override def greet(): Unit = println("Good morning!")
}

trait TraitC extends TraitA {
  override def greet(): Unit = println("Good evening!")
}

/**
 * TraitC that was mixed in later has priority,
 * because the inheritance order of traits has been linearized.
 *
 * ex. (new Class1).greet() >> Good evening!
 */
class Class1 extends  TraitC with TraitB