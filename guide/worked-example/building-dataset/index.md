---
layout: page
title: Building the word-counts data set
---


We start by loading a text of the *Iliad* as a Vector of Strings, where each element of the Vector is one line of the text.  

```scala mdoc:silent

import scala.io.Source
val iliadUrl = "https://raw.githubusercontent.com/neelsmith/summer2020nbs/master/data/iliad-dipl.txt"
val iliad = Source.fromURL(iliadUrl).getLines.toVector
```




Here's line 1 of the *Iliad*, for example,

```scala mdoc
println(iliad.head)
```

and here's how many lines there are in this particular *Iliad*:
```scala mdoc
iliad.size
```

Next, we split each line up into a list of words, and count them.  The result is a list of integers, recording the number of words in each of the 15639 lines.
```scala mdoc:silent
val iliadNoPunct = iliad.map(ln => ln.replaceAll("[,·\\.:~]", ""))
val iliadWordsPerLine = iliadNoPunct.map(ln => ln.split("[ \n]+").size)
```

Here's what the first ten lines look like:

```scala mdoc
iliadWordsPerLine.take(10)
```

We group the values for line count and map the pairing of word count and *list* of occurrences to a pairing of word count and *number* of occurrences.  (Here, we're chaining the `groupBy`, `toVector` and `map` methods together successively, and can do all of this in one line of code!)

```scala mdoc
val groupedWordCounts = iliadWordsPerLine.
   groupBy(count => count).
   toVector.
   map{ case (count, occurrences) => (count, occurrences.size)}
```

That's exactly what we want to create a `Histogram`~
