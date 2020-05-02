package Stages;

import Components.RegisterFile;

public class WriteBack {
    /*
    This method takes in the destination register from InstDecode method, the
    result data from Stages.Execute method as an input. writes the result in the destination register
    and sets the corresponding flag for register writing.

    – Inputs: ALUresult (32-bits) , ReadData [from data memory] (32-bits)), MemToReg (1-
    bit), RegDst (1-bit).

    – Outputs: WriteData (32-bits).
     */
    public static void WriteBack(){
        System.out.println("WRITEBACK STAGE...........................................");
        int regDst = Integer.parseInt(InstructionDecode.rd,2);
        RegisterFile.RegWrite(regDst,Execute.out);
        System.out.println("WRITE DATA: "+RegisterFile.ReadData(regDst));
        System.out.println("FINISHED WRITEBACK STAGE...................................");
    }
}
