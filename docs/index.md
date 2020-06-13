---
title: histoutils library
layout: page
---


## Overview

This library defines two classes: `Frequency` and `Histogram`.  Like Scala's collection classes (such as `Vector`), `Frequency` and `Histogram` are containers for items of any type.  (When you create a `Frequency` and `Histogram`, you'll see the type of item it contains in square brackets, as you do with a `Vector`, for example. The classes are "type parameterized," in Scala terminology.)

The word "that" occurs [13 times in the Gettsyburg Address](https://mybinder.org/v2/gh/neelsmith/gettysburg/master?filepath=nbs%2Fzipf.ipynb).  We create a `Frequency` from a combination of the item and its count.


```scala
import edu.holycross.shot.histoutils._
val count = Frequency("that", 13)
// count: Frequency[String] = Frequency("that", 13)
```

As you see in the output, `count` is a `Frequency` for a `String`-class item.

`Histogram`s are made from a Vector of `Frequency`s.

```scala
import edu.holycross.shot.histoutils._
val that = Frequency("that", 13)
val the =  Frequency("the", 11)
val frequencies = Vector(that, the)
```
```scala
val histogram = Histogram(frequencies)
// histogram: Histogram[String] = Histogram(
//   Vector(Frequency("that", 13), Frequency("the", 11))
// )
```

`histogram` is a container for `String`-type `Frequency`s.

The `Histogram` class offers many methods for working with a list of frequency counts, that are illustrated in the linked example.

## Example

See a [worked example](./worked-example/) using real-world data.
