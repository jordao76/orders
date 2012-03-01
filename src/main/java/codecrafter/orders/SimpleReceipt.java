package codecrafter.orders;

import java.io.PrintStream;

public class SimpleReceipt implements Receipt {

  public static SimpleReceipt create(PrintStream out) {
    return new SimpleReceipt(out);
  }

  private final PrintStream out;
  
  private SimpleReceipt(PrintStream out) {
    this.out = out;
  }

  @Override
  public void start() {
    out.println("------------------ THIS IS YOUR ORDER ------------------");
  }

  @Override
  public void printProduct(ProductEntry entry) {
    out.printf("(%03d) %35s ----- %8s\n", entry.getQuantity(), entry.getProductName(), entry.getAmount());
  }

  @Override
  public void printTax(TaxEntry entry) {
    out.printf("(%3s) %35s ----- %8s\n", entry.getTaxName(), entry.getProductName(), entry.getAmount());
  }

  @Override
  public void printSubTotal(Money subTotal) {
    out.printf("%41s ----- %8s\n", "SUB-TOTAL", subTotal);
  }

  @Override
  public void printTaxTotal(Money taxTotal) {
    out.printf("%41s ----- %8s\n", "TAX TOTAL", taxTotal);
  }

  @Override
  public void printTotal(Money total) {
    out.printf("%41s ----- %8s\n", "TOTAL", total);
  }

  @Override
  public void end() {
    out.println("---------------- THANKS FOR CHOOSING US ----------------");
  }
 
}
