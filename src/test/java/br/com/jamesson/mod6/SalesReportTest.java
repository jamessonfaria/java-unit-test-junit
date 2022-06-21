package br.com.jamesson.mod6;

import org.junit.Test;
import org.mockito.Mockito;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

public class SalesReportTest {
    private static final String CITY = "London";
    private static final int TALLY = 235;


    private PrintStream mockOut = mock(PrintStream.class);
    private SalesAnalyser mockAnalyser = mock(SalesAnalyser.class);
    private SalesReport report = new SalesReport(mockAnalyser, mockOut);

    @Test
    public void shouldPrintCityTallies(){
        // given
        Map<String, Integer> cityTallies = new HashMap<>();
        cityTallies.put(CITY, TALLY);
        when(mockAnalyser.tallyCitySales()).thenReturn(cityTallies);

        // when
        report.run();

        // then
        verify(mockOut).printf(anyString(), eq(CITY), eq(TALLY));
    }

}
