package Stages;

import Components.PC;
import Components.RegisterFile;


import java.util.ArrayList;

import static Controller.Controller.*;

public class InstructionDecode {
    public static String output,rs,rt,rd;
    static RegisterFile registerFile;

    /*
    This method takes in the instruction from InstFetch method as an input,
    decodes and outputs the ALUOp code, the first operand, the second operand.
    – Inputs: Instruction (32-bits), PC incremented by 4. (32-bits)
    – Outputs: Control Signals ( all 8 different Signals), ReadData1 (32-bits), ReadData2 (32-
      bits), PC incremented by 4. (32-bits).
     */

    public static void InstDecode(String instruction){
        registerFile = new RegisterFile();
       // System.out.println(s);
        ArrayList<String> a = new ArrayList<String>();
         opCode = instruction.substring(0,6);
        //ContUnit(opCode);
        rs = instruction.substring(6,11);
        rt = instruction.substring(11,16);
        a.add(opCode);
        a.add(rs);
        a.add(rt);
        a.add(instruction.substring(16,32));
        //System.out.println("my instruction");       System.out.println(a.toString());
        String Type = "";


        funct = instruction.substring(26,32);
        System.out.println("Decoding .......................................................... ");

        if(opCode.equals("000000")){
            //R Type
             rd = instruction.substring(16,21);



            //System.out.println("rs "+ReadData1+"rt "+ReadData2);

            Type = getType(rd);
            if(!rd.equals("000000")){
                //////
                ReadData1 = RegisterFile.registers[Integer.parseInt(rs,2)];
                ReadData2 = RegisterFile.registers[Integer.parseInt(rt,2)];

                output = "opCode:" + opCode + "|rs:"+rs+"|rt:"+rt+"|rd:"
                        +rd+"|0 "+"|funct:"+funct;
                System.out.println("Type of Register: "+Type);
                System.out.println(output);
                //System.out.println("RegWrite: "+ RegWrite +"\n"+"RegDst: "+RegDst);
            }else{
                //RegWrite = false;
                System.out.println("can not access register zero");
                //System.out.println("RegWrite: "+RegWrite);
            }

        }else if(opCode.equals("000010")){//J type (jump)
            //RegWrite = false;
            output = "opCode:" + opCode + "|immediate: "+a.get(1)+""+a.get(2);
            System.out.println(output);

        }else if(opCode.equals("100011")){//I type (lw,sw)
            //RegWrite = true;
            //System.out.println("RegWrite: "+RegWrite);
            output = "opCode:" + opCode + "|rs:"+rs+"|rt:"+rt+"|immediate "
                    +a.get(3);
            System.out.println("Sign Extend: "+SignExtend(a.get(3)));
            System.out.println(output);


        }else if(opCode.equals("000100")){//(BEQ) branch
            output = "opCode:" + opCode + "|rs:"+rs+"|rt:"+rt+"|immediate "
                    +a.get(3);
            System.out.println(output);


        }
        ContUnit(opCode);
        //RegWrite = false;
        System.out.println("finished decoding ................................................. ");
        Execute.Execute(ALUOp,ALUSrc,ReadData1,ReadData2,SignExtend,pc);

    }

    /*
    This method takes from the decoded instruction first 16-bits and outputs the
    extended value in 32-bits as follows: the left most significant bit is to be duplicated e.g
    1000000000000000 -> 1111111111111111000000000000000.
     */
    public static String SignExtend(String s){
        String firstBit = s.substring(0,1);
        String append = s;
        if(firstBit.equals("0")){
            SignExtend = "0000000000000000"+append;
            return SignExtend;
        }else{ SignExtend = "1111111111111111"+append;
            return SignExtend;}

    }

    /*
    This method takes in the OpCode code from InstDecode method as an input,
    changes the control signals according to the provided table outputs all 8 control unit signals
    to the ALU Control.
     */
    public static void ContUnit(String opCode){
        if(opCode.equals("000000")){ //R type
            RegWrite = 1;
            RegDst = 1;
            ALUOp = "10";
            System.out.println("WB controls: MemToReg: "+MemToReg+" ,RegWrite: "+RegWrite+"\n" +
                    "MEM controls: MemRead: "+MemRead+" ,MemWrite: "+MemWrite+" ,Branch: "+Branch+"\n" +
                    "EX controls: RegDest: "+RegDst+" ,ALUOp: "+ALUOp+" ,ALUSrc: "+ALUSrc);
        }
        else if(opCode.equals("100011")){ //lw/sw
            RegWrite = 1;
            MemRead = 1;
            System.out.println("WB controls: MemToReg: "+MemToReg+" ,RegWrite: "+RegWrite+"\n" +
                    "MEM controls: MemRead: "+MemRead+" ,MemWrite: "+MemWrite+" ,Branch: "+Branch+"\n" );

        }
        else if(opCode.equals("000100")){ //BEQ
            ALUOp ="01";
            System.out.println("WB controls: MemToReg: "+"Don't care"+" ,RegWrite: "+RegWrite+"\n" +
                    "MEM controls: MemRead: "+MemRead+" ,MemWrite: "+MemWrite+" ,Branch: "+Branch+"\n" );


        }
        else {// J type
            System.out.println("WB controls: MemToReg: "+MemToReg+" RegWrite: "+RegWrite+"\n" +
                    "MEM controls: MemRead: "+MemRead+" MemWrite: "+MemWrite+" Branch: "+Branch+"\n");
        }
        ALUControl();
        System.out.println("ALU OPERATION: "+ALUOperation);

    }


    //helper method to know the type of the registers in the instruction provided
    public static String getType(String s){
        String regType = "";
        int m = Integer.parseInt(s);
        if(m == 0){
            regType = "zero , Can not be modified";
            RegWrite = 0;
        }
        if(m == 1){
            regType = "reserved for use by the assembler";
            RegWrite = 0;
        }
        if(m>= 2 && m<=3){
            regType = "Function result";
        }
        if(m>=8 && m<=15){
            regType = "Temporary registers";
        }
        if(m>=16 && m<=23){
            regType = "Saved registers";
        }
        if(m>=24 && m<=25){
            regType = "Temporary registers";
        }

        return regType;
    }
///////////////////////////////////////// helper to know operation type to pass it to the ALU
    public static void ALUControl(){
        if(ALUOp.equals("00")){// lw/sw
            MemRead = 1;
            MemWrite = 1;
        }
        else if(ALUOp.equals("01")){//BEQ
            PCSrc = pc.getPc()+4;
        }
        else if(ALUOp.equals("10")){ //R-type instruction
            if(funct.equals("100000")){
                ALUOperation = "0010"; //ADD
                }
            else if(funct.equals("100010")) {
                ALUOperation = "0110"; //SUB
            }
            else if(funct.equals("100100")) {
                ALUOperation = "0000"; //AND
            }
            else if(funct.equals("100101")) {
                ALUOperation = "0001"; //OR
            }
            else if(funct.equals("101010")) {
                ALUOperation = "0111";  //SLT
            }
        }

    }



}
