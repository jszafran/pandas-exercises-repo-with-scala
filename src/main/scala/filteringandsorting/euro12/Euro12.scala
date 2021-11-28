package dev.jszafran
package filteringandsorting.euro12

import parsers.EuroTeamsDataParser

object Euro12 extends App {
  val teams = EuroTeamsDataParser.parseData("./datasets/Euro_2012_stats_TEAM.csv")
  println(teams.head)
}
