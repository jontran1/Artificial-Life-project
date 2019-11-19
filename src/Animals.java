public abstract class Animals extends Agent {

    private final String sex;
    private int energy;
    private final int visibility;
    private boolean hungry;
    private boolean fertile;
    protected TargetsPosition Targets_position = new TargetsPosition(0, 0);

    Animals(String sex, int visibility, int deathAge, int energy, int x, int y) {
        super(deathAge, x, y);
        this.energy = energy;
        this.sex = sex;
        this.visibility = visibility;
    }

    public String getSex() {
        return sex;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public int getEnergy() {
        return energy;
    }

    public int getVisibility() {
        return visibility;
    }

    public void increaseEnergy(int i) {
        setEnergy(getEnergy() + i);
    }

    public void decreaseEnergy(int i) {
        setEnergy(getEnergy() - i);
    }

    public boolean isHungry() {
        return hungry;
    }

    public void setHungry(boolean hungry) {
        this.hungry = hungry;
    }

    public void randomDirection(Agent[][] area) {
        if (getX() - 1 >= 0 && area[getX() - 1][getY()] == null) {
            area[getX() - 1][getY()] = area[getX()][getY()];
            area[getX()][getY()] = null;
            area[getX() - 1][getY()].setXY(getX() - 1, getY());
        } else if (getX() + 1 < 30 && area[getX() + 1][getY()] == null) {
            area[getX() + 1][getY()] = area[getX()][getY()];
            area[getX()][getY()] = null;
            area[getX() + 1][getY()].setXY(getX() + 1, getY());
        } else if (getY() + 1 < 30 && area[getX()][getY() + 1] == null) {
            area[getX()][getY() + 1] = area[getX()][getY()];
            area[getX()][getY()] = null;
            area[getX()][getY() + 1].setXY(getX(), getY() + 1);
        } else if (getY() - 1 >= 0 && area[getX()][getY() - 1] == null) {
            area[getX()][getY() - 1] = area[getX()][getY()];
            area[getX()][getY()] = null;
            area[getX()][getY() - 1].setXY(getX(), getY() - 1);
        }
    }

    public void setFertile(boolean fertile) {
        this.fertile = fertile;
    }

    public boolean isFertile() {
        return fertile;
    }

    //Made these abstract because A animal needs to find certain
    //foods or mates that are his or her classes.
    //Check status is abstract so that to include animals subclasses new
    //traits.
    public  abstract void hunt(Agent[][] area);

    public abstract boolean searchArea(Agent[][] area);

    public abstract boolean findFemale(Agent[][] area);

    public abstract boolean findMale(Agent[][] area);

    public abstract void reproduce(Agent[][] area);

    public abstract void checkStatus();

}