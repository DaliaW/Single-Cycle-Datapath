package Controller;

import Components.DataMemory;
import Components.InstructionMemory;
import Components.PC;
import Components.RegisterFile;
import Stages.*;

import java.util.Arrays;


public class Controller {
    public static RegisterFile registerFile;
    public static int Branch;
    DataMemory dataMemory;

    Execute execute;
    InstructionFetch instructionFetch;
    InstructionDecode instructionDecode;
    MemoryAccess memoryAccess;
    WriteBack writeBack;

    public static int RegDst;
    public static int RegWrite ;
    public static String ALUOp;
    public static int ALUSrc;
    public static int PCSrc;
    public static int MemRead;
    public static int MemWrite;
    public static int MemToReg;
    public static int ReadData1;
    public static int ReadData2;

    public static String funct;
    public static String SignExtend;
    public static String opCode;
    public static String ALUOperation;

    public static InstructionMemory instructionMemory;
    public static PC pc;


    public Controller(){
        //instructionMemory = new InstructionMemory(1000);
        registerFile = new RegisterFile();

        dataMemory = new DataMemory(1024);
        ALUSrc = 0; ALUOp = ""; RegWrite = 0; RegDst = 0; PCSrc = 0;
        MemRead=0;MemToReg=0;MemWrite = 0;Branch = 0;
    }


    public static void main(String[] args) {
        pc = new PC();
        instructionMemory = new InstructionMemory(1024);
        instructionMemory.Load();

        for(int i = 0 ; i < instructionMemory.size();i++) {
            System.out.println("Register File: " + Arrays.toString(registerFile.registers)+"\n");
            InstructionFetch.InstFetch(i);
            System.out.println("Clock Cycle: "+i);
        }

    }
}
