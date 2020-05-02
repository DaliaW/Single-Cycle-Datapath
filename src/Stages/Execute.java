package Stages;

import Components.PC;
import Controller.Controller;

public class Execute {
    static String Name;
    public static int out;
    static int z;
    /*
    This method takes in the ALUOp from ContUnit method, the first operand and
    the second operand from InstDecode method as an input. computes and outputs the result.

     – Inputs: ALUOp (2-bits), ALUSrc. (1-bits), ReadData1 (32-bits), ReadData2 (32-bits),
       SignExtend (32-bits), PC incremented by 4. (32-bits).

     – Outputs: ALUresult (32-bits) , ZeroFlag (1-bit), BranchAddressResult (32-bits), Read-
       Data2 (32-bits), PC incremented by 4. (32-bits).*/
    public static void Execute(String ALUOp, int ALUSrc, int ReadData1, int ReadData2, String SignExtend, PC pc) {
        System.out.println("EXECUTING............................................");
        if(ALUOp.equals("01")){ //BEQ
            if(ReadData1 == ReadData2){
                int se = Integer.parseInt(SignExtend);
                int se1 = se << 2;
                //String s = Integer.toBinaryString(se1);
                pc.pc =pc.pc +4 + se1;
            }else{ //they're not equal then Ignore and increment pc by 4
                pc.pc = pc.getPc() +4;
            }
            System.out.println("FINISHED EXECUTING..............................................");
        }
        else if(ALUOp.equals("00")){
            Controller.MemWrite = 1;
            System.out.println("FINISHED EXECUTING..............................................");
        }
        else if (ALUOp.equals("10")) {
            if (Controller.ALUOperation.equals("0000")) {
                Name = "AND";
                ANDOp(ReadData1, ReadData2);

            } else if (Controller.ALUOperation.equals("0001")) {
                Name = "OR";
                OROp(ReadData1, ReadData2);

            } else if (Controller.ALUOperation.equals("0010")) {
                Name = "ADD";
                addOp(ReadData1, ReadData2);

            } else if (Controller.ALUOperation.equals("0110")) {
                Name = "SUB";
                subOp(ReadData1, ReadData2);

            } else if (Controller.ALUOperation.equals("0111")) {
                Name = "slt";
                sltOp(ReadData1, ReadData2);

            } else if (Controller.ALUOperation.equals("1100")) {
                Name = "NOR";
                NOR(ReadData1, ReadData2);

            } else
                System.out.println("invalid operator");

            System.out.println("Operation Name: "+Name+"\n"
                    +"ReadData1: "+ReadData1+"\n"+"ReadData2: "+ReadData2+"\n"
                    +"Output: "+out+"\n"+"Z-Flag Value: "+z+" ,PC: "+pc.getPc());

            System.out.println("FINISHED EXECUTING..............................................");
            WriteBack.WriteBack();
        }





    }
    private static void NOR(int operand1, int operand2) {
        out = ~(operand1 | operand2);
        if(out == 0) z = 1;
    }

    private static void sltOp(int operand1, int operand2) {
        out = (operand1 < operand2)? 1 : 0;
        if(out == 0) z = 1;
    }

    private static void subOp(int operand1, int operand2) {
        out = operand1 - operand2;
        if(out == 0) z = 1;
    }

    private static void addOp(int operand1, int operand2) {
        out = operand1 + operand2;
        if(out == 0) z = 1;
    }

    private static void OROp(int operand1, int operand2) {
        out = operand1 | operand2;
        if(out == 0) z = 1;
    }

    private static void ANDOp(int operand1, int operand2) {
        out =  operand1 & operand2;
        if(out == 0) z = 1;
    }

}


