package proiect.domain;

import java.util.Arrays;

public class Country {
    protected int id;
    protected String name;
    protected int surface;
    President president;
    protected int research_level = 0;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getResearchLevel() {
        return research_level;
    }

    public void setResearchLevel(int researchLevel) {
        this.research_level = researchLevel;
    }

    @Override
    public String toString() {
        return "Country{" +
                "name='" + name + '\'' +
                ", surface=" + surface +
                ", president=" + president +
                ", nuclearWarheads=" + Arrays.toString(nuclearWarheads) +
                '}' + '\n';
    }

    public void doResearch() {
        research_level++;
    }

    public void addNuke(NuclearWarhead nuclearWarhead) {
        if (nuclearWarheads == null) {
            nuclearWarheads = new NuclearWarhead[1];
            nuclearWarheads[0] = nuclearWarhead;
        }
        else {
            this.nuclearWarheads = Arrays.copyOf(nuclearWarheads, nuclearWarheads.length + 1);
            this.nuclearWarheads[nuclearWarheads.length - 1] = nuclearWarhead;
        }
    }

}
