package edu.holycross.shot.histoutils

import org.scalatest.FlatSpec


class HistogramSpec extends FlatSpec {

 "A Histogram" should "sort frequencies by count from highest to lowest" in {
   val freqs = Vector(
     Frequency("a", 1),
     Frequency("b", 2),
     Frequency("c", 3)
   )
   val histo: Histogram[String] = Histogram(freqs)

   val expectedStrings = Vector("c", "b", "a")
   assert(histo.sorted.map(_.item) == expectedStrings)
 }

}
