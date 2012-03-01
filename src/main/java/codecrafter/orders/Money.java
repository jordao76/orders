package codecrafter.orders;

import java.math.*;

public class Money {

  public static Money dollars(String amount) {
    return new Money(new BigDecimal(amount));
  }
  
  private final BigDecimal amount;

  private Money(BigDecimal amount) { 
    this.amount = amount.setScale(2, RoundingMode.UP);
  }
  
  @Override public int hashCode() {
    return amount.hashCode();
  }
  
  @Override public boolean equals(Object other) {
    if (other == null) return false;
    return other instanceof Money && equals((Money)other);
  }
  
  public boolean equals(Money other) {
    return amount.equals(other.getAmount());
  }
  
  private BigDecimal getAmount() {
    return amount;
  }

  public Money add(Money other) {
    return new Money(amount.add(other.amount));
  }

  public Money multiply(int times) {
    return multiply(new BigDecimal(times));
  }
  
  public Money multiply(String factor) {
    return multiply(new BigDecimal(factor));
  }

  public Money multiply(BigDecimal factor) {
    return new Money(amount.multiply(factor));
  }

  @Override public String toString() {
    return "$" + amount;
  }

}
