package codecrafter.orders;

import java.util.*;

import static codecrafter.orders.Money.dollars;

public class Order {
  
  public static Order create(TaxingPractice taxingPractice) {
    return new Order(taxingPractice);
  }
  
  private final TaxingPractice taxingPractice;
  private final List<ProductEntry> entries = new ArrayList<ProductEntry>();
  private final List<TaxEntry> taxEntries = new ArrayList<TaxEntry>();

  private Order(TaxingPractice taxingPractice) {
    this.taxingPractice = taxingPractice;
  }

  public void add(Product product, int quantity) {
    ProductEntry entry = ProductEntry.create(product, product.getPrice(), quantity);
    entries.add(entry);
    taxEntries.addAll(taxingPractice.apply(entry));
  }

  public Money getSubTotal() {
    return getTotal(entries);
  }

  public Money getTaxTotal() {
    return getTotal(taxEntries);
  }
  
  public Money getTotal() {
    return getSubTotal().add(getTaxTotal());
  }
  
  private Money getTotal(Iterable<? extends OrderEntry> entries) {
    Money total = dollars("0.00");
    for (OrderEntry entry : entries) {
      total = total.add(entry.getAmount());
    }
    return total;    
  }

  public void print(Receipt receipt) {
    receipt.start();
    for (ProductEntry entry : entries) receipt.printProduct(entry);
    for (TaxEntry entry : taxEntries) receipt.printTax(entry);
    receipt.printSubTotal(getSubTotal());
    receipt.printTaxTotal(getTaxTotal());
    receipt.printTotal(getTotal());
    receipt.end();
  }

}
