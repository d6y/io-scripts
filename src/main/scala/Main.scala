import scalax.io._
import Resource._

import scalax.file.Path

// Copy all the G*.rules files in the rules folder
// but split the filenames So G12345.drl becomes 5/4/G123.rule
// to supported Win95 limited size folders.

object Main extends App {

val rules = Path("rules") ** "G*.drl"

val dest = Path("/", "tmp", "rules").createDirectory(failIfExists=false)


def w95(s: String) = {
  val (a, rest) = (s.last.toString, s.init)
  val (b, name) = (rest.last.toString, rest.init)
  Path(a,b,name+".rule")
}

rules.foreach { path => 
  val o = dest / w95(path.name split('.') head) 
  path copyTo o
}

}

