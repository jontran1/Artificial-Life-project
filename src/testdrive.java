import java.security.SecureRandom;
import java.util.Arrays;

class iutestdrive {
    public static void main(String[] args){
        Environment area = new Environment();
        area.getMap_and_Dead();
        System.out.println("Starting Area");
        //Highest simulation without without total death is 500
        int count = 1;
        while (!area.isCompletelyDead()){
            area.runSimulation();
            System.out.println("Simulation: " + count);
            count++;
        }
        //area.printPopulation();
    }



}
