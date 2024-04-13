package proiect;

import proiect.domain.Continent;
import proiect.domain.Country;
import proiect.service.WorldService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        WorldService worldService = new WorldService();
        Scanner scanner = new Scanner(System.in);

        while(true){
            System.out.println("Select Option:");
            System.out.println("1. Add Continent");
            System.out.println("2. Add Country");
            System.out.println("3. Add President");
            System.out.println("4. Add Rocket");
            System.out.println("5. Attach a Payload");
            System.out.println("6. Do Research");
            System.out.println("7. Explore");
            System.out.println("8. Exit");
            int option = scanner.nextInt();
            switch(option){
                case 1:
                    worldService.addContinent();
                    break;
                    case 2:
                        worldService.addCountry();
                        break;
                        case 3:
                            worldService.electPresident();
                            break;
                            case 4:
                    worldService.makeRocket();
                    break;
                case 5:
                    worldService.attachPayload();
                    break;
                case 6:
                    worldService.increaseResearch();
                    break;
                            case 7:
                                worldService.explore();
                                break;
                                case 8:
                                    System.exit(0);
            }
        }
    }
}
