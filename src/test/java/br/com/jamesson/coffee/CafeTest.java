package br.com.jamesson.coffee;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static br.com.jamesson.coffee.CoffeeType.*;
import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
import static org.junit.Assert.assertThat;

public class CafeTest {

    public static final int ESPRESSO_BEANS = Espresso.getRequiredBeans();
    public static final int NO_MILK = 0;
    public static final int NO_BEANS = 0;

    private Cafe cafe;

    @Before
    public void before(){
        this.cafe = new Cafe();
    }

    private void withBeans() {
        cafe.restockBeans(ESPRESSO_BEANS);
    }

    @Test
    public void canBrewEspresso(){
        // given
        withBeans();

        // when
        Coffee coffee = cafe.brew(Espresso);

        // then
        assertThat(coffee, hasProperty("beans"));
        Assert.assertEquals("Wrong coffee type", Espresso, coffee.getType());
        Assert.assertEquals("Wrong amount of milk", NO_MILK, coffee.getMilk());
        assertThat(coffee, hasProperty("beans", equalTo(ESPRESSO_BEANS)));
    }

    @Test
    public void brewingEspressoConsumesBeans(){
        // given
        withBeans();

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
        withBeans();

        // when
        cafe.brew(Latte);
    }

    @Test
    public void canBrewLatte(){
        // given
        withBeans();
        cafe.restockMilk(Latte.getRequiredMilk());

        // when
        Coffee coffee = cafe.brew(Latte);

        // then
        assertEquals("Wrong coffee type", Latte, coffee.getType());
    }

}
