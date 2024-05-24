package proiect.service;

import proiect.domain.*;
import proiect.repository.*;
import proiect.config.SetupDataUsingStatement;

import java.util.Scanner;

public class WorldService {
    private Continent[] continents = new Continent[7];
    private int lastContinentIndex = 0;


    public WorldService() {
        SetupDataUsingStatement setupDataUsingStatement = new SetupDataUsingStatement();
        setupDataUsingStatement.Tables();
        ContinentRepository continentRepository = new ContinentRepository();
        CountryRepository countryRepository = new CountryRepository();
        continents = continentRepository.getAllContinents();
        for (int i = 0; i < continents.length; i++) {
            if (continents[i] == null) break;
            int continentId = continentRepository.getContinentId(continents[i].getName());
            Country[] countries = countryRepository.getCountries(continentId);
            continents[i].setCountries(countries);
            lastContinentIndex++;
        }

    }

    public void addContinent() {
        System.out.println("Specify name:");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        Continent newContinent = new Continent(name);
        if (lastContinentIndex < continents.length) {
            continents[lastContinentIndex] = newContinent;
            ContinentRepository continentRepository = new ContinentRepository();
            continentRepository.insert(name);
            System.out.println("Added continent: " + name);
            lastContinentIndex++;
        }
    }

    public void addCountry() {
        System.out.println("Specify Continent: ");
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < continents.length; i++) {
            if (continents[i] == null) break;
            System.out.print(i+1);
            System.out.println(". " + continents[i].getName());
        }
        int c = scanner.nextInt();
        c--;
        String continentName = continents[c].getName();
        System.out.println("Specify Name: ");
        String name = scanner.next();
        System.out.println("Specify Surface: ");
        int surface = scanner.nextInt();

        ContinentRepository continentRepository = new ContinentRepository();
        CountryRepository countryRepository = new CountryRepository();

        int continentId = continentRepository.getContinentId(continentName);
        System.out.println(continentId);
        countryRepository.insert(name,surface,0, continentId);

        Country country = new Country(name, surface);
        continents[c].addCountry(country);
    }

    public void explore() {
        Scanner scanner = new Scanner(System.in);
        boolean cond = true;
        while (cond) {
            for (int i = 0; i < continents.length; i++) {
                if (continents[i] == null) break;
                System.out.print(i + 1);
                System.out.println(". " + continents[i].getName());
            }
            System.out.print(lastContinentIndex + 1);
            System.out.println(". Exit");
            int c = scanner.nextInt();
            c--;
            if (c == lastContinentIndex) cond = false;
            else System.out.println(continents[c]);
        }
    }

    public void makeRocket() {
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < continents.length; i++) {
            if (continents[i] == null) break;
            System.out.print(i+1);
            System.out.println(". " + continents[i].getName());
        }
        int c = scanner.nextInt();
        c--;
        if (continents[c].getCountries() == null) {
            System.out.println("No countries found");
            return;
        }
        Country[] countries = continents[c].getCountries();
        for (int i = 0; i < countries.length; i++) {
            System.out.print(i+1);
            System.out.println(". " + countries[i].getName());
        }
        int co = scanner.nextInt();
        co--;
        if(countries[co].getResearchLevel() < 2){
            System.out.println("Research level is too low for this action");
            return;
        }
        System.out.println("Specify range:");
        int range = scanner.nextInt();
        System.out.println("Specify Speed:");
        int speed = scanner.nextInt();
        System.out.println("Specify Location:");
        String location = scanner.next();

        CountryRepository countryRepository = new CountryRepository();
        int countryId = countryRepository.getCountryId(countries[co].getName());

        NuclearWarheadRepository nuclearWarheadRepository = new NuclearWarheadRepository();
        nuclearWarheadRepository.insert(countryId, range, speed, location);

        int recentNukeId = nuclearWarheadRepository.getIdOfMostRecentNuke();

        NuclearWarhead nuke = new NuclearWarhead(range, speed, location);
        nuke.setId(recentNukeId);
        countries[co].addNuke(nuke);
    }

    public void attachPayload() {
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < continents.length; i++) {
            if (continents[i] == null) break;
            System.out.print(i+1);
            System.out.println(". " + continents[i].getName());
        }
        int c = scanner.nextInt();
        c--;
        if (continents[c].getCountries() == null) {
            System.out.println("No countries found");
            return;
        }
        Country[] countries = continents[c].getCountries();
        for (int i = 0; i < countries.length; i++) {
            System.out.print(i+1);
            System.out.println(". " + countries[i].getName());
        }
        int co = scanner.nextInt();
        co--;
        if(countries[co].getResearchLevel() < 4){
            System.out.println("Research level is too low for this action");
            return;
        }
        NuclearWarhead[] nukes = countries[co].getNuclearWarheads();
        for (int i = 0; i < nukes.length; i++) {
            System.out.print(i+1);
            System.out.println(". " + nukes[i].toString());
        }
        int n = scanner.nextInt();
        n--;
        System.out.println("Specify yield(tonnes of TNT):");
        double yield = scanner.nextDouble();
        System.out.println("Specify type:");
        String type = scanner.next();

        int nuke_id = nukes[n].getId();

        BombRepository bombRepository = new BombRepository();
        bombRepository.insert(nuke_id, type, yield);

        Bomb payload = new Bomb(type,yield);
        nukes[n].attachPayload(payload);
    }

    public void increaseResearch(){
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < continents.length; i++) {
            if (continents[i] == null) break;
            System.out.print(i+1);
            System.out.println(". " + continents[i].getName());
        }
        int c = scanner.nextInt();
        c--;
        if (continents[c].getCountries() == null) {
            System.out.println("No countries found");
            return;
        }
        Country[] countries = continents[c].getCountries();
        for (int i = 0; i < countries.length; i++) {
            System.out.print(i+1);
            System.out.println(". " + countries[i].getName());
        }
        int co = scanner.nextInt();
        co--;

        CountryRepository countryRepository = new CountryRepository();
        countryRepository.IncreaseResearchLevel(countries[co].getName());

        countries[co].doResearch();
        System.out.println("Increased research level");
    }

    public void electPresident() {
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < continents.length; i++) {
            if (continents[i] == null) break;
            System.out.print(i+1);
            System.out.println(". " + continents[i].getName());
        }
        int c = scanner.nextInt();
        c--;
        if (continents[c].getCountries() == null) {
            System.out.println("No countries found");
            return;
        }
        Country[] countries = continents[c].getCountries();
        for (int i = 0; i < countries.length; i++) {
            System.out.print(i+1);
            System.out.println(". " + countries[i].getName());
        }
        int co = scanner.nextInt();
        co--;
        System.out.println("Specify Name:");
        String firstName = scanner.next();
        String lastName = scanner.next();
        System.out.println("Specify Age:");
        int age = scanner.nextInt();
        System.out.println("Specify Party:");
        String party = scanner.next();

        PresidentRepository presidentRepository = new PresidentRepository();
        presidentRepository.insert(firstName,lastName,age,party);

        int presidentId = presidentRepository.getPresidentId(firstName,lastName);

        CountryRepository countryRepository = new CountryRepository();
        countryRepository.updatePresident(countries[co].getName(), presidentId);

        President president = new President(firstName, lastName, age, party);
        countries[co].setPresident(president);

    }
}
