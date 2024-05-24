package proiect.domain;

import java.util.Arrays;

public class Continent {
    protected String name;
    protected Country[] countries;


    public Continent(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Country[] getCountries() {
        return countries;
    }

    public void setCountries(Country[] countries) {
        this.countries = countries;
    }

    public void addCountry(Country country) {
        if (countries == null) {
            countries = new Country[1];
            countries[0] = country;
        }
        else {
            this.countries = Arrays.copyOf(countries, countries.length + 1);
            this.countries[countries.length - 1] = country;
        }
    }

    @Override
    public String toString() {
        return name + '\n'
                + Arrays.toString(countries);
    }
}
