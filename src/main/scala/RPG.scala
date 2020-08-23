object RPG {

}

abstract class Creature(
  val hitPoint: Int,
  val attackDamage: Int

) {
  def isAlive(): Boolean = this.hitPoint > 0
}

class Hero(
  _hitPoint: Int,
  _attackDamage: Int

) extends Creature (_hitPoint, _attackDamage) {
  def attack(monster: Monster): Unit = ???
  def escape(monster: Monster): Boolean = ???

  override def toString = s"Hero(体力:${hitPoint}, 攻撃力:${attackDamage})"
}

class Monster(
  _hitPoint: Int,
  _attackDamage: Int,
  var isAwayFromHero: Boolean

) extends Creature(_hitPoint, _attackDamage) {
  override def toString = s"Monster(体力:${hitPoint}, 攻撃力:${attackDamage}, ヒーローから離れている:${isAwayFromHero})"

}