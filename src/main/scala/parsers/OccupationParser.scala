package dev.jszafran
package parsers

case class User(id: Int, age: Int, gender: String, occupation: String, zipCode: String)


object OccupationParser {
  private def lineParsingFunc(line: String): User = {
    val ls = line.split("\\|")
    User(
      id=ls(0).toInt,
      age=ls(1).toInt,
      gender=ls(2),
      occupation=ls(3),
      zipCode=ls(4)
    )
  }

  def parseData(filePath: String): List[User] = {
    GenericTextParser.parseData[User](
      filePath = filePath,
      lineParsingFunc = lineParsingFunc,
      skipHeader = true
    )
  }
}
