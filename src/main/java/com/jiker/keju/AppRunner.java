package com.jiker.keju;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.Scanner;

public class AppRunner {

    public static void main(String[] args) {
        /*TODO
          1. args[0]为resources下的测试数据文件名，例如传入的args[0]值为"testData.txt"，注意并不包含文件路径。
          2. 你写的程序将把testDataFile作为参数加载此文件并读取文件内的测试数据，并对每条测试数据计算结果。
          3. 将所有计费结果拼接并使用\n分割，然后保存到receipt变量中。
         */
        String testDataFile = args[0];
        try {
            String receipt = runEachData(testDataFile);
            System.out.println(receipt);
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
        TestData testData = TestDataParser.parse(params);
        TaxiFeeCalculator calculator = new TaxiFeeCalculator();
        return calculator.calculate(testData.getDistance(), testData.getWaiting());
    }
}
