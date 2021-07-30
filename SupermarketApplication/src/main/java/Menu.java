import controller.Market;
import entity.User;
import types.UserType;

import java.util.Scanner;

public class Menu {

    Market market = new Market();
    Scanner scanner = new Scanner(System.in);

    public void start(){

        System.out.println("Welcome to Supermarket, enter your user type: \n 1.Buyer \n 2.Owner \n 3.Exit");
        String userChoice = scanner.nextLine();
        User activeUser = createUser(userChoice);

        if (activeUser == null) {
            handleExit();
        }
        market.setActiveUser(activeUser);

        showUserMenu(activeUser.getUserType());
        userChoice = scanner.nextLine();
        handleMenuChoice(activeUser.getUserType(), userChoice);

    }

    private void handleExit() {
        System.out.println("Enter A to exit or B to return to the main menu");
        if (scanner.nextLine().equals("A")) {
            System.exit(0);
        }
        start();
    }

    private User createUser(String userChoice) {
        switch (Integer.parseInt(userChoice)) {
            case 1:
                System.out.println("Enter your budget: ");
                double budget = Double.parseDouble(scanner.nextLine());
                return new User(UserType.BUYER, budget);
            case 2:
                return new User(UserType.OWNER);
            default:
                break;
        }
        return null;
    }

    private void handleMenuChoice(UserType userType, String userChoice) {
        switch (userType){
            case OWNER:
                handleOwnerChoice(userChoice);
                break;
            case BUYER:
                handleBuyerChoice(userChoice);
                break;
            default:
                start();
        }
    }

    private void handleBuyerChoice(String userChoice) {
        switch (userChoice) {
            case "1":
                market.sellProduct();
                break;
            case "2":
                market.viewProducts();
                break;
            case "3":
                handleExit();
                break;
            default:
                break;
        }
        showUserMenu(market.getActiveUser().getUserType());

    }

    private void handleOwnerChoice(String userChoice) {
        switch (userChoice) {
            case "1":
                market.addProduct();
                break;
            case "2":
                market.removeProduct();
                break;
            case "3":
                market.viewProducts();
                break;
            case "4":
                market.viewSalesHistory();
                break;
            case "5":
                handleExit();
                break;
            default:
                break;
        }
        showUserMenu(market.getActiveUser().getUserType());
    }

    private void showUserMenu(UserType userType) {
        switch (userType){
            case OWNER:
                System.out.println(getOwnerMenu());
                break;
            case BUYER:
                System.out.println(getBuyerMenu());
                break;
            default:
                start();
        }
        String userChoice = scanner.nextLine();
        handleMenuChoice(userType, userChoice);
    }

    private String getBuyerMenu() {
        return "\n Choose one option below: "
                + "\n1. Buy product"
                + "\n2. View all products"
                + "\n3. Exit";

    }

    private String getOwnerMenu() {
        return "\n Choose one option below:"
                + "\n1. Add product to the market"
                + "\n2. Remove product"
                + "\n3. View products"
                + "\n4. View sales history"
                + "\n5. Exit";

    }
}
