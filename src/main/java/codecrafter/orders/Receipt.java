package codecrafter.orders;

public interface Receipt {
  void start();
  void printProduct(ProductEntry entry);
  void printTax(TaxEntry entry);
  void printSubTotal(Money subTotal);
  void printTaxTotal(Money taxTotal);
  void printTotal(Money total);
  void end();
}
