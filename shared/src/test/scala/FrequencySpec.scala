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
   val count = 5

   val idNumber : Int = 14
   val intFreq : Frequency[Int] = Frequency(idNumber, count)
   intFreq.item match {
     case i: Int => assert(true)
   }

   val truthy: Boolean = true
   val booleanFreq: Frequency[Boolean] = Frequency(truthy, count)
   booleanFreq.item match {
     case b: Boolean => assert(true)
   }

   val item : String = "word"
   val stringFreq : Frequency[String] = Frequency(item, count)
   stringFreq.item match {
     case s: String => assert(true)
     case _ => fail(s"${stringFreq.item} was not a String")
   }
 }


}
