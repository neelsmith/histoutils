package edu.holycross.shot.histoutils

import org.scalatest.FlatSpec



class HistogramObjectSpec extends FlatSpec {

 "The Histogram object" should "create a Histogram[String] from 2-column delimited-text input" in {
   val cex = """in#325
et#237
ad#197
cum#174
ut#136
se#103
non#101
est#98
ex#92
qui#86
"""
    val hist : Histogram[String] = Histogram.fromCex((cex))
    hist match {
      case h: Histogram[String] => assert(true)
      case _ => fail("Failed to make Histogram from CEX source")
    }
  }

}
