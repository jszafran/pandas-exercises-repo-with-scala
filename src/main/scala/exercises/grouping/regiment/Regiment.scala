package dev.jszafran
package exercises.grouping.regiment

import parsers.RegimentTestResultsParser

import utils.printBreak

object Regiment extends App {
  val soldiersResults = RegimentTestResultsParser.parseData("./datasets/fictional_army_test_results.csv")

  // Q1
  println("Q: What is the mean preTestScore from the regiment Nighthawks?")
  val q1Answer = soldiersResults
    .filter(_.regiment == "Nighthawks")
    .map(_.preTestScore).sum / soldiersResults.count(_.regiment == "Nighthawks").toDouble
  println(q1Answer)
  printBreak()

  // Q2
  println("Q: What is the mean of each company's preTestScore?")
  val q2Answer = soldiersResults
    .groupBy(_.companyName)
    .transform((k, v) => v.map(_.preTestScore).sum / v.length.toDouble)
  q2Answer.foreach(println)
  printBreak()

  // Q3
  println("Q: Present the mean preTestScores grouped by regiment and company")
  val q3Answer = soldiersResults
    .groupBy(sr => (sr.regiment, sr.companyName))
    .transform((k, v) => v.map(_.preTestScore).sum / v.length.toDouble)
    .toSeq
    .sortBy(_._1)
  q3Answer.foreach(println)
  printBreak()

  // Q4
  println("Q: What is the number of observations in each regiment and company")
  val q4Answer = soldiersResults
    .groupBy(sr => (sr.regiment, sr.companyName))
    .transform((k, v) => v.length)
    .toSeq
    .sortBy(_._1)
  q4Answer.foreach(println)
}
