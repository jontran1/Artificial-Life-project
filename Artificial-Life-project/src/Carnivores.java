import java.security.SecureRandom;
import java.math.*;
public class Carnivores extends Animals {
    private static final SecureRandom randomNumber = new SecureRandom();
    private static String sex(){
        int rand = randomNumber.nextInt(2);
        if(rand %2 == 0){
            return "M";
        }
        else{
            return "F";
        }
    }
    //vision 10-25
    private static int generate_visibility(){
        return  10 + randomNumber.nextInt(25-9);
    }
    // death age 15- 20
    private static int generate_deathAge(){
        return 15 + randomNumber.nextInt(20-14);
    }
    // energy 50 - 70
    private static int generate_energy(){
        return 50 + randomNumber.nextInt(61-49);
    }


    Carnivores(int x, int y){
        super(sex(),generate_visibility(),generate_deathAge(),generate_energy(),x,y);
    }

    @Override
    public void action(Agent[][] area){
        if(!isActionTaken()) {
            checkStatus();
            if (isAlive()) {
                if(isFertile()){
                    lookForMate(area);
                }else if(isHungry()){
                    lookForFood(area);
                }else {
                    randomDirection(area);
                }
                decreaseEnergy(1);
                increaseAge();
                setActionTaken(true);
            }
        }
    }
    public void lookForFood(Agent[][] area){
        findFood(area);
    }

    public void lookForMate(Agent[][] area) {
        if(getSex() == "M"){
            if(findFemale(area)){
                goToMate(area);
            }
        }else if(findMale(area)){
            goToMate(area);

        }
    }

    public void findFood(Agent[][] area) {
        if(searchArea(area)){
            hunt(area);
            hunt(area);
        }else {
            randomDirection(area);
            randomDirection(area);
        }
    }

    @Override
    public boolean searchArea(Agent[][] area){
        int startpoint_X = getX() - getVisibility();
        int endpoint_Y = getX() - getVisibility();
        if(startpoint_X < 0){
            startpoint_X = 0;
        }
        if(endpoint_Y < 0){
            endpoint_Y = 0;
        }
        int min_distance = Integer.MAX_VALUE;
        for (int i = startpoint_X; i < 30 && i <= getX() + getVisibility(); i ++ ){
            for(int j = endpoint_Y; j < 30 && j <= getY() + getVisibility(); j++){
                if(area[i][j] instanceof Herbivores){
                    int distance = (int) Math.sqrt(Math.pow(getX()-i,2)+Math.pow(getY()-j,2));
                    if(distance < min_distance){
                        min_distance = distance;
                        Targets_position.setXY(i,j);
                    }
                }
            }
        }
        if(area[Targets_position.getX()][Targets_position.getY()] instanceof Herbivores){
            return true;
        }else
            return false;
    }

    @Override
    public void hunt(Agent[][] area){
        int new_Y, new_X;

        if(Targets_position.getX() < getX()){
            new_X = getX() - 1;
        }else if(Targets_position.getX() > getX()){
            new_X = getX() + 1;
        }else{
            new_X = getX();
        }
        if(Targets_position.getY() < getY()){
            new_Y = getY() - 1;
        }else if(Targets_position.getY() > getY()){
            new_Y = getY() + 1;
        }else{
            new_Y = getY();
        }
        if(area[new_X][new_Y] instanceof Herbivores){
            eat(area);
        }else if(area[new_X][new_Y] == null){
            area[new_X][new_Y] = area[getX()][getY()];
            area[getX()][getY()] = null;
            area[new_X][new_Y].setXY(new_X,new_Y);
        }else
            randomDirection(area);
    }

    public void eat(Agent [][] area){
        //The map scanning function will scan for dead and mark them as "x". Or i can change this function so that
        //The reference can point to null instead.
        area[Targets_position.getX()][Targets_position.getY()].death();
        increaseEnergy(15);
    }

    @Override
    public boolean findFemale(Agent[][] area) {
        int startpoint_X = getX() - getVisibility();
        int endpoint_Y = getY() - getVisibility();
        if(startpoint_X < 0){
            startpoint_X = 0;
        }
        if(endpoint_Y < 0){
            endpoint_Y = 0;
        }
        int min_distance = Integer.MAX_VALUE;
        for (int i = startpoint_X; i < 30 && i <= getX() + getVisibility(); i ++ ){
            for(int j = endpoint_Y; j < 30 && j <= getY() + getVisibility(); j ++){

                if(area[i][j] instanceof Carnivores){
                    Animals other_Animal = (Animals)area[i][j];
                    if(other_Animal.getSex() == "F" && other_Animal.isFertile()==true){
                        int distance = (int) Math.sqrt(Math.pow(getX()-i,2)+Math.pow(getY()-j,2));
                        if(distance < min_distance){
                            min_distance = distance;
                            Targets_position.setXY(i,j);
                        }
                    }
                }

            }
        }
        if(area[Targets_position.getX()][Targets_position.getY()] instanceof Carnivores){
            return true;
        }else
            return false;
    }

