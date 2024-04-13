package proiect.domain;

public class NuclearWarhead extends Rocket{
    private Bomb payload;
    private final String location;

    public NuclearWarhead(int range, double speed, String location) {
        super(range, speed);
        this.location = location;
    }

    public Bomb getPayload() {
        return payload;
    }

    public void attachPayload(Bomb payload) {
        this.payload = payload;
    }

    public String getLocation() {
        return location;
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
