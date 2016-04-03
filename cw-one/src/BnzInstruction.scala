package ScalaSML

case class BnzInstruction(label: String, op: String, val register: Int, val target: String) extends Instruction(label, op) {

  override def execute(m: Machine) {
    val nextLabel = m.labels.labels.indexOf(target)
    if ( m.regs(register) != 0 ) m.execute(nextLabel)
  }

  override def toString(): String = {
    super.toString + " if value in register " + register + " is equal to 0 go to " + target + "\n"
  }
}

  object BnzInstruction {
  def apply(label: String, register: Int, target: String) = {
    new BnzInstruction(label, "bnz", register, target)
  }
}