package edu.holycross.shot.histoutils

import org.scalatest.FlatSpec


class FrequencySpec extends FlatSpec {

 "A Frequency" should "have an integer count for an item" in {

   val item : String = "word"
   val count = 5
   val freq = Frequency(item, 5)
   assert(freq.count == count)
 }

 it should "have a type-qualified item you can recover with pattern matching" in {
   val item : String = "word"
   val count = 5
   val stringFreq = Frequency(item, 5)
   stringFreq.item match {

     case s: String => assert(true)
     case _ => fail(s"${stringFreq.item} was not a String")
   }
 }
}
