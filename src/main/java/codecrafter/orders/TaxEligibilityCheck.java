package codecrafter.orders;

public interface TaxEligibilityCheck {
  boolean isEligible(OrderEntry entry);
}
