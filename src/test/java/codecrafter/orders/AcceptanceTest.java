package codecrafter.orders;

import org.junit.*;
import org.jmock.*;
import org.jmock.integration.junit4.*;

import static codecrafter.orders.Money.*;
import static codecrafter.orders.ProductOrigin.*;
import static codecrafter.orders.ProductType.*;
import static codecrafter.orders.TaxExemptionEligibilityCheck.*;
import static codecrafter.orders.TaxImportedEligibilityCheck.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class AcceptanceTest {

  private Mockery context = new JUnit4Mockery();
  
  private TaxingPractice taxingPractice;
  private Product exemptLocal;
  private Product exemptImported;
  private Product taxedLocal;
  private Product taxedImported;

  @Before
  public void setUp() {
    setUpTaxes();
    setUpProducts();
  }

  private void setUpTaxes() {
    TaxMethod basicSalesTax = TaxMethod.create("BST", "0.10", exempt(BOOK, FOOD, MEDICAL));
    TaxMethod importDuty = TaxMethod.create("IMD", "0.05", imported());
    taxingPractice = TaxingPractice.create();
    taxingPractice.add(basicSalesTax);
    taxingPractice.add(importDuty);    
  }
  
  private void setUpProducts() {
    exemptLocal = Product.create("Exempt Local", BOOK, LOCAL, dollars("10"));
    exemptImported = Product.create("Exempt Imported", BOOK, IMPORTED, dollars("10"));
    taxedLocal = Product.create("Taxed Local", MOVIE, LOCAL, dollars("10"));
    taxedImported = Product.create("Taxed Imported", MOVIE, IMPORTED, dollars("10"));
  }

  @Test
  public void Purchase_Exempt_Local_Product() {
    Order order = Order.create(taxingPractice);
    order.add(exemptLocal, 1);
    assertThat(order.getSubTotal(), is(dollars("10")));
    assertThat(order.getTaxTotal(), is(dollars("0")));
    assertThat(order.getTotal(), is(dollars("10")));
  }
  
  @Test
  public void Purchase_Exempt_Imported_Product() {
    Order order = Order.create(taxingPractice);
    order.add(exemptImported, 1);
    assertThat(order.getSubTotal(), is(dollars("10")));
    assertThat(order.getTaxTotal(), is(dollars("0.5")));
    assertThat(order.getTotal(), is(dollars("10.5")));
  }

  @Test
  public void Purchase_Taxed_Local_Product() {
    Order order = Order.create(taxingPractice);
    order.add(taxedLocal, 1);
    assertThat(order.getSubTotal(), is(dollars("10")));
    assertThat(order.getTaxTotal(), is(dollars("1")));
    assertThat(order.getTotal(), is(dollars("11")));
  }

  @Test
  public void Purchase_Two_Taxed_Imported_Products() {
    Order order = Order.create(taxingPractice);
    order.add(taxedImported, 2);
    assertThat(order.getSubTotal(), is(dollars("20")));
    assertThat(order.getTaxTotal(), is(dollars("3"))); // 15%
    assertThat(order.getTotal(), is(dollars("23")));
  }

  @Test
  public void Purchase_Taxed_Imported_Product_And_Print_Receipt() {
    Order order = Order.create(taxingPractice);
    order.add(taxedImported, 1);

    final Receipt receipt = context.mock(Receipt.class);
    final Sequence printing = context.sequence("printing");
    context.checking(new Expectations() {{
      oneOf(receipt).start(); inSequence(printing);
      oneOf(receipt).printProduct(with(any(ProductEntry.class))); inSequence(printing);
      oneOf(receipt).printTax(with(any(TaxEntry.class))); inSequence(printing);
      oneOf(receipt).printTax(with(any(TaxEntry.class))); inSequence(printing);
      oneOf(receipt).printSubTotal(dollars("10")); inSequence(printing);
      oneOf(receipt).printTaxTotal(dollars("1.50")); inSequence(printing);
      oneOf(receipt).printTotal(dollars("11.50")); inSequence(printing);
      oneOf(receipt).end(); inSequence(printing);
    }});

    order.print(receipt);
  }

  @Test
  public void Purchase_All_Products() {
    Order order = Order.create(taxingPractice);
    order.add(exemptLocal, 1);    // $0.00 tax
    order.add(exemptImported, 1); // $0.50 tax
    order.add(taxedLocal, 1);     // $1.00 tax
    order.add(taxedImported, 1);  // $1.50 tax
                                  // _________ +
                            // TOTAL $3.00 tax

    assertThat(order.getSubTotal(), is(dollars("40")));
    assertThat(order.getTaxTotal(), is(dollars("3")));
    assertThat(order.getTotal(), is(dollars("43")));
  }

}
