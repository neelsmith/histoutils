---
title: A worked exmaple
layout: page
---


## Background: the data set we'll look at

This example will construct a `Histogram` from a data set counting the number of words in each hexameter line of a Greek text of the *Iliad*.  (If you're interested in how we built this data set, see [the full story here](./building-dataset/))

```scala mdoc:invisible

import scala.io.Source
val iliadUrl = "https://raw.githubusercontent.com/neelsmith/summer2020nbs/master/data/iliad-dipl.txt"
val iliad = Source.fromURL(iliadUrl).getLines.toVector
val iliadNoPunct = iliad.map(ln => ln.replaceAll("[,·\\.:~]", ""))
val iliadWordsPerLine = iliadNoPunct.map(ln => ln.split("[ \n]+").size)
val groupedWordCounts = iliadWordsPerLine.
   groupBy(count => count).
   toVector.
   map{ case (count, occurrences) => (count, occurrences.size)}
```


Here's what our data set looks like:

```scala mdoc
groupedWordCounts
```


## Constructing a `Histogram` object

Import the library.

```scala mdoc
import edu.holycross.shot.histoutils._
```


Convert the list of paired items and counts to a list of `Frequency` objects.

```scala mdoc
val freqs = groupedWordCounts.map{ case (wordcount, occurrences) => Frequency(wordcount, occurrences)}
```

Notice  that the result is a `Frequency` of `Int` items.

We can create a `Histogram` from a list of `Frequency`s.

```scala mdoc
val iliadWordsHisto = Histogram(freqs)
```

Notice that this time the result is a `Histogram` of `Int` items.


## Using a `Histogram`
