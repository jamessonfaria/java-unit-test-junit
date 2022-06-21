package br.com.jamesson.mod6;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class SalesRepositoryTest {

    @Test
    public void shouldLoadSampleData(){
        SalesRepository repository = new SalesRepository("src/main/resources/example-sales.csv");

        List<Sale> sales = repository.loadSales();

        assertThat(sales, hasItem(allOf(
            hasProperty("store", equalTo("London")),
            hasProperty("number", equalTo(2)),
            hasProperty("pricePerItem", equalTo(30))
        )));
    }
}
