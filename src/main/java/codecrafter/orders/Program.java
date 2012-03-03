package codecrafter.orders;

/*
 * 
 * Basic sales tax is applicable at a rate of 10% on all goods, 
 * except books, food, and medical products that are exempt. 
 * Import duty is an additional sales tax applicable on all imported goods at a rate of 5%, with no exemptions. 
 * When I purchase items I receive a receipt which lists the name of all the items and their price (including tax), 
 * finishing with the total cost of the items, and the total amounts of sales taxes paid. 
 * The rounding rules for sales tax are that for a tax rate of n%, 
 * a shelf price of p contains (np/100 rounded up to the nearest 0.05) amount of sales tax.
 * 
 */

import static codecrafter.orders.Money.*;
import static codecrafter.orders.ProductOrigin.*;
import static codecrafter.orders.ProductType.*;
import static codecrafter.orders.TaxExemptionEligibilityCheck.*;
import static codecrafter.orders.TaxImportedEligibilityCheck.*;

public class Program {

  public static void main(String[] args) {

    // configure the system
    Product ddd = Product.create("Domain Driven Design", BOOK, LOCAL, dollars("69.99"));
    Product goos = Product.create("Growing Object Oriented Software", BOOK, IMPORTED, dollars("49.99"));
    Product house1 = Product.create("House M.D. Season 1", MOVIE, LOCAL, dollars("29.99"));
    Product house7 = Product.create("House M.D. Season 7", MOVIE, IMPORTED, dollars("34.50"));

    TaxMethod basicSalesTax = TaxMethod.create("BST", "0.10", exempt(BOOK, FOOD, MEDICAL));
    TaxMethod importDuty = TaxMethod.create("IMD", "0.05", imported());

    TaxMethodsPractice taxes = TaxMethodsPractice.create();
    taxes.add(basicSalesTax);
    taxes.add(importDuty);

    // purchase some items
    Order order = Order.create(taxes);
    order.add(ddd, 1);
    order.add(goos, 1);
    order.add(house1, 1);
    order.add(house7, 1);

    // print the receipt
    SimpleReceipt receipt = SimpleReceipt.create(System.out);
    order.print(receipt);
  }

}
