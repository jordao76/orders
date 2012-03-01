package codecrafter.orders;

import java.math.BigDecimal;

public class TaxEntry implements OrderEntry {

  public static TaxEntry create(ProductEntry taxedEntry, TaxMethod tax, Money amount) {
    return new TaxEntry(taxedEntry, tax, amount);
  }

  private final ProductEntry taxedEntry;
  private final TaxMethod tax;
  private final Money amount;

  private TaxEntry(ProductEntry taxedEntry, TaxMethod tax, Money amount) {
    this.taxedEntry = taxedEntry;
    this.tax = tax;
    this.amount = amount;
  }

  @Override
  public Money getAmount() {
    return amount;
  }

  @Override
  public Product getProduct() {
    return taxedEntry.getProduct();
  }

  public String getProductName() {
    return getProduct().getName();
  }
    
  public String getTaxName() {
    return tax.getName();
  }
  
  public BigDecimal getTaxRate() {
    return tax.getRate();
  }

}
