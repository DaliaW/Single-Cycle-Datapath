package Components;
//task1
public class DataMemory {
   //private static int[]RAM= new int[1024];
    private static int[] data;
    private static int maximumUsedAddress = -1;

    //constructs a new mem with specified size
    public DataMemory(int size){
        data = new int[size]; }


    //reads word from memory at specified address
    public static void MemRead(int MemRead,String address){
        int i = Integer.parseInt(address);
        if(MemRead == 1) {

            System.out.println(data[i/4]);

        }
        MemRead = 0;
    }
    //write a word to the memory at specified address
    public static void MemWrite(int MemWrite,String address,int valueToWrite){
        int i = Integer.parseInt(address);
        if(MemWrite == 1) {
            maximumUsedAddress = Math.max(maximumUsedAddress, i/4);
            data[i/4] = valueToWrite;

        }
        MemWrite = 0;


    }

    public String toString(){
        String s = "";
        for(int i=0 ; i<= maximumUsedAddress ; i++){
            s = s + String.format("%d: %d\n", i, data[i]);
        }
        return s;
    }

}
