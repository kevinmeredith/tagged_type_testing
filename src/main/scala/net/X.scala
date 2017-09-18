package net 

import scalaz._, Scalaz._

object X {


  def main(args: Array[String]): Unit = {
    println(regular)
    println(unwrapped)
  }

  trait Foo

  def regular: String = {
    val foo: String @@ Foo = Tag.of[Foo]("regular")
    s"hi $foo"
  } 

  def unwrapped: String = {
    val	 foo: String @@ Foo = Tag.of[Foo]("unwrapped")
    s"hi ${Tag.unwrap(foo)}"
  }

}