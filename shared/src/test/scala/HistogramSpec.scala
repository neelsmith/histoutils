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
   assert(histo.sorted.frequencies.map(_.item) == expectedStrings)
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

  it should "require unique item values in frequencies" in {
    val freqs = Vector(
      Frequency("a", 1),
      Frequency("a", 2),
      Frequency("c", 3)
    )
    try {
      val histo: Histogram[String] = Histogram(freqs)
    } catch {
      case iae: IllegalArgumentException => assert(iae.toString.contains("Histogram had multiple frequency counts for the same key"))
      case t : Throwable => fail("Should have thrown IllegalArgumentException")
    }
  }

  it should "find the count for an item" in {
    val freqs = Vector(
      Frequency("a", 1),
      Frequency("b", 2),
      Frequency("c", 3)
    )
    val histo: Histogram[String] = Histogram(freqs)
    assert(histo.countForItem("c") == 3)
  }
  it should "be able to add a second histogram" in {
    val freqs1 =   Vector(
      Frequency("a", 1),
      Frequency("b", 2),
      Frequency("c", 3)
    )
    val h1 : Histogram[String] = Histogram(freqs1)

    val freqs2 =  Vector(
      Frequency("b", 2),
      Frequency("c", 1),
      Frequency("d", 5)
    )
    val h2 : Histogram[String] = Histogram(freqs2)

    val combo = h1 ++ h2
    val expected = Histogram(Vector(Frequency("a",1), Frequency("b",4), Frequency("c",4), Frequency("d",5)))

    assert(combo == expected)


  }

}
