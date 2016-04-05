package SP3

/*
 * The translator of a <b>S</b><b>M</b>al<b>L</b> program.
 */
class Translator(fileName: String) {
  // word + line is the part of the current line that's not yet processed
  // word has no whitespace
  // If word and line are not empty, line begins with whitespace

  /**translate the small program in the file into lab (the labels) and prog (the program)*/
  def readAndTranslate(m: Machine): Machine = {
    val labels = m.labels
    var program = m.prog
    import scala.io.Source
    val lines = Source.fromFile(fileName).getLines
    for (line <- lines) {
      val fields = line.split(" ")
      if (fields.length > 0) {
        labels.add(fields(0))
        val className = Class.forName("SP3."+fields(1).capitalize+"Instruction")
        val constructor = className.getConstructors()(0)
        val args: Array[Object] = new Array[Object](fields.length)
        for (i <- 0 until fields.length) {
          if (fields(i).matches("-?[0-9]*")) {
            args(i) = new Integer(fields(i))
          } else {
            args(i) = fields(i)
          }
        }

        program = program :+ constructor.newInstance(args:_*).asInstanceOf[Instruction]
      }
    }
    new Machine(labels, program)
  }
}

object Translator {
  def apply(file: String) =
    new Translator(file)
}