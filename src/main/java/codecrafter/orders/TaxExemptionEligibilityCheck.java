package codecrafter.orders;

import java.util.*;

public class TaxExemptionEligibilityCheck implements TaxEligibilityCheck {

  public static TaxExemptionEligibilityCheck exempt(ProductType... types) {
    return new TaxExemptionEligibilityCheck(types);
  }
  
  private final List<ProductType> types;
  
  private TaxExemptionEligibilityCheck(ProductType... types) {
    this.types = Arrays.asList(types);
  }
  
  @Override
  public boolean isEligible(OrderEntry entry) {
    return !types.contains(entry.getProduct().getType());
  }

}
