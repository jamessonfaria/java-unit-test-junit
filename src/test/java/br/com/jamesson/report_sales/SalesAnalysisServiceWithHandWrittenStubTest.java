package br.com.jamesson.report_sales;

import br.com.jamesson.report_sales.after.Sale;
import br.com.jamesson.report_sales.after.SalesAnalysisService;
import br.com.jamesson.report_sales.after.SalesReport;
import br.com.jamesson.report_sales.after.SalesRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class SalesAnalysisServiceWithHandWrittenStubTest {

	private SalesRepository mockRepo;
	private SalesAnalysisService analysisService;

	private static final List<Sale> exampleSales = Arrays.asList(
			new Sale("Apples", "Cardiff", 10, 2),
			new Sale("Oranges", "Cardiff", 3, 5),
			new Sale("Bananas", "Cardiff", 6, 20),
			new Sale("Oranges", "London", 5, 7));

	private static final Map<String, Integer> expectedStoreSales = new HashMap<>();
	static {
		expectedStoreSales.put("Cardiff", 155);
		expectedStoreSales.put("London", 35);
	}

	private static final Map<String, Integer> expectedStoreProducts = new HashMap<>();
	static {
		expectedStoreProducts.put("Apples", 20);
		expectedStoreProducts.put("Oranges", 50);
		expectedStoreProducts.put("Bananas", 120);
	}

	@Before
	public void before(){
		mockRepo = mock(SalesRepository.class);
		analysisService = new SalesAnalysisService(mockRepo);
	}

	@Test
	public void shouldAggregateStoreSales() {
		// given
		when(mockRepo.loadSales()).thenReturn(exampleSales);

		// when
		Map<String, Integer> storeSales = analysisService.tallyStoreSales();

		// then
		assertEquals(expectedStoreSales, storeSales);
		verify(mockRepo).loadSales();
	}

	@Test
	public void shouldAggregateProductSales() {
		// given
		when(mockRepo.loadSales()).thenReturn(exampleSales);

		// when
		Map<String, Integer> storeProducts = analysisService.tallyProductSales();

		// then
		assertEquals(expectedStoreProducts, storeProducts);
	}

}
