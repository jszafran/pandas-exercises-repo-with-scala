package dev.jszafran
package exercises.grouping.occupation

import parsers.OccupationParser

import utils.printBreak

object Occupation extends App {
  val users = OccupationParser.parseData("./datasets/occupation.txt")

  // Q1
  println("Q: Discover what is the mean age per occupation")
  val q1Answer = users
    .groupBy(_.occupation)
    .transform((k, v) => v.map(_.age).sum / v.length)
    .toSeq
  q1Answer.foreach(println)
  printBreak()

  // Q2
  println("Q: Discover the Male ratio per occupation and sort it from the most to the least")
  val q2Answer = users
    .groupBy(_.occupation)
    .transform((k, v) => v.count(_.gender == "M") / v.length.toDouble)
    .toSeq
    .sortBy(_._2)
    .reverse
  q2Answer.foreach(println)
  printBreak()

  // Q3
  println("For each occupation, calculate the minimum and maximum ages")
  val q3Answer = users
    .groupBy(_.occupation)
    .transform((k, v) => {
      val ages = v.map(_.age)
      (ages.min, ages.max)
    })
    .toSeq
    q3Answer.foreach(println)
  printBreak()

  // Q4
  println("For each combination of occupation and gender, calculate the mean age")
  val q4Answer = users
    .groupBy(u => (u.occupation, u.gender))
    .transform((k, v) => v.map(_.age).sum / v.length)
    .toSeq
    .sortBy(_._1._1)
  q4Answer.foreach(println)
  printBreak()


  // Q5
  println("For each occupation present the percentage of women and men")
  val q5Answer = users
    .groupBy(_.occupation)
    .transform((k, v) => {
      val maleCount = v.count(_.gender == "M")
      val femaleCount = v.count(_.gender == "F")
      (femaleCount / v.length.toDouble, maleCount / v.length.toDouble)
    })
    .toSeq
    .sortBy(_._2._1)
    .reverse
   q5Answer.foreach(println)

}
