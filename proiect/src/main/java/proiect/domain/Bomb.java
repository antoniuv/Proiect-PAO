package proiect.domain;

public class Bomb {
    private String type;
    private int yield;

    public Bomb(String type, int yield) {
        this.type = type;
        this.yield = yield;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getYield() {
        return yield;
    }

    public void setYield(int yield) {
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
