import java.security.SecureRandom;

public class Plant extends Agent {
    private static final SecureRandom randomNumber = new SecureRandom();
    private final int spwanArea = 4;

    // death age 10-15
    private static int deathAge(){
        return 5 + randomNumber.nextInt(15-4);
    }
    Plant(int x, int y){
        super(deathAge(),x,y);
    }
    @Override
    public void action(Agent[][] area){
        if(!isActionTaken()){
            checkStatus();
            if(isAlive()){

            }
            increaseAge();
            setActionTaken(true);
        }
    }

    private void reproduce(Agent[][] area){
        //Originally I was going to have it so that the plants would grow next to each other.
        //I am keeping the reproduce function in agent because plants should reproduce near by
        //other plants.
        //However the for this assignment plants will randomly spawn on the map.
        int startpoint_X = getX() - spwanArea;
        int endpoint_Y = getY() - spwanArea;
        if(startpoint_X < 0){
            startpoint_X = 0;
        }
        if(endpoint_Y < 0){
            endpoint_Y = 0;
        }
        for (int i = startpoint_X; (i < 30) && (i <= getX() + spwanArea); i ++ ){
            for(int j = endpoint_Y; j < 30 && (j <= getY() + spwanArea); j++){
                int rand = randomNumber.nextInt(100);
                    if(rand == 0) {
                        if (area[i][j] == null) {
                            area[i][j] = new Plant(i, j);
                        }
                    }
            }
        }
    }

    private void checkStatus(){
        if(getAge() >= getDeathAge())
            death();
    }

    public String showStatus(){
        return String.format("Plant: Alive: "+ isAlive());
    }
    @Override
    public String toString(){
        return " P ";
    }
}
