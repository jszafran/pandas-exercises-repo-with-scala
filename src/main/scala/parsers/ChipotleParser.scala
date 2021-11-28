package dev.jszafran
package parsers

case class Order(id: Int, quantity: Int, itemName: String, choiceDescription: String, price: Double)

object ChipotleParser {
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

  def parseData(filePath: String): List[Order] = {
    val textSource = io.Source.fromFile(filePath)
    val lines = textSource.getLines.drop(1)
    val orders = for (line <- lines) yield parseLine(line)
    orders.toList
  }
}
