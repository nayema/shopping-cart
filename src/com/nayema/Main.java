package com.nayema;

import java.text.NumberFormat;
import java.util.Scanner;

public class Main {

    final static double TAX_RATE = 0.15;
    static int totalItems;
    static Item[] items;
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("Welcome to Nayema's Shopping Centre");
        totalItems = getNumberOfItems("Number of Items in Shopping Cart: ");
        items = new Item[totalItems];

        for (int i = 0; i < totalItems; i++) {
            getItemDetails(i);
        }
        displayCart();
    }

    private static void getItemDetails(int index) {
        String name = getItemNames("Name of Item #" + (index + 1) + ": ");
        double price = getItemPrice("Price of Item #" + (index + 1) + "($) : ");
        int quantity = getItemQuantity("Qty of Item #" + (index + 1) + ": ");

        items[index] = new Item(name, price, quantity);
    }

    private static void displayCart() {
        System.out.println("####################################");
        System.out.println("Shopping Cart Details: ");
        System.out.println("####################################");

        for (int i = 0; i < totalItems; i++) {
            showLineItems(i);
            System.out.println("");
        }

        System.out.println("####################################");
        double subTotal = calculateSubtotal();
        double tax = subTotal * TAX_RATE;
        double total = subTotal + tax;

        NumberFormat currency = NumberFormat.getCurrencyInstance();
        NumberFormat percent = NumberFormat.getPercentInstance();

        System.out.println("Total items: " + getTotalItems());
        System.out.println("Subtotal: " + currency.format(subTotal));
        System.out.println("Sales tax at " + percent.format(TAX_RATE) + ": " + currency.format(tax));
        System.out.println("Total Cost: " + currency.format(total));
        System.out.println("Thank you for shopping with us!");
    }

    private static int getTotalItems() {
        int sum = 0;
        for (int i = 0; i < totalItems; i++) {
            sum += items[i].getQuantity();
        }

        return sum;
    }

    private static double calculateSubtotal() {
        double total = 0;

        for (int i = 0; i < totalItems; i++) {
            total += items[i].getPrice() * items[i].getQuantity();
        }
        return total;
    }

    private static void showLineItems(int i) {
        String quantityText = items[i].getQuantity() + "\t--\t" + items[i];
        String priceText = items[i].getPrice() + " : " + items[i].getPrice() * items[i].getQuantity();

        System.out.format(quantityText, priceText);
    }

    private static double getItemPrice(String m) {
        System.out.print(m);
        return scanner.nextDouble();
    }

    private static String getItemNames(String m) {
        System.out.print(m);
        return scanner.nextLine();
    }

    private static int getItemQuantity(String m) {
        System.out.print(m);
        return scanner.nextInt();
    }

    private static int getNumberOfItems(String m) {
        System.out.print(m);
        return scanner.nextInt();
    }
}