package codecrafter.orders;

import java.util.Collection;

public interface TaxingPractice {

  Collection<TaxEntry> apply(ProductEntry entry);

}