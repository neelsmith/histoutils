package edu.holycross.shot.histoutils

case class Histogram[T] (frequencies: Vector[Frequency[T]])  {

  def sorted = frequencies.sortWith(_.count > _.count)


}
