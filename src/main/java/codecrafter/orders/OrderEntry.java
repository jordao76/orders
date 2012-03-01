package codecrafter.orders;

public interface OrderEntry {
  Product getProduct();
  Money getAmount();
}
