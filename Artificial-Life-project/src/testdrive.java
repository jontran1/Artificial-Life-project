import java.security.SecureRandom;
import java.util.Arrays;

public class testdrive {
    public static void main(String[] args){
        Environment area = new Environment();
        area.getMap_and_Dead();
        System.out.println("Starting Area");
        //Highest simulation without without total death is 500
        for(int i = 0 ; i < 100; i ++) {
            area.runSimulation();
            System.out.println("Simulation: " + i);
        }
        area.printPopulation();
    }



}
