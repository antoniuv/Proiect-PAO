package proiect.domain;

import java.util.Arrays;

public class Country {
    protected String name;
    protected int surface;
    President president;
    NuclearWarhead[] nuclearWarheads;

    public Country(String name,  int surface) {
        this.name = name;
        this.surface = surface;
    }

    public Country(String name, NuclearWarhead[] nuclearWarheads, President president, int surface) {
        this.name = name;
        this.nuclearWarheads = nuclearWarheads;
        this.president = president;
        this.surface = surface;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public NuclearWarhead[] getNuclearWarheads() {
        return nuclearWarheads;
    }

    public void setNuclearWarheads(NuclearWarhead[] nuclearWarheads) {
        this.nuclearWarheads = nuclearWarheads;
    }

    public President getPresident() {
        return president;
    }

    public void setPresident(President president) {
        this.president = president;
    }

    public int getSurface() {
        return surface;
    }

    public void setSurface(int surface) {
        this.surface = surface;
    }

    @Override
    public String toString() {
        return "Country{" +
                "name='" + name + '\'' +
                ", surface=" + surface +
                ", president=" + president +
                ", nuclearWarheads=" + Arrays.toString(nuclearWarheads) +
                '}';
    }

    public void addNuke(NuclearWarhead nuclearWarhead) {
        this.nuclearWarheads = Arrays.copyOf(nuclearWarheads, nuclearWarheads.length + 1);
        this.nuclearWarheads[nuclearWarheads.length - 1] = nuclearWarhead;
    }

}
