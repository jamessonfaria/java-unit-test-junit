package br.com.jamesson.coffee;

import org.junit.Assert;
import org.junit.Test;

import static br.com.jamesson.coffee.CoffeeType.*;
import static junit.framework.TestCase.assertEquals;

public class CafeTest {

    public static final int ESPRESSO_BEANS = Espresso.getRequiredBeans();
    public static final int NO_MILK = 0;
    public static final int NO_BEANS = 0;

    private Cafe cafeWithBeans() {
        Cafe cafe = new Cafe();
        cafe.restockBeans(ESPRESSO_BEANS);
        return cafe;
    }

    @Test
    public void canBrewEspresso(){
        // given
        Cafe cafe = cafeWithBeans();

        // when
        Coffee coffee = cafe.brew(Espresso);

        // then
        Assert.assertEquals("Wrong coffee type", Espresso, coffee.getType());
        Assert.assertEquals("Wrong amount of milk", NO_MILK, coffee.getMilk());
        Assert.assertEquals("Wrong number of beans", ESPRESSO_BEANS, coffee.getBeans());
    }

    @Test
    public void brewingEspressoConsumesBeans(){
        // given
        Cafe cafe = cafeWithBeans();

        // when
        Coffee coffee = cafe.brew(Espresso);

        // then
        Assert.assertEquals(NO_BEANS, cafe.getBeansInStock());
    }

    @Test(expected = IllegalArgumentException.class)
    public void mustRestockMilk(){
        // given
        Cafe cafe = new Cafe();

        // when
        cafe.restockMilk(NO_MILK);
    }

    @Test(expected = IllegalArgumentException.class)
    public void mustRestockBeans(){
        // given
        Cafe cafe = new Cafe();

        // when
        cafe.restockBeans(NO_BEANS);
    }

    // then
    @Test(expected = IllegalStateException.class)
    public void lattesRequiresMilk(){
        // given
        Cafe cafe = cafeWithBeans();

        // when
        cafe.brew(Latte);
    }

    @Test
    public void canBrewLatte(){
        // given
        Cafe cafe = cafeWithBeans();
        cafe.restockMilk(Latte.getRequiredMilk());

        // when
        Coffee coffee = cafe.brew(Latte);

        // then
        assertEquals("Wrong coffee type", Latte, coffee.getType());
    }

}
