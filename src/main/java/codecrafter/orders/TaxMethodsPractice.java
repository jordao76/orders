package codecrafter.orders;

import java.util.*;

public class TaxMethodsPractice implements TaxingPractice {
  
  public static TaxMethodsPractice create() {
    return new TaxMethodsPractice();
  } 
  
  private final List<TaxMethod> taxes = new ArrayList<TaxMethod>();
  
  private TaxMethodsPractice() {}
  
  public void add(TaxMethod tax) {
    taxes.add(tax);
  }

  @Override
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