    @Override
    public boolean findMale(Agent[][] area) {
        int startpoint_X = getX() - getVisibility();
        int endpoint_Y = getY() - getVisibility();
        if (startpoint_X < 0) {
            startpoint_X = 0;
        }
        if (endpoint_Y < 0) {
            endpoint_Y = 0;
        }
        int min_distance = Integer.MAX_VALUE;
        for (int i = startpoint_X; i < 30 && i <= getX() + getVisibility(); i++) {
            for (int j = endpoint_Y; j < 30 && j <= getY() + getVisibility(); j++) {
                if (area[i][j] instanceof Carnivores) {
                    Animals other_Animal = (Animals) area[i][j];
                    if (other_Animal.getSex() == "M" && other_Animal.isFertile() == true) {
                        int distance = (int) Math.sqrt(Math.pow(getX() - i, 2) + Math.pow(getY() - j, 2));
                        if (distance < min_distance) {
                            min_distance = distance;
                            Targets_position.setXY(i, j);
                        }
                    }
                }
            }
        }
        if (area[Targets_position.getX()][Targets_position.getY()] instanceof Carnivores) {
            return true;
        } else
            return false;
    }

    public void goToMate(Agent[][] area) {
        int new_Y, new_X;

        if(Targets_position.getX() < getX()){
            new_X = getX() - 1;
        }else if(Targets_position.getX() > getX()){
            new_X = getX() + 1;
        }else{
            new_X = getX();
        }
        if(Targets_position.getY() < getY()){
            new_Y = getY() - 1;
        }else if(Targets_position.getY() > getY()){
            new_Y = getY() + 1;
        }else{
            new_Y = getY();
        }
        if(new_X == Targets_position.getX() && new_Y == Targets_position.getY()){
            reproduce(area);
        }else if(area[new_X][new_Y] == null){
            area[new_X][new_Y] = area[getX()][getY()];
            area[getX()][getY()] = null;
            area[new_X][new_Y].setXY(new_X,new_Y);
        }else
            randomDirection(area);
    }

    @Override
    public void reproduce(Agent[][]area){
        if(area[Targets_position.getX()][Targets_position.getY()] instanceof Carnivores) {
            if((getX() > 0 && getX() < 29) && (getY() > 0 && getY() < 29)){
                if( area[getX()+1][getY()] == null) {
                    area[getX()+1][getY()] = new Carnivores(getX()+1, getY());
                }
                else if( area[getX()-1][getY()] == null) {
                    area[getX()-1][getY()] = new Carnivores(getX()-1, getY());
                 }
                else if( area[getX()][getY()+1] == null) {
                    area[getX()][getY()+1] = new Carnivores(getX(), getY()+1);
                 }
                else if( area[getX()][getY()-1] == null) {
                    area[getX()][getY()-1] = new Carnivores(getX(), getY()-1);

                }
            }
        }
        Animals this_animal = (Animals) area[Targets_position.getX()][Targets_position.getY()];
        this_animal.decreaseEnergy(5);
        this_animal.setFertile(false);
        setFertile(false);
        decreaseEnergy(5);
    }
    @Override
    public void checkStatus(){
        if (getEnergy() < 0 || getAge() >= getDeathAge()){
            death();
            return;
        }
        if((getAge() >=3 && getAge() <= 10) && (getEnergy() >= 40)){
            setFertile(true);
        }else {
            setFertile(false);
        }
        if(getEnergy() < 40){
            setHungry(true);
        }else {
            setHungry(false);
        }
    }
    @Override
    public String showStatus(){
           return String.format("%nAge: " + getAge() + " Death Age: " + getDeathAge() + " Alive: " + isAlive() +
                " %nSex: " + getSex() + " Energy: " + getEnergy() + " Fertile: " + isFertile()) +" Visability: "+getVisibility()
                   +"%n X, and Y:" + getY() + ", " + getY();

    }
    @Override
    public String toString() {
        return " C ";
    }
}
