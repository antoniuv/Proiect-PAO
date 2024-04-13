package proiect.domain;

public class Bomb {
    private String type;
    private double yield;

    public Bomb(String type, double yield) {
        this.type = type;
        this.yield = yield;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getYield() {
        return yield;
    }

    public void setYield(double yield) {
        this.yield = yield;
    }

    @Override
    public String toString() {
        return "Bomb{" +
                "type='" + type + '\'' +
                ", yield=" + yield +
                " tons of TNT }";
    }
}
