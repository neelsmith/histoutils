package edu.holycross.shot.histoutils

/** Generic class for working with frequency counts of an item.*/
case class Frequency[T] (item: T, count: Int) {

  def cex(delimiter : String = "#") :  String = {
    s"${item}${delimiter}${count}"
  }
}
