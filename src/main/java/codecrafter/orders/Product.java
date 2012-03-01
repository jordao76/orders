package codecrafter.orders;

public class Product {

  public static Product create(String name, ProductType type, ProductOrigin origin, Money price) {
    return new Product(name, type, origin, price);
  }

  private final String name;
  private final ProductType type;
  private final ProductOrigin origin;
  private final Money price;
  
  private Product(String name, ProductType type, ProductOrigin origin, Money price) {
    this.name = name;
    this.type = type;
    this.origin = origin;
    this.price = price;
  }

  public String getName() {
    return name;
  }
  
  public ProductType getType() {
    return type;
  }
  
  public Money getPrice() {
    return price;
  }
  
  public boolean isImported() {
    return origin == ProductOrigin.IMPORTED;
  }
  
}
