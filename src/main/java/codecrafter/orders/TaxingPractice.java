package codecrafter.orders;

import java.util.*;

public class TaxingPractice {
  
  public static TaxingPractice create() {
    return new TaxingPractice();
  } 
  
  private final List<TaxMethod> taxes = new ArrayList<TaxMethod>();
  
  private TaxingPractice() {}
  
  public void add(TaxMethod tax) {
    taxes.add(tax);
  }

  public Collection<TaxEntry> apply(ProductEntry entry) {
    List<TaxEntry> entries = new ArrayList<TaxEntry>();
    for (TaxMethod tax : taxes) {
      if (tax.isEligible(entry)) {
        TaxEntry taxEntry = apply(tax, entry);
        entries.add(taxEntry);
      }
    }
    return entries;
  }

  private TaxEntry apply(TaxMethod tax, ProductEntry entry) {
    Money taxAmount = tax.calculate(entry.getAmount());
    return TaxEntry.create(entry, tax, taxAmount);
  }
  
}
