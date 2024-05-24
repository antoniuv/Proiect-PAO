package proiect.domain;

public class Rocket {
    private int id;
    protected int range;
    protected double speed;

    public Rocket(int range, double speed) {
        this.range = range;
        this.speed = speed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    @Override
    public String toString() {
        return "Rocket{" +
                "range=" + range +
                ", speed=" + speed +
                '}';
    }
}
