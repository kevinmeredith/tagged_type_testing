This output demonstrates that there's no need to call `Tag.unwrap(taggedValue)` from String interpolation, no?

```
$cat src/main/scala/net/X.scala
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
    val		  foo: String @@ Foo = Tag.of[Foo]("unwrapped")
    s"hi ${Tag.unwrap(foo)}"
  }

}
```

and `javap`:

```
$javap -c -l target/scala-2.12/classes/net/X$Foo.class
Compiled from "X.scala"
public final class net.X {
  public static java.lang.String unwrapped();
    Code:
       0: getstatic     #19                 // Field net/X$.MODULE$:Lnet/X$;
       3: invokevirtual #21                 // Method net/X$.unwrapped:()Ljava/lang/String;
       6: areturn

  public static java.lang.String regular();
    Code:
       0: getstatic     #19                 // Field net/X$.MODULE$:Lnet/X$;
       3: invokevirtual #24                 // Method net/X$.regular:()Ljava/lang/String;
       6: areturn

  public static void main(java.lang.String[]);
    Code:
       0: getstatic     #19                 // Field net/X$.MODULE$:Lnet/X$;
       3: aload_0
       4: invokevirtual #28                 // Method net/X$.main:([Ljava/lang/String;)V
       7: return
}
```
