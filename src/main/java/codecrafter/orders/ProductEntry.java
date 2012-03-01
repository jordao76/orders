package codecrafter.orders;

public class ProductEntry implements OrderEntry {

  public static ProductEntry create(Product product, Money price, int quantity) {
    return new ProductEntry(product, price, quantity);
  }

  private final Money price;
  private final Product product;
  private final int quantity;

  private ProductEntry(Product product, Money price, int quantity) {
    this.product = product;
    this.price = price;
    this.quantity = quantity;
  }

  @Override
  public Money getAmount() {
    return price.multiply(quantity);
  }

  @Override
  public Product getProduct() {
    return product;
  }
  
  public String getProductName() {
    return product.getName();
  }
  
  public int getQuantity() {
    return quantity;
  }

}
