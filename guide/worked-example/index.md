---
title: A worked example
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


The data set we'll start from  looks like:

```scala mdoc
groupedWordCounts
```

Note that it's a Vector of pairings of an `Int` (number of words in the line) with a second `Int` (number of lines with this many words).

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


How many entries are there in the histogram?

```scala mdoc
iliadWordsHisto.size
```


What is the total number of occurrences?

```scala mdoc
iliadWordsHisto.total
```

Find occurrences for a value.  How many lines have 5 words?

```scala mdoc
iliadWordsHisto.countForItem(5)
```

Select the minimum number of entries that make up a given percent of the total. What entries get us to the Pareto distribution of 80%?

```scala mdoc
iliadWordsHisto.takePercent(80)
```


Find percent of the entries with a count equal or greater than the count for a specified item.  How many lines have 5 or more words?

```scala mdoc
iliadWordsHisto.percentForItem(5)
```


Find percent of the entries with a count equal or greater than a specified count.  How many line sizes occur 2000 or more times?


```scala mdoc
iliadWordsHisto.percentForCount(2000)
```

Sort the histogram by frequency, descending.

```scala mdoc
iliadWordsHisto.sorted
```
