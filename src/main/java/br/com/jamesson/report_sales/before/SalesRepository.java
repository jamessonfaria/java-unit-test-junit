package br.com.jamesson.report_sales.before;

import java.util.List;

public interface SalesRepository {

	public List<Sale> loadSales();

}
