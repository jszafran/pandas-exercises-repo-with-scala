package dev.jszafran
package parsers

case class RegimentCompany(
                            regiment: String,
                            company: String,
                            deaths: Int,
                            battles: Int,
                            size: Int,
                            veterans: Int,
                            readiness: Int,
                            armored: Boolean,
                            deserters: Int,
                            origin: String,
                          )

object FictionalArmyParser {
  private def lineParsingFunc(line: String): RegimentCompany = {
    val sl = line.split(",")
    RegimentCompany(
      regiment = sl(0),
      company = sl(1),
      deaths = sl(2).toInt,
      battles = sl(3).toInt,
      size = sl(4).toInt,
      veterans = sl(5).toInt,
      readiness = sl(6).toInt,
      armored = sl(7).toInt match {
        case 0 => false;
        case 1 => true;
      },
      deserters = sl(8).toInt,
      origin = sl(9)
    )
  }

  def parseData(filePath: String): List[RegimentCompany] = {
    GenericTextParser.parseData[RegimentCompany](
      filePath = filePath,
      lineParsingFunc = lineParsingFunc
    )
  }
}
