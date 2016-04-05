package SP3

case class OutInstruction(label: String, opcode: String, register: Int)
  extends Instruction(label, opcode) {

  override def execute(m: Machine): Unit = {
    println("Register " + register + " contains " + m.regs(register) + "\n")
  }

  override def toString(): String = {
    super.toString + " Print contents of register " + register + "\n"
  }
}

object OutInstruction {
  def apply(label: String, register: Int) =
    new OutInstruction(label, "out", register)
}