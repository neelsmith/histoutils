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

 it should "indicate number of entries in the histogram" in {
   val freqs = Vector(
     Frequency("a", 1),
     Frequency("b", 2),
     Frequency("c", 3)
   )
   val histo: Histogram[String] = Histogram(freqs)
   assert(histo.size == 3)
 }

 it should "sum total of all counts" in {
   val freqs = Vector(
     Frequency("a", 1),
     Frequency("b", 2),
     Frequency("c", 3)
   )
   val histo: Histogram[String] = Histogram(freqs)
   assert(histo.total == 6)
 }

 it should "compute a percent of the total of all counts" in {
  val freqs = Vector(
    Frequency("a", 1),
    Frequency("b", 2),
    Frequency("c", 3)
  )
  val histo: Histogram[String] = Histogram(freqs)
  assert(histo.percent(4) == 66)
 }


  it should "extract Frequencys totalling a given percent of the whole" in {
   val freqs = Vector(
     Frequency("a", 1),
     Frequency("b", 2),
     Frequency("c", 3),
     Frequency("d", 4),
     Frequency("e", 5),
     Frequency("f", 6)
   )
   val histo: Histogram[String] = Histogram(freqs)

   // == 52% of total
   val expected = Vector(Frequency("f",6), Frequency("e",5))
   assert(histo.takePercent(50) == expected)
 }

  it should "compute what percent of the histogram a given item's value represents" in {
    val freqs = Vector(
      Frequency("a", 1),
      Frequency("b", 2),
      Frequency("c", 3),
      Frequency("d", 4),
      Frequency("e", 5),
      Frequency("ebis", 5),
      Frequency("f", 6),
      Frequency("g", 7),
      Frequency("h", 8),
      Frequency("i", 9)
    )
    val histo: Histogram[String] = Histogram(freqs)

    assert(histo.total == 50)
    assert(histo.size == 10)
    assert(histo.percentForItem("e") == 80)
  }

  it should "compute what percent of the histogram a given value represents" in {
    val freqs = Vector(
      Frequency("a", 1),
      Frequency("b", 2),
      Frequency("c", 3),
      Frequency("d", 4),
      Frequency("e", 5),
      Frequency("ebis", 5),
      Frequency("f", 6),
      Frequency("g", 7),
      Frequency("h", 8),
      Frequency("i", 9)
    )
    val histo: Histogram[String] = Histogram(freqs)

    assert(histo.total == 50)
    assert(histo.size == 10)
    assert(histo.percentForCount(5) == 80)
  }

  it should "export to CEX" in {
    val freqs = Vector(
      Frequency("a", 1),
      Frequency("b", 2),
      Frequency("c", 3)
    )
    val histo: Histogram[String] = Histogram(freqs)

    val expected = "c#3\nb#2\na#1"
    assert(histo.cex() == expected)
  }

}
