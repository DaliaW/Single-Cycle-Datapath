package Stages;

import Components.PC;
import Controller.Controller;

public class InstructionFetch {

public static String s;
        static int pc1;



    /*This method takes in the instruction address from the program counter as an
        input, fetches and outputs the instruction.
         – Inputs: PC address. (32-bits)
         – Outputs: Instruction. (32-bits), PC incremented by 4. (32-bits)*/
    public static void InstFetch(int i) {
        s = Controller.instructionMemory.getInstruction((i)*4);
        System.out.println("Instruction Fetched: "+s);
         pc1= PC.pc +4;
        System.out.println("Next PC: "+pc1);
        InstructionDecode.InstDecode(s);

    }

    /*
    This method points to the next instruction to be executed. PC is incremented
    by 4 after each instruction is executed. A branch instruction alters the flow of control by
    modifying the PC.
     */
    public static int ProgCount() {
        return PC.pc+4;
    }

}