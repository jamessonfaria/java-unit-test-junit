package br.com.jamesson.mod6;

import org.junit.Test;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;

public class SalesReportSystemTest {

    @Test
    public void shouldPrintStoreReportForSampleData(){

        ApplicationRunner runner = new ApplicationRunner();

        String report = runner.run("src/main/resources/example-sales.csv");

        assertThat(report, containsString("- London          -    235 -"));
    }

}
