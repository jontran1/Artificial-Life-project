import java.security.SecureRandom;
import java.util.Arrays;

public class Environment {
    private static final SecureRandom randomNumber = new SecureRandom();
    private static int generateSpawn(){
        return randomNumber.nextInt(31);
    }
    private int starting_Carnivores = 20;
    private int starting_Herbivores = 20;

    Agent [][] area = new Agent[30][30];
    Environment(){

        for(int i = 0; i < 30; i++){
            for(int j = 0; j < 30; j++){
                //10 percent chance for plant growth on each block
                int rand = randomNumber.nextInt(10);
                if(rand ==0) {
                    area[i][j] = new Plant(i, j);
                }
            }
        }
        for(int i = 0; i < starting_Carnivores; i++){
            int X =  randomNumber.nextInt(30);
            int Y =  randomNumber.nextInt(30);

            while(area[X][Y] != null){
                X =  randomNumber.nextInt(30);
                Y =  randomNumber.nextInt(30);
            }
            area[X][Y] = new Carnivores(X,Y);
        }
        for(int i = 0; i < starting_Herbivores; i++){
            int X =  randomNumber.nextInt(30);
            int Y =  randomNumber.nextInt(30);
            while(area[X][Y] != null){
                X =  randomNumber.nextInt(30);
                Y =  randomNumber.nextInt(30);
            }
            area[X][Y] = new Herbivores(X,Y);
        }


    }
    public void runSimulation(){
        for(int i = 0; i < 30; i++){
            for(int j = 0; j < 30; j++){
                int rand = randomNumber.nextInt(100);
                //5 Percent chance for plant growth each block.
                if(area[i][j] == null && rand < 5){
                    area[i][j] = new Plant(i,j);
                }
                if(area[i][j] != null && area[i][j].isActionTaken()==false){
                    area[i][j].action(area);
                }
            }
        }
        getMap_and_Dead();
        resetAction();
        printPopulation();
    }

    private void resetAction(){
        for(int i = 0; i < 30; i++){
            for(int j = 0; j < 30; j++){
                if(area[i][j] != null){
                    area[i][j].setActionTaken(false);
                }
            }
        }
    }

    public void getMap_and_Dead(){
        for(int i = 0 ; i < 30 ; i ++){
            for(int j = 0; j < 30; j++){
                if(area[i][j] == null){
                    System.out.print(" _ ");
                }else{
                    if(area[i][j].isAlive() == true) {
                        System.out.print(area[i][j]);
                    }else{
                        area[i][j] = null;
                        System.out.print(" x ");
                    }
                }
            }
            System.out.println();
         }
         System.out.println();
    }
    public void printPopulation(){
        int total_PlantLife = 0;
        int total_Carnivores = 0;
        int total_Herbivores = 0;
        for(int i = 0 ; i < 30; i++){
            for(int j = 0; j < 30; j++){
                if(area[i][j] != null) {
                    if (area[i][j] instanceof Plant) {
                        total_PlantLife++;
                    } else if (area[i][j] instanceof Carnivores) {
                        total_Carnivores++;
                    }else if(area[i][j] instanceof Herbivores){
                        total_Herbivores ++;
                    }
                }
            }
        }
        System.out.printf("%nTotal Plants: %d, %nTotal Carnivores: %d, %nTotal Herbivores: %d%n",total_PlantLife,total_Carnivores,total_Herbivores);
    }

}
