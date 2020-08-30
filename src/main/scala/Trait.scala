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

trait TraitD {
  def greet(): Unit = println("Hello!")
}

trait TraitE extends TraitD {
  override def greet(): Unit = {
    super.greet()
    println("My name is Terebi-chan")
  }
}

trait  TraitF extends  TraitD {
  override def greet(): Unit = {
    super.greet()
    println("I like niconico.")
  }
}
/**
 * enable to output in order by using super
 *
 * ex. (new Class2).greet() >> """
 *   |Hello!
 *   |My name is Terebi-chan
 *   |I like niconico."""
 */

class Class2 extends TraitE with TraitF
class Class3 extends TraitF with TraitE