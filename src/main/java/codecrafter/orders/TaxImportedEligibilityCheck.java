package codecrafter.orders;

public class TaxImportedEligibilityCheck implements TaxEligibilityCheck {

  public static TaxImportedEligibilityCheck imported() {
    return new TaxImportedEligibilityCheck();
  }
  
  private TaxImportedEligibilityCheck() {}
  
  @Override
  public boolean isEligible(OrderEntry entry) {
    return entry.getProduct().isImported();
  }

}
