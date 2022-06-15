package br.com.jamesson.report_sales.after;

public class SimpleReportRunner {

	public static void main(String[] args) {

		if (args.length < 1) {
			System.err.println("You must provide a commandline argument specifying the file to analyse");
			System.exit(-1);
		}

		CsvSalesRepository repository = new CsvSalesRepository(args[0]);
		repository.setError(System.err);

		SalesAnalysisService analyser = new SalesAnalysisService(repository);
		SalesReport report = new SalesReport(System.out, analyser);
		report.report();
	}

}
