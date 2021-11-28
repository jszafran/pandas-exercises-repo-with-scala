package dev.jszafran

package object utils {
  def printBreak(n: Int = 2): Unit = {
    (0 until n).foreach(_ => println(""))
  }

  def roundDouble(double: Double, decimalPlaces: Int = 2): Double = {
    val fmtString = s"%.${decimalPlaces}f"
    fmtString.formatted(double).toDouble
  }

  // TODO: figure out how to do it generically
  def medianInt(nums: List[Int]): Double = {
    val sNums = nums.sortWith(_ < _)
    if (sNums.length % 2 == 0) {
      (sNums(sNums.length / 2) + sNums((sNums.length / 2) - 1)) / 2.0
    } else {
      sNums(sNums.length / 2).toDouble
    }
  }

  def medianDouble(nums: List[Double]): Double = {
    val sNums = nums.sortWith(_ < _)
    if (sNums.length % 2 == 0) {
      (sNums(sNums.length / 2) + sNums((sNums.length / 2) - 1)) / 2.0
    } else {
      sNums(sNums.length / 2)
    }
  }
}
