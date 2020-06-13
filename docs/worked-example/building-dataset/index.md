---
layout: page
title: Building the word-counts data set
---


We start by loading a text of the *Iliad* as a Vector of Strings, where each element of the Vector is one line of the text.  

```scala

import scala.io.Source
val iliadUrl = "https://raw.githubusercontent.com/neelsmith/summer2020nbs/master/data/iliad-dipl.txt"
val iliad = Source.fromURL(iliadUrl).getLines.toVector
```




Here's line 1 of the *Iliad*, for example,

```scala
println(iliad.head)
// Μῆνιν ἄειδε θεὰ Πηληϊάδεω Ἀχιλῆος
```

and here's how many lines there are in this particular *Iliad*:
```scala
iliad.size
// res1: Int = 15639
```

Next, we split each line up into a list of words, and count them.  The result is a list of integers, recording the number of words in each of the 15639 lines.
```scala
val iliadNoPunct = iliad.map(ln => ln.replaceAll("[,·\\.:~]", ""))
val iliadWordsPerLine = iliadNoPunct.map(ln => ln.split("[ \n]+").size)
```

Here's what the first ten lines look like:

```scala
iliadWordsPerLine.take(10)
// res2: Vector[Int] = Vector(5, 6, 6, 6, 7, 7, 7, 7, 8, 8)
```

We group the values for line count and map the pairing of word count and *list* of occurrences to a pairing of word count and *number* of occurrences.  (Here, we're chaining the `groupBy`, `toVector` and `map` methods together successively, and can do all of this in one line of code!)

```scala
val groupedWordCounts = iliadWordsPerLine.
   groupBy(count => count).
   toVector.
   map{ case (count, occurrences) => (count, occurrences.size)}
// groupedWordCounts: Vector[(Int, Int)] = Vector(
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

That's exactly what we want to create a `Histogram`~
