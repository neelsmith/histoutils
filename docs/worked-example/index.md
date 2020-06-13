---
title: A worked example
layout: page
---


## Background: the data set we'll look at

This example will construct a `Histogram` from a data set counting the number of words in each hexameter line of a Greek text of the *Iliad*.  (If you're interested in how we built this data set, see [the full story here](./building-dataset/))



Here's what our data set looks like:

```scala
groupedWordCounts
// res0: Vector[(Int, Int)] = Vector(
//   (5, 1194),
//   (10, 536),
//   (1, 31),
//   (6, 3556),
//   (9, 1652),
//   (2, 27),
//   (12, 15),
//   (7, 4810),
//   (3, 24),
//   (11, 114),
//   (8, 3411),
//   (4, 269)
// )
```


## Constructing a `Histogram` object

Import the library.

```scala
import edu.holycross.shot.histoutils._
```


Convert the list of paired items and counts to a list of `Frequency` objects.

```scala
val freqs = groupedWordCounts.map{ case (wordcount, occurrences) => Frequency(wordcount, occurrences)}
// freqs: Vector[Frequency[Int]] = Vector(
//   Frequency(5, 1194),
//   Frequency(10, 536),
//   Frequency(1, 31),
//   Frequency(6, 3556),
//   Frequency(9, 1652),
//   Frequency(2, 27),
//   Frequency(12, 15),
//   Frequency(7, 4810),
//   Frequency(3, 24),
//   Frequency(11, 114),
//   Frequency(8, 3411),
//   Frequency(4, 269)
// )
```

Notice  that the result is a `Frequency` of `Int` items.

We can create a `Histogram` from a list of `Frequency`s.

```scala
val iliadWordsHisto = Histogram(freqs)
// iliadWordsHisto: Histogram[Int] = Histogram(
//   Vector(
//     Frequency(5, 1194),
//     Frequency(10, 536),
//     Frequency(1, 31),
//     Frequency(6, 3556),
//     Frequency(9, 1652),
//     Frequency(2, 27),
//     Frequency(12, 15),
//     Frequency(7, 4810),
//     Frequency(3, 24),
//     Frequency(11, 114),
//     Frequency(8, 3411),
//     Frequency(4, 269)
//   )
// )
```

Notice that this time the result is a `Histogram` of `Int` items.


## Using a `Histogram`


How many entries are there in the histogram?

```scala
iliadWordsHisto.size
// res1: Int = 12
```


What is the total number of occurrences?

```scala
iliadWordsHisto.total
// res2: Int = 15639
```

Find occurrences for a value.  How many lines have 5 words?

```scala
iliadWordsHisto.countForItem(5)
// res3: Int = 1194
```

Select the minimum number of entries that make up a given percent of the total. What entries get us to the Pareto distribution of 80%?

```scala
iliadWordsHisto.takePercent(80)
// res4: Vector[Frequency[Int]] = Vector(
//   Frequency(7, 4810),
//   Frequency(6, 3556),
//   Frequency(8, 3411),
//   Frequency(9, 1652)
// )
```


Find percent of the entries with a count equal or greater than the count for a specified item.  How many lines have 5 or more words?

```scala
iliadWordsHisto.percentForItem(5)
// res5: Int = 93
```


Find percent of the entries with a count equal or greater than a specified count.  How many line sizes occur 2000 or more times?


```scala
iliadWordsHisto.percentForCount(2000)
// res6: Int = 75
```

Sort the histogram by frequency, descending.

```scala
iliadWordsHisto.sorted
// res7: Histogram[Int] = Histogram(
//   Vector(
//     Frequency(7, 4810),
//     Frequency(6, 3556),
//     Frequency(8, 3411),
//     Frequency(9, 1652),
//     Frequency(5, 1194),
//     Frequency(10, 536),
//     Frequency(4, 269),
//     Frequency(11, 114),
//     Frequency(1, 31),
//     Frequency(2, 27),
//     Frequency(3, 24),
//     Frequency(12, 15)
//   )
// )
```
