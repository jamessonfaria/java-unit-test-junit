package br.com.jamesson.report_sales.after;

import java.util.List;

public interface SalesRepository {

	public List<Sale> loadSales();

}
