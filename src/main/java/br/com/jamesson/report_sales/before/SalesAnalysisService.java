package br.com.jamesson.report_sales.before;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingInt;

import java.util.Map;
import java.util.function.Function;

public class SalesAnalysisService {

	private final String fileLocation;

	public SalesAnalysisService(String fileLocation) {
		this.fileLocation = fileLocation;
	}

	public Map<String, Integer> tallyStoreSales() {
		return tallySalesBy(Sale::getStore);
	}

	public Map<String, Integer> tallyProductSales() {
		return tallySalesBy(Sale::getProduct);
	}

	private Map<String, Integer> tallySalesBy(Function<Sale, String> classifier) {
		CsvSalesRepository repo = new CsvSalesRepository(fileLocation);
		return repo.loadSales()
				.stream()
				.collect(groupingBy(classifier, summingInt(Sale::getValue)));
	}

}
