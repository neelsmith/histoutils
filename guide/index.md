---
title: histoutils library
layout: page
---


- the `Frequency` class
- the `Histogram` class
-


```scala mdoc
import scala.io.Source
val iliadUrl = "https://raw.githubusercontent.com/neelsmith/summer2020nbs/master/data/iliad-dipl.txt"
val scholiaUrl = "https://raw.githubusercontent.com/neelsmith/summer2020nbs/master/data/scholia-dipl.txt"

val iliad = Source.fromURL(iliadUrl).mkString("")
val scholia = Source.fromURL(scholiaUrl).mkString("")
```
