package edu.holycross.shot.histoutils


/** Generic class for working with frequency counts of items
* of a given type T.
*
* @param frequencies Vector of [[Frequency]] counts.
*/
case class Histogram[T] (frequencies: Vector[Frequency[T]])  {

  /** Sort frequencies by count, descending.*/
  def sorted : Vector[Frequency[T]]= frequencies.sortWith(_.count > _.count)

  /** Number of entries in the histogram.*/
  def size : Int = frequencies.size

  /** Total of all count values.*/
  def total: Int = frequencies.map(_.count).sum

  /** Compute percent of total entries.  Crudely
  * round down to Int value, e.g., percent(2) out
  * of a total of 3 == 66, not 67.
  *
  * @param n What percent of the total in this histogram
  * does the count n represent?
  */
  def percent(n: Int) : Int = {
    ((n / total.toDouble) * 100).toInt
  }

  /** Return minimum number of frequencies with counts summing
  * up to or beyond a percent threshhold by recursively extracting
  * top entry and inspecting running total.
  *
  * @param threshhold Percent of totals to extract.
  * @param runningTotal Total of counts seen so far.
  * @param source Vector of [[Frequency]]s remaining to consider.
  * @param entries Vector of [[Frequency]]s extracted so far.
  */
  def takePercent(
    threshhold: Int,
    runningTotal : Int = 0,
    source:  Vector[Frequency[T]]= sorted,
    entries:  Vector[Frequency[T]] = Vector.empty[Frequency[T]]
  ): Vector[Frequency[T]] = {
    val currentTotal = runningTotal + source.head.count

    if (source.isEmpty) {
      println(s"Histogram: ran out of frequency counts in source vector looking for threshhold percentage ${threshhold}.")
      entries :+ source.head

    } else if (percent(currentTotal) >= threshhold) {
      entries :+ source.head

    } else {
      takePercent(threshhold, currentTotal, source.tail, entries :+ source.head)
    }
  }


  /** Compute what percent of the histogram the total values up to
  * a given item's value represents.  E.g., if for four entries
  * with counts  4,3,2,1, the percent for subtotal at 3 is 70%
  * (subtotal of 7 out of total of 10). Note that if multiple items
  * have the same count as the requesteditem, their counts are included:
  * we want the percent for all items with this count value.
  *
  * @param item What percent of the histogram does
  */
  def percentForItem[T](item: T) = {
    val frequency = frequencies.filter(_.item == item)
    frequency.size match {
      case 1 => {
        val totals = frequencies.filter(_.count >= frequency(0).count).map(_.count).sum
        percent(totals)
      }

      case 0 => throw new Exception("Histogram: no entry for item " + item)
      case _ => throw new Exception("Somehow found more than one entry for " + item)
    }
  }

  /** Compute what percent of the histogram the total values up
  * a given count constitute.
  *
  * @param count Threshhold count to include.
  */
  def percentForCount(count: Int) = {
    val frequencyList = frequencies.filter(_.count >= count)
    percent(frequencyList.map(_.count).sum)
  }

  def cex(delimiter: String = "#") : String = {
    sorted.map(_.cex(delimiter)).mkString("\n")
  }

}
