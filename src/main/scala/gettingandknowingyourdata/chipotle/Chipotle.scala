package dev.jszafran
package gettingandknowingyourdata.chipotle

import utils.printBreak

case class Order(id: Int, quantity: Int, itemName: String, choiceDescription: String, price: Double)

object Chipotle extends App {
  private def parsePrice(price: String): Double = {
    price.replace("$", "").toDouble
  }

  private def parseLine(line: String): Order = {
    val lineSplit = line.split("\t")
    val id = lineSplit(0)
    val quantity = lineSplit(1)
    val itemName = lineSplit(2)
    val choiceDescription = lineSplit(3)
    val price = parsePrice(lineSplit(4))

    val order = Order(
      id=id.toInt,
      quantity=quantity.toInt,
      itemName=itemName,
      choiceDescription=choiceDescription,
      price=price
    )
    order
  }

  private def parseDatasource(filePath: String): List[Order] = {
    val textSource = io.Source.fromFile(filePath)
    val lines = textSource.getLines.drop(1)
    val orders = for (line <- lines) yield parseLine(line)
    orders.toList
  }

  val orders = parseDatasource("./src/main/scala/gettingandknowingyourdata/chipotle/dataset.tsv")

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
}
