package dev.jszafran

package object utils {
  def printBreak(n: Int = 2): Unit = {
    (0 until n).foreach(_ => println(""))
  }
}
