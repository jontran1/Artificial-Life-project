abstract public class Agent {
    private int age;
    private final int deathAge;
    private boolean alive;
    private boolean actionTaken;
    private int x;
    private int y;

    Agent(int deathAge, int x, int y){
        this.deathAge = deathAge;
        this.age = 1;
        this.x = x;
        this.y = y;
        alive = true;
    }
    public int getDeathAge(){
        return deathAge;
    }
    public boolean isAlive() {
        return alive;
    }
    public void increaseAge(){
        age += 1;
    }
    public int getAge(){
        return age;
    }
    public void death(){
        alive = false;
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }

    public void setXY(int x, int y){
        this.x = x;
        this.y = y;
    }
    public boolean isActionTaken() {
        return actionTaken;
    }
    public void setActionTaken(boolean actionTaken) {
        this.actionTaken = actionTaken;
    }


    //Every Agent on the map should have an action even plants.
    public abstract void action(Agent[][] area);
    public abstract String showStatus();
}
