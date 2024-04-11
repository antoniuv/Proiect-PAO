package proiect.domain;

public class President extends Person{
    protected String Party;

    public President(String firstName, String lastName, int age, String party) {
        super(firstName, lastName, age);
        Party = party;
    }

    public String getParty() {
        return Party;
    }

    public void setParty(String party) {
        Party = party;
    }

    @Override
    public String toString() {
        return "President{" +
                "Party='" + Party + '\'' +
                ", age=" + age +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
