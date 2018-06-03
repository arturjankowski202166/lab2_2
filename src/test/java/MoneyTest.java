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

    @Test
    public void subtractingSameCurrencyMoneyShouldReturnProperResult()
    {
        Money expectedSum = new Money(100, "PLN");
        Money returnedSum = pln150.subtract(pln50);
        Assert.assertThat(returnedSum, is(expectedSum));
    }

    @Test
    public void multiplyingCurrencyWithDoubleShouldReturnProperResult()
    {
        Money expectedSum = new Money(100, "PLN");
        Money returnedSum = pln50.multiplyBy(2.0);
        Assert.assertThat(returnedSum, is(expectedSum));
    }

    @Test
    public void multiplyingCurrencyWithDecimalShouldReturnProperResult()
    {
        Money expectedSum = new Money(100, "PLN");
        Money returnedSum = pln50.multiplyBy(new BigDecimal(2.0));
        Assert.assertThat(returnedSum, is(expectedSum));
    }

    @Test
    public void multiplyingNoCurrencyWithDoubleShouldReturnProperResult()
    {
        Money expectedSum = new Money(100);
        Money returnedSum = noCurrency50.multiplyBy(2.0);
        Assert.assertThat(returnedSum, is(expectedSum));
    }

    @Test
    public void multiplyingNoCurrencyWithDecimalShouldReturnProperResult()
    {
        Money expectedSum = new Money(100);
        Money returnedSum = noCurrency50.multiplyBy(new BigDecimal(2.0));
        Assert.assertThat(returnedSum, is(expectedSum));
    }

    @Test(expected = IllegalArgumentException.class)
    public void addingNoCurrencyToCurrencyMoneyShouldThrowIllegalArgumentException()
    {
        Money expectedSum = new Money(200, "PLN");
        Money returnedSum = pln50.add(noCurrency150);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addingDifferentCurrencyMoneyShouldThrowIllegalArgumentException()
    {
        Money expectedSum = new Money(200, "PLN");
        Money returnedSum = pln50.add(usd150);
    }

    @Test(expected = IllegalArgumentException.class)
    public void subtractingNoCurrencyToCurrencyMoneyShouldThrowIllegalArgumentException()
    {
        Money expectedSum = new Money(200, "PLN");
        Money returnedSum = pln50.subtract(noCurrency150);
    }

    @Test(expected = IllegalArgumentException.class)
    public void subtractingDifferentCurrencyMoneyShouldThrowIllegalArgumentException()
    {
        Money expectedSum = new Money(200, "PLN");
        Money returnedSum = pln50.subtract(usd150);
    }

    @Test
    public void addingNoCurrenciesShouldReturnDefaultEurCurrencyWithProperValue()
    {
        Money expectedSum = new Money(200, "EUR");
        Money returnedSum = noCurrency50.add(noCurrency150);
        Assert.assertThat(expectedSum, is(returnedSum));
    }

    @Test
    public void subtractingNoCurrenciesShouldReturnDefaultEurCurrencyWithProperValue()
    {
        Money expectedSum = new Money(100, "EUR");
        Money returnedSum = noCurrency150.subtract(noCurrency50);
        Assert.assertThat(expectedSum, is(returnedSum));
    }

    @Test
    public void subtractingBiggerValueFromSmallerShouldReturnProperNegativeValue()
    {
        Money expectedSum = new Money(-100, "PLN");
        Money returnedSum = pln50.subtract(pln150);
        Assert.assertThat(expectedSum, is(returnedSum));
    }
}
