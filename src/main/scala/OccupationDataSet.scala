package dev.jszafran

// data structure definition for text file row
case class User(val id: Int, age: Int, gender: String, occupation: String, zipCode: String)


object Main extends App {
  def parseUsers(filePath: String): List[User] = {
    val textSource = io.Source.fromFile(filePath)
    val lines = textSource.getLines.drop(1) // skip header
    val users = for (line <- lines) yield {
      val lineData = line.split("\\|")
      User(
        id=lineData(0).toInt,
        age=lineData(1).toInt,
        gender=lineData(2),
        occupation=lineData(3),
        zipCode=lineData(4))
    }
    users.toList
  }
  def printBreak(): Unit = {
    println("")
    println("")
  }

  val users = parseUsers("./data/users.txt")

  // Q1
  println("Q: What is the number of observations in the dataset?")
  println(s"A: ${users.length}")
  printBreak()

  // Q2
  println("Q: How many different occupations are in this dataset?")
  val q2Answer1 = users.map(_.occupation).toSet.size
  val q2Answer2 = users.map(_.occupation).distinct.length
  assert(q2Answer1 == q2Answer2)
  println(s"A: ${q2Answer1}")
  printBreak()

  // Q3
  println("Q: What is the most frequent occupation?")
  val q3Answer = users
    .map(_.occupation)
    .groupBy(identity)
    .transform((k, v) => v.size)
    .toSeq
    .maxBy(_._2)
    ._1
  println(s"A: Most frequent occupation: $q3Answer")
  printBreak()

  // Q4
  println("Q: What is the mean age of users?")
  val ageMean = users.map(_.age).sum / users.length
  println(s"A: Users age mean: $ageMean")
  printBreak()

  // Q5
  println("Q: What is the age with least occurrence?")
  val leastOccurrence = users
    .map(_.age)
    .groupBy(identity)
    .transform((k, v) => v.size)
    .toSeq
    .minBy(_._2)
    ._2

  val agesWithLeastOccurrence = users
    .map(_.age)
    .groupBy(identity)
    .transform((k, v) => v.size)
    .filter(_._2 == leastOccurrence)
    .toSeq
    .map(_._1)
  println(s"Ages that occurred $leastOccurrence time(s): ${agesWithLeastOccurrence.mkString(", ")}")
}
