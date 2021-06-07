/*
 *  UCF COP3330 Summer 2021 Assignment 1 Solution
 *  Copyright 2021 Jamar Jackson
 */

package base;

import java.util.Scanner;

/*
Exercise 20 - Multistate Sales Tax Calculator

More complex programs may have decisions nested in other decisions, so that when one decision is made, additional decisions must be made.

Create a tax calculator that handles multiple states and multiple counties within each state. The program prompts the user for the order amount and the state where the order will be shipped.

    Wisconsin residents must be changed 5% sales tax with an additional county-level charge. For Wisconsin residents, prompt for the county of residence.
        For Eau Claire county residents, add an additional 0.005 tax.
        For Dunn county residents, add an additional 0.004 tax.
    Illinois residents must be charged 8% sales tax with no additional county-level charge.
    All other states are not charged tax.

The program then displays the tax and the total for Wisconsin and Illinois residents but just the total for everyone else.
Example Output

What is the order amount? 10
What state do you live in? Wisconsin
What county do you live in? Dane
The tax is $0.50.
The total is $10.50.

Constraints

    Ensure that all money is rounded up to the nearest cent.
    Use a single output statement at the end of the program to display the program results.

Challenges

    Add support for your state and county.
    Allow the user to enter a state abbreviation and county name in upper, lower, or mixed case.
    Allow the user to also enter the state’s full name in upper, lower, or mixed case.
    Implement the program using data structures to avoid nested if statements.

 */
public class App {
    static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        App myApp = new App();
        String order = myApp.orderAmount();
        String state = myApp.stateResidence();
        double amount = Double.parseDouble(order);
        String county = myApp.countyResidence(state);
        double tax = myApp.anyTax(amount, state, county);
        double total = myApp.totalCost(amount, tax);
        String outputString = myApp.generateOutputString(total);
        myApp.printOutput(outputString);
        }

    public void printOutput(String outputString) {
        System.out.println(outputString);
    }

    public String orderAmount() {
        System.out.println("What is the order amount? ");
        return in.nextLine();
    }

    public String stateResidence() {
        System.out.println("What state do you live in? ");
        return in.nextLine();
    }

    public String countyResidence(String state) {
        if(state.equals("Wisconsin")){
            System.out.println("What county do you live in? ");
        }
        return in.nextLine();
    }

    public double anyTax(double amount, String state, String county) {
        double wisTax = 5.0 / 100;
        double eauClaire = .005;
        double dunn = .004;
        double illinois = 8.0 / 100;
        double taxes = 0;
        double temp;
        if(state.equals("Wisconsin")){
            temp = amount * wisTax;
            taxes += temp;
            if(county.equals("Eau Claire")) {
                temp = amount * eauClaire;
                taxes += temp;
            }else if(county.equals("Dunn")) {
                temp = amount * dunn;
                taxes += temp;
            }
        }else if(state.equals("Illinois")){
            temp = amount * illinois;
            taxes += temp;
        }
        double money = Math.round(taxes * 100.0) / 100.0;
        System.out.println("The tax is $" + money + ".");
        return money;
    }

    public double totalCost(double order, double tax) {
        return order + tax;
    }

    public String generateOutputString(double total) {
        return "The total is $" + total + ".";
    }
}
