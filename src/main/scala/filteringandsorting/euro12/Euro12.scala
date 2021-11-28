package dev.jszafran
package filteringandsorting.euro12

import parsers.EuroTeamsDataParser

import dev.jszafran.utils.printBreak

object Euro12 extends App {
  val teams = EuroTeamsDataParser.parseData("./datasets/Euro_2012_stats_TEAM.csv")

  // Q1
  println("Q: How many team participated in the Euro2012?")
  assert(teams.map(_.name).distinct.length == teams.length)
  println(s"A: ${teams.length} teams participated in Euro 2012.")
  printBreak()

  // Q2
  println("Q: Sort the teams by Red Cards, then to Yellow Cards")
  val teamsSortedByCards = teams
    .sortBy(t => (t.redCards, t.yellowCards))
    .map(t => (t.name, t.redCards, t.yellowCards))
  teamsSortedByCards.foreach(println)
  printBreak()
}
