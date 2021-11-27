package dev.jszafran

package object utils {
  def printBreak(n: Int = 2): Unit = {
    (0 until n).foreach(_ => println(""))
  }

  def roundDouble(double: Double, decimalPlaces: Int = 2): Double = {
    val fmtString = s"%.${decimalPlaces}f"
    fmtString.formatted(double).toDouble
  }
}
