package com.jiker.keju;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class AppRunner {

    public static void main(String[] args) {
        String testDataFile = args[0];
        try {
            System.out.println(runEachData(testDataFile));
        } catch (IllegalArgumentException e) {
            System.err.println("test data error" + e.getMessage());
        } catch (FileNotFoundException e) {
            System.err.println("Can not find file " + testDataFile);
        }
    }

    private static String runEachData(String testDataFile) throws FileNotFoundException {
        File file = new File(ClassLoader.getSystemClassLoader().getResource(testDataFile).getFile());
        String receipt = "";
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            receipt += calculate(scanner.nextLine()) + System.lineSeparator();
        }
        return receipt;
    }

    private static String calculate(String params) {
        Args args = new ArgDataParser().parse(params);
        TaxiFeeCalculator calculator = new TaxiFeeCalculator();
        return calculator.calculate(args.getDistance(), args.getWaiting());
    }
}
