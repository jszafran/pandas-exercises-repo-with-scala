package dev.jszafran
package gettingandknowingyourdata.chipotle

import utils._

import dev.jszafran.parsers.ChipotleParser


object Chipotle extends App {
  val orders = ChipotleParser.parseData("./datasets/Chipotle.tsv")

  // Q1
  println("Q: See the first 10 entries")
  orders.take(10).foreach(println)
  printBreak()

  // Q2
  println("Q: What is the number of observations in the dataset?")
  println(s"A: Number of observations: ${orders.length}")
  printBreak()

  // Q3 & Q4
  println("Q: Which was the most-ordered item?")
  println("Q: For the most-ordered item, how many items were ordered?")
  val mostOrderedItem = orders
    .groupBy(_.itemName)
    .transform((k, v) => v.map(_.quantity).sum)
    .toSeq
    .maxBy(_._2)
  println(s"A: ${mostOrderedItem._1} was ordered ${mostOrderedItem._2} times.")
  printBreak()

  // Q5
  println("Q: What was the most ordered item in the choice_description column?")
  val q5Answer = orders
    .filterNot(_.choiceDescription == "NULL")   // assume that nulls shouldn't be counted
    .map(_.choiceDescription)
    .groupBy(identity)
    .transform((k, v) => v.size)
    .toSeq
    .maxBy(_._2)
    ._1
  println(s"A: Most ordered item by the choice description column: $q5Answer")
  printBreak()

  // Q6
  println("Q: How many items were ordered in total")
  val itemsOrderedTotal = orders
    .map(_.quantity)
    .sum
  println(s"A: $itemsOrderedTotal")

  // Q7
  println("Q: How much was the revenue for the period in the dataset?")
  val revenueAmount = orders
    .map(o => o.quantity * o.price)
    .sum
  println(s"A: Revenue for the period in the dataset: $$${roundDouble(revenueAmount, 2)}")
  printBreak()

  // Q8
  println("Q: How many orders were made in the period?")
  val totalOrdersAmount = orders.map(_.id).distinct.length
  println(s"A: Total amount of orders: $totalOrdersAmount")
  printBreak()

  // Q9
  println("Q: What is the average revenue amount per order?")
  val revenuePerOrder = orders
    .groupBy(_.id)
    .transform((k, v) => v.map(o => o.quantity * o.price).sum)
  val averageRevenuePerOrder = revenuePerOrder.toSeq.map(_._2).sum / revenuePerOrder.size
  println(s"A: Average revenue per order $$${roundDouble(averageRevenuePerOrder, 2)}")
}
