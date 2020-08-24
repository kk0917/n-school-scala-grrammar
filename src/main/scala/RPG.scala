import java.util.Random

object RPG extends App {
  val random       = new Random()
  val monsterCount = 5
  val hero         = new Hero("ポクお", 200, 50)
  var monsters     = for (i <- 1 to monsterCount) yield new Monster(random.nextInt(300), random.nextInt(120), false)

  println(
    s"""あなた、${hero.name}は冒険中のヒーローであり、
      |モンスターが潜んでいる洞窟を抜けねばならない。
      |【ルール】:
      |1を入力してEnterキーを押すと攻撃、それ以外を入力すると逃走となる。
      |逃走成功確率は50%。逃走に失敗した場合はダメージをうける。
      |またモンスターを倒した場合、モンスターの武器の攻撃力が自分より高い場合はその武器を奪うことできる。
      |---------------------------------------------
      |未知のモンスターが${monsterCount}匹あらわれた。""".stripMargin)
  println(s"${ monsters.map { m: Monster => println(m.name) }}") // TODO: Shouldn't output lastline
  println(s"\n【現在の状態】: ${hero}")

  while (monsters.nonEmpty) {
    val monster = monsters.head
    val input   = scala.io.StdIn.readLine(s"\n【選択】: 攻撃[1] or 逃走[0] > ")

    if (input == "1") { // 攻撃する
      // hero's turn
      hero.attack(monster)
      println(s"${hero.name}は${hero.attackDamage}のダメージを与えた。")

      // monster's turn
      monster.attack(hero)

      val message = if (monster.attackDamage == 0) {
        s"${monster.name}は${hero.name}にダメージを与えられない。"

      } else {
        s"${monster.name}は${hero.name}に${monster.attackDamage}のダメージを与えた。"
      }

      println(message)

      println(s"【現在の状態】: ${hero}, ${monster}")

    } else if (input == "0") { // 逃走する
      if (hero.isEscape(monster)) {
        println(s"${hero.name}は、モンスターから逃走に成功した。")
        monster.isAwayFromHero = true
        println(s"【現在の状態】: ${hero}")

      } else {
        monster.attack(hero)
        println(s"${hero.name}は、モンスターから逃走に失敗し、${monster.attackDamage}のダメージを受けた。")
        println(s"【現在の状態】: ${hero}, ${monster}")
      }
    } else {
      println("入力に誤りがあります。")
    }

    if (!hero.isAlive) { // Hero が死んでいるかどうか
      println(
        """---------------------------------------------
          |【ゲームオーバー】: ${hero.name}は無残にも殺されてしまった。 """.stripMargin)

      System.exit(0)

    } else if (!monster.isAlive || monster.isAwayFromHero) { // Monster いないかどうか
      if(!monster.isAwayFromHero) { // 倒した場合
        println(s"モンスターは倒れた。")

        if (hero.attackDamage < monster.attackDamage) {
          hero.attackDamage = monster.attackDamage
          println(s"そして${hero.name}は、モンスターの武器を奪い攻撃力が${monster.attackDamage}に上がった！")
        }
      }

      monsters = monsters.tail

      println(s"残りのモンスターは${monsters.length}匹となった。")

      if (monsters.nonEmpty) {
        println(
          """---------------------------------------------
            |新たな未知のモンスターがあらわれた。 """.stripMargin)
      }
    }
  }

  println(
    s"""---------------------------------------------
      |【ゲームクリア】: ${hero.name}は困難を乗り越えた。新たな冒険に祝福を。
      |【結果】: ${hero}""".stripMargin)

  System.exit(0)
}

abstract class Creature(
  var hitPoint:     Int,
  var attackDamage: Int
) {
  var name: String = "{{name}}"

  def isAlive: Boolean = this.hitPoint > 0

  def attack(target: Creature): Unit = {
    target.hitPoint -= this.attackDamage
  }

  // TODO: add avoid attacks

  // TODO: add defence attacks
}

class Hero(
  _name:         String,
  _hitPoint:     Int,
  _attackDamage: Int

) extends Creature (_hitPoint, _attackDamage) {
  name = _name

  def isEscape(monster: Monster): Boolean = {
    val isEscaped = RPG.random.nextInt(2) == 1
    isEscaped
  }

  override def toString =
    s"${name}(体力:${if (hitPoint > 0) hitPoint else 0}, 攻撃力:${attackDamage})"
}

class Monster(
  _hitPoint:     Int,
  _attackDamage: Int,
  var isAwayFromHero: Boolean

) extends Creature(_hitPoint, _attackDamage) {
  val nameList: Seq[String] = Seq(
    "hogehoge",
    "fugafuga",
    "dosudosu",
    "gasugasu",
    "pokupoku"
  )
  name = setName()

  def setName(): String = {
    val key = RPG.random.nextInt(5)
    nameList(key)
  }

  override def toString =
    s"${name}(体力:${if (hitPoint > 0) hitPoint else 0}, 攻撃力:${attackDamage}, ヒーローから離れている:${isAwayFromHero})"

}