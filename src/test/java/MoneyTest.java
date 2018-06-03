import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pl.com.bottega.ecommerce.sharedkernel.Money;

import java.math.BigDecimal;

import static org.hamcrest.core.Is.is;

public class MoneyTest {
    Money pln50;
    Money pln150;
    Money usd50;
    Money usd150;
    Money noCurrency50;
    Money noCurrency150;
    @Before
    public void initTesting()
    {
        pln50 = new Money(50,"PLN");
        pln150 = new Money(150, "PLN");
        usd50 = new Money(50, "USD");
        usd150 = new Money( 150, "USD");
        noCurrency50 = new Money(50);
        noCurrency150 = new Money(150);

    }

    @Test
    public void addingSameCurrencyMoneyShouldReturnSumOfThePrice()
    {
        Money expectedSum = new Money(200, "PLN");
        Money returnedSum = pln50.add(pln150);
        Assert.assertThat(returnedSum, is(expectedSum));
    }
}
