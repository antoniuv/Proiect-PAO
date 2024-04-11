package proiect.domain;

public class NuclearWarhead extends Rocket{
    Bomb payload;

    public NuclearWarhead(int range, double speed, Bomb payload) {
        super(range, speed);
        this.payload = payload;
    }

    public Bomb getPayload() {
        return payload;
    }

    public void setPayload(Bomb payload) {
        this.payload = payload;
    }

    @Override
    public String toString() {
        return "NuclearWarhead{" +
                "payload=" + payload +
                ", range=" + range +
                ", speed=" + speed +
                '}';
    }
}
