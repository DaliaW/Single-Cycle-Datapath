package Components;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


//task3
public class InstructionMemory {

    public static List<String> instructions = Collections.emptyList(); //program
    static List<String> inst = Collections.emptyList(); //instructions

    int numOfInst;

    public InstructionMemory(int size){
        instructions = Arrays.asList(new String[size]);
    }

    //gets instruction at the index given using byte addressing
    public String getInstruction(int Index){
        return instructions.get(Index / 4);
    }

    //sets instruction at the index given using byte addressing
    public void setInstruction(int Index , String ValueToSet){
        instructions.set(Index / 4, ValueToSet);
        numOfInst++;
    }

    public int size() {
        return instructions.size();
    }
    public void Load(){
        try {
            String file = "Instruction.txt";
            inst = Files.readAllLines(Paths.get(file), StandardCharsets.UTF_8);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        for (int i = 0; i < inst.size(); i++) {
            inst.get(i).split(" ");
        }

        try {
            String file = "program.txt";
            instructions =  Files.readAllLines(Paths.get(file), StandardCharsets.UTF_8);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        for (int i = 0; i < instructions.size(); i++) {
            instructions.get(i).split(" ");
        }
        System.out.println("instruction memory: ");

        System.out.println(instructions.toString());

    }

}
