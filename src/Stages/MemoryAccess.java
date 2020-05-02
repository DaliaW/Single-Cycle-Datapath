package Stages;

import Components.DataMemory;

import static Controller.Controller.*;
import static Stages.InstructionDecode.rs;

public class MemoryAccess {
    /*
    This method takes in the Address which is the ALU result (offset is added
    to the value in the rs in the ALU). Plus the readdata2 value (this will be our write data in
    the memory). from InstDecode method, the result data from Stages.Execute method as an input.
    writes the result in the memory address and sets the corresponding flag for memory accessing.

    – Inputs: ALUresult (32-bits), ReadData2 (32-bits), SignExtend (32-bits), ZeroFlag (1-bit),
    BranchAddressResult (32-bits), MemWrite (1-bit), MemRead (1-bit), Branch (1-bit).

    – Outputs: ALUresult (32-bits) , ReadData2 (32-bits)).
     */

    public static void MemAccess(){
        System.out.println("MEMORY ACCESS.............................................");
        if(ALUSrc ==1 && MemWrite ==1)
            DataMemory.MemWrite(MemWrite,rs,Execute.out);
        else
            DataMemory.MemRead(MemRead,rs);

        System.out.println("ALUresult: "+Execute.out+"ReadData2: "+ReadData2);
        System.out.println("FINISHED MEMORY ACCESS....................................");
    }
}
