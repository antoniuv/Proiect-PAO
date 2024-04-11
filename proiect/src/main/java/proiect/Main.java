package proiect;

import proiect.domain.Continent;
import proiect.domain.Country;
import proiect.service.WorldService;

public class Main {
    public static void main(String[] args) {
        WorldService worldService = new WorldService();
        Continent continent1 = new Continent("Europe");
        worldService.addContinent(continent1);

        Continent continent2 = new Continent("Asia");
        worldService.addContinent(continent2);

        Country France = new Country("France", 100000);

        worldService.addCountry(France);

        worldService.explore();

    }
}
