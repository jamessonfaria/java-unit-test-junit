package br.com.jamesson.mod6;

import java.io.PrintStream;

public class SalesReportRunner {
    private final PrintStream out;

    public static void main(String[] args) {
        String fileLocation = args[0];
        new SalesReportRunner(System.out).run(fileLocation);
    }

    public SalesReportRunner(PrintStream out) {
        this.out = out;
    }

    public void run(String fileLocation) {
        SalesRepository repo = new SalesRepository(fileLocation);
        SalesAnalyser analyser = new SalesAnalyser(repo);
        SalesReport report = new SalesReport(analyser, out);
        report.run();
    }
}
