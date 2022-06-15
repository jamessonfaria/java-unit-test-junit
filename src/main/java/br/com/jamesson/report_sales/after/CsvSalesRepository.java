package br.com.jamesson.report_sales.after;

import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class CsvSalesRepository implements SalesRepository {

	private final String fileLocation;
	private PrintStream error;
	private List<Sale> sales;

	public CsvSalesRepository(String fileLocation) {
		this.fileLocation = fileLocation;
		error = System.out;
	}

	public void setError(PrintStream error) {
		this.error = error;
	}

	private int parseInt(String value) {
		return Integer.parseInt(value.trim());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.monotonic.testing.m5.SalesRepository#loadSales()
	 */
	@Override
	public List<Sale> loadSales() {
		if (sales == null) {
			sales = new ArrayList<>();
			try (CSVReader reader = new CSVReader(new FileReader(fileLocation))) {
				String[] nextLine;
				while ((nextLine = reader.readNext()) != null) {
					String product = nextLine[0].trim();
					String store = nextLine[1].trim();
					int number = parseInt(nextLine[2]);
					int pricePerItem = parseInt(nextLine[3]);
					sales.add(new Sale(product, store, number, pricePerItem));
				}
			} catch (Exception e) {
				e.printStackTrace(error);
				sales = new ArrayList<>();
			}
		}
		return sales;
	}

}
