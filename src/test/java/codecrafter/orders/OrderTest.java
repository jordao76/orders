package codecrafter.orders;

import java.util.*;

import org.junit.*;
import org.jmock.*;
import org.jmock.integration.junit4.*;

import static codecrafter.orders.Money.*;
import static codecrafter.orders.ProductOrigin.*;
import static codecrafter.orders.ProductType.*;

public class OrderTest {

  private Mockery context = new JUnit4Mockery();

  @Test
  public void Added_Product_Should_Trigger_Tax_Calculation() {
    final TaxingPractice taxes = context.mock(TaxingPractice.class);
    Order order = Order.create(taxes);

    context.checking(new Expectations() {{
      oneOf(taxes).apply(with(any(ProductEntry.class))); will(returnValue(emptyList())); 
    }});

    Product product = Product.create("Test Product", BOOK, LOCAL, dollars("0"));
    order.add(product, 42);
  }

  public static <E> List<E> emptyList() { // TODO: move!
    return new ArrayList<E>();
  }
  
}
