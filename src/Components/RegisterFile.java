package Components;

public class RegisterFile {
    public static final int[] registers = new int[32];
    static int max;
    static int min;
    static int range;

    public RegisterFile(){
        //registers = new String[32];
        //Random r = new Random();
        max = 99;
        min = 1;
        range = max - min +1;
        for (int i = 0 ; i<32 ; i++){
            registers[i]=(int) (Math.random()*range);
        }
    }
    public static void RegWrite(int index,int Data){
        registers[index] = Data;
    }
    public static int ReadData(int i){
        return registers[i];
    }

}
