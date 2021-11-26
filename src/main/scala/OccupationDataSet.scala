package dev.jszafran

// data structure definition for text file row
case class User(val id: Int, age: Int, gender: String, occupation: String, zipCode: String)

class DataProcessor(val filePath: String) {
  val users: List[User] = parseUsers()
  def getUsers: List[User] = users

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
  val dp = new DataProcessor("./data/users.txt")
  println(dp.users(1))
}
