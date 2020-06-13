---
title: A worked exmaple
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

