package codecrafter.orders;

import java.math.BigDecimal;

public class Tax {

  public static Tax create(String name, String rate) {
    return new Tax(name, new BigDecimal(rate), null);
  }

  public static Tax create(String name, String rate, TaxEligibilityCheck eligibilityCheck) {
    return new Tax(name, new BigDecimal(rate), eligibilityCheck);
  }
  
  private final String name;
  private final BigDecimal rate;
  private final TaxEligibilityCheck eligibilityCheck;
  
  private Tax(String name, BigDecimal rate, TaxEligibilityCheck eligibilityCheck) {
    this.name = name;
    this.rate = rate;
    this.eligibilityCheck = eligibilityCheck;
  }
  
  public String getName() {
    return name;
  }
  
  public BigDecimal getRate() {
    return rate;
  }
  
  public boolean isEligible(OrderEntry entry) {
    return eligibilityCheck == null || eligibilityCheck.isEligible(entry);
  }

  public Money calculate(Money amount) {
    return amount.multiply(rate);
  }
  
}
