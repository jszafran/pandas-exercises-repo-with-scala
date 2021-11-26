package dev.jszafran

// data structure definition for text file row
case class User(val id: Int, age: Int, gender: String, occupation: String, zipCode: String)

class DataProcessor(val filePath: String) {
  val users: List[User] = parseUsers()

  private def parseUsers(): List[User] = {
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
}

object Main extends App {
  def printBreak(): Unit = {
    println("")
    println("")
  }

  val dp = new DataProcessor("./data/users.txt")
  val users = dp.users

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
  println(s"Most frequent occupation: $q3Answer")
  printBreak()

}


