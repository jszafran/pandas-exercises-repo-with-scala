package dev.jszafran
package parsers

case class Team(
               name: String,
               goals: Int,
               shotsOnTarget: Int,
               shotsOffTarget: Int,
               shootingAccuracy: Double,
               goalsToShots: Double,
               totalShots: Int,
               hitWoodwork: Int,
               penaltyGoals: Int,
               penaltiesNotScored: Int,
               headedGoals: Int,
               passes: Int,
               passesCompleted: Int,
               passingAccuracy: Double,
               touches: Int,
               crosses: Int,
               dribbles: Int,
               cornersTaken: Int,
               tackles: Int,
               clearances: Int,
               interceptions: Int,
               clearancesOffLine: Option[Int],
               cleanSheets: Int,
               blocks: Int,
               goalsConceded: Int,
               savesMade: Int,
               savesToShotsRatio: Double,
               foulsWon: Int,
               foulsConceded: Int,
               offsides: Int,
               yellowCards: Int,
               redCards: Int,
               subsOn: Int,
               subsOff: Int,
               playersUsed: Int
               )

object EuroTeamsDataParser {
  private def strPercentToDouble(n: String): Double = n.replace("%", "").toDouble
  private def lineParsingFunc(line: String): Team = {
    val ls = line.split(",")
    Team(
      name = ls(0),
      goals = ls(1).toInt,
      shotsOnTarget = ls(2).toInt,
      shotsOffTarget = ls(3).toInt,
      shootingAccuracy = strPercentToDouble(ls(4)),
      goalsToShots = strPercentToDouble(ls(5)),
      totalShots = ls(6).toInt,
      hitWoodwork = ls(7).toInt,
      penaltyGoals = ls(8).toInt,
      penaltiesNotScored = ls(9).toInt,
      headedGoals = ls(10).toInt,
      passes = ls(11).toInt,
      passesCompleted = ls(12).toInt,
      passingAccuracy = strPercentToDouble(ls(13)),
      touches = ls(14).toInt,
      crosses = ls(15).toInt,
      dribbles = ls(16).toInt,
      cornersTaken = ls(17).toInt,
      tackles = ls(18).toInt,
      clearances = ls(19).toInt,
      interceptions = ls(20).toInt,
      clearancesOffLine = ls(21) match {
        case "" => None
        case _  => Some(ls(21).toInt)
      },
      cleanSheets = ls(22).toInt,
      blocks = ls(23).toInt,
      goalsConceded = ls(24).toInt,
      savesMade = ls(25).toInt,
      savesToShotsRatio = strPercentToDouble(ls(26)),
      foulsWon = ls(27).toInt,
      foulsConceded = ls(28).toInt,
      offsides = ls(29).toInt,
      yellowCards = ls(30).toInt,
      redCards = ls(31).toInt,
      subsOn = ls(32).toInt,
      subsOff = ls(33).toInt,
      playersUsed = ls(34).toInt
    )
  }

  def parseData(filePath: String): List[Team] = {
    GenericTextParser.parseData[Team](
      filePath = filePath,
      lineParsingFunc = lineParsingFunc,
    )
  }
}
