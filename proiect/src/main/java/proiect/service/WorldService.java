package proiect.service;

import proiect.domain.Bomb;
import proiect.domain.Continent;
import proiect.domain.Country;
import proiect.domain.NuclearWarhead;

import java.util.Scanner;

public class WorldService {
    private Continent[] continents = new Continent[7];
    private int lastContinentIndex = 0;

    public void addContinent(Continent continent) {
        if (lastContinentIndex < continents.length) {
            continents[lastContinentIndex] = continent;
            System.out.println("Added continent at index " + lastContinentIndex);
            lastContinentIndex++;
        }
    }

    public void addCountry(Country country) {
        System.out.println("Specify Continent: ");
        Scanner scanner = new Scanner(System.in);
        for(int i=0;i<continents.length;i++){
            if(continents[i] == null) break;
            System.out.print(i);
            System.out.println(". " + continents[i].getName());
        }
        int c = scanner.nextInt();
        continents[c].addCountry(country);
    }

    public void explore() {
        Scanner scanner = new Scanner(System.in);
        for(int i=0;i<continents.length;i++){
            if(continents[i] == null) break;
            System.out.print(i);
            System.out.println(". " + continents[i].getName());
        }
        int c = scanner.nextInt();
        System.out.println(continents[c]);

    }

    public void makeRocket(){}

    public void attachPayload(){}

    public void electPresident(){}

}
