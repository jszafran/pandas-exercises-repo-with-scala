package dev.jszafran
package parsers

object GenericTextParser {
  def parseData[T](
                    filePath: String,
                    lineParsingFunc: String => T,
                    skipHeader: Boolean = true,
                  ): List[T] = {
    val source = io.Source.fromFile(filePath)
    val lines = if (skipHeader) {source.getLines.drop(1)} else {source.getLines}
    (for (line <- lines) yield lineParsingFunc(line)).toList
  }
}
