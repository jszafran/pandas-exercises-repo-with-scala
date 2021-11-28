package dev.jszafran
package exercises.filteringandsorting.euro12

import parsers.EuroTeamsDataParser

import utils.printBreak

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
    .map(t => (t.name, t.redCards, t.yellowCards)).reverse
  teamsSortedByCards.foreach(println)
  printBreak()

  // Q3
  println("Q: Calculate the mean Yellow Cards given per Team")
  val yellowCardsMeanPerTeam = teams.map(_.yellowCards).sum / teams.length
  println(yellowCardsMeanPerTeam)
  printBreak()

  // Q4
  println("Q: Filter teams that scored more than 6 goals")
  val teamsWithMoreThan6Goals = teams
    .filter(_.goals > 6)
    .map(t => (t.name, t.goals))

  teamsWithMoreThan6Goals.foreach(println)
  printBreak()

  // Q5
  println("Q: Select the teams that start with G")
  val teamsStartingWithGLetter = teams
    .map(_.name)
    .filter(_.startsWith("G"))

  teamsStartingWithGLetter.foreach(println)
  printBreak()

  // Q7
  println("Q: Present only the Shooting Accuracy from England, Italy and Russia")
  val accuracyForSelectedCountries =  teams
    .filter(t => Set("England", "Italy", "Russia").contains(t.name))
    .map(t => (t.name, t.shootingAccuracy))

  accuracyForSelectedCountries.foreach(println)
}
