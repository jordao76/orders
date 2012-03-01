package codecrafter.orders;

import java.util.*;

public class TaxingPractice {
  
  public static TaxingPractice create() {
    return new TaxingPractice();
  } 
  
  private final List<Tax> taxes = new ArrayList<Tax>();
  
  private TaxingPractice() {}
  
  public void add(Tax tax) {
    taxes.add(tax);
  }

  public Collection<TaxEntry> apply(ProductEntry entry) {
    List<TaxEntry> entries = new ArrayList<TaxEntry>();
    for (Tax tax : taxes) {
      if (tax.isEligible(entry)) {
        TaxEntry taxEntry = apply(tax, entry);
        entries.add(taxEntry);
      }
    }
    return entries;
  }

  private TaxEntry apply(Tax tax, ProductEntry entry) {
    Money taxAmount = tax.calculate(entry.getAmount());
    return TaxEntry.create(entry, tax, taxAmount);
  }
  
}
