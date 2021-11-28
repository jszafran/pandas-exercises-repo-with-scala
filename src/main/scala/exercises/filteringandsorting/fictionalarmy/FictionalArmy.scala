package dev.jszafran
package exercises.filteringandsorting.fictionalarmy

import parsers.FictionalArmyParser

import utils.printBreak

object FictionalArmy extends App {
  val regiments = FictionalArmyParser.parseData("./datasets/fictional_army.csv")

  // Q1
  println("Q: Select the 'deaths', 'size' and 'deserters' columns from Maine and Alaska")
  val q1Answer = regiments
    .filter(r => r.origin == "Maine" || r.origin == "Alaska")
    .map(r => (r.deaths, r.size, r.deserters))
  q1Answer.foreach(println)
  printBreak()

  // Q2
  println("Q: Select every row after the fourth row and all columns")
  val q2Answer = regiments.drop(4)
  q2Answer.foreach(println)
  printBreak()

  // Q3
  println("Q: Select every row up to the 4th row and all columns")
  val q3Answer = regiments.take(4)
  q3Answer.foreach(println)
  printBreak()

  // Q4
  println("Q: Select rows where df.deaths is greater than 50")
  val q4Answer = regiments
    .filter(_.deaths > 50)
    .map(r => (r.company, r.origin, r.deaths))
  q4Answer.foreach(println)
  printBreak()

  // Q5
  println("Q: Select rows where df.deaths is greater than 500 or less than 50")
  val q5Answer = regiments
    .filter(r => r.deaths > 500 || r.deaths < 50)
    .map(r => (r.company, r.origin, r.deaths))
  q5Answer.foreach(println)
  printBreak()

  // Q6
  println("Q: Select all the regiments not named Dragoons")
  val q6Answer = regiments.filterNot(_.regiment == "Dragoons")
  q6Answer.foreach(println)
  printBreak()

  // Q7
  println("Q: Select the rows called Texas and Arizona")
  val q7Answer = regiments.filter(r => r.origin == "Texas" || r.origin == "Arizona")
  q7Answer.foreach(println)
  printBreak()
}
