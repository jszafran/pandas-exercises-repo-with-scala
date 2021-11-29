package dev.jszafran
package exercises.grouping.regiment

import parsers.RegimentTestResultsParser

object Regiment extends App {
  val soldiersResults = RegimentTestResultsParser.parseData("./datasets/fictional_army_test_results.csv")

  println(soldiersResults.head)
}
