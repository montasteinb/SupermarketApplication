package controller;

import entity.Product;
import repository.MarketRepository;
import types.ProductType;
import entity.User;

import java.util.ArrayList;
import java.util.Scanner;

public class Market {

    private User user;
    private ArrayList<Product> products = new ArrayList<>();
    private ArrayList<Sale> salesHistory = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public void setActiveUser(User activeUser) {
        this.user = activeUser;
    }

    public void addProduct() {
        MarketRepository marketRepository = new MarketRepository();
        Product product = collectProductInfo();
        try{
            marketRepository.addProductToDB(product);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        products.add(product);
        showMessage("Product Added successfully");
    }

    public void removeProduct() {

        System.out.println("Enter product ID to remove");
        int productId = Integer.parseInt(scanner.nextLine());
        try {
            this.products.remove(productId);
            showMessage("Product Removed");
        }catch (IndexOutOfBoundsException ex) {
            System.out.println("Sorry, product not found");
        }
    }

    public void viewProducts() {
        System.out.println("ID  | \tNAME | \tPRICE | \tQUANTITY | \tTYPE");
        products.forEach(System.out::println);
    }

    public void viewSalesHistory() {
        System.out.println("NAME | \tQUANTITY | \tAMOUNT");
        for (Sale sale: salesHistory){
            Product product = products.get(sale.getProductId());
            System.out.println(product.getName() + "\t| " + sale.getQuantity()
                    + "\t| " + sale.getTotal());
        }
    }

    public User getActiveUser() {
        return this.user;
    }


    private void showMessage(String message) {
        System.out.println(message + " Successfully!");
    }

    private void showFailedMessage(String message) {
        System.out.println(message);

    }

    private Product collectProductInfo() {
        Product newProduct = new Product();
        try {
            System.out.println("***ADD PRODUCT***");
            System.out.println("Enter Product Name:");
            newProduct.setName(scanner.nextLine());
            System.out.println("Enter Product Price:");
            newProduct.setPrice(Double.parseDouble(scanner.nextLine()));
            System.out.println("Quantity:");
            newProduct.setQuantity(Integer.parseInt(scanner.nextLine()));
            System.out.println("Enter Product Type (VEGETABLES,\n" +
                    "    MEAT,\n" +
                    "    SEAFOOD,\n" +
                    "    FRUIT,\n" +
                    "    DAIRY,\n" +
                    "    GRAINS,\n" +
                    "    NUTS,\n" +
                    "    SEEDS,\n" +
                    "    SWEETS,\n" +
                    "    DRINKS):");
            newProduct.setProductType(ProductType.valueOf(scanner.nextLine().toUpperCase()));
            newProduct.setId(generateProductId());
        }catch(Exception exception) {
            exception.printStackTrace();
            collectProductInfo();
        }
        return newProduct;
    }

    private int generateProductId() {
        return (products.size() < 1) ?
                0 :
                products.get(products.size() - 1).getId() + 1;
    }

    public void sellProduct() {
        System.out.println("Enter ID for the product you want to buy:");
        int productId = scanner.nextInt();

        Product product = findProductById(productId);

        boolean userCanBuyProduct = false;
        try {
            userCanBuyProduct = userCanBuyProduct(product, user.getBudget());
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        if(userCanBuyProduct){
            product.setQuantity(product.getQuantity() - 1);
            addSalesHistory(product.getId(), 1, product.getPrice());
            updateUserBalance(user.getBudget() - product.getPrice());
            updateProduct(product);
            showMessage("Product Purchased");
            return;

        }
        showFailedMessage("Unable to complete product purchase!");
    }

    private void updateUserBalance(double newBalance) {
        this.user.setBudget(newBalance);
    }

    private boolean userCanBuyProduct(Product product, double budget) throws Exception {
        if(product == null) throw new Exception("Invalid product selection");
        if (budget < product.getPrice()) throw new Exception("You do not have enough money to buy the product!");
        if (product.getQuantity() < 1) throw new Exception("Not enough products left in the market");
        return true;
    }

    private void updateProduct(Product product) {
        products.set(product.getId(), product);

    }

    private void addSalesHistory(int productId, int quantity, double total) {
        this.salesHistory.add(new Sale(productId, total, quantity));

    }

    private Product findProductById(int productId) {
        for (Product product: this.products){
            if (product.getId() == productId){
                return product;
            }
        }
        return null;
    }


}
