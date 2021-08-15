package common;

import org.testng.annotations.DataProvider;

import java.lang.reflect.Method;
import java.util.ArrayList;

public class GetData {
    @DataProvider(name = "testData")
    public static Object[][] getData(Method m) {
        String testFileName = m.getDeclaringClass().getSimpleName();
        String[] packageName = m.getDeclaringClass().getPackageName().split("\\.");
        ExcelUtils excel = new ExcelUtils("src/test/java/" + packageName[0] + "/dataProvider/" + packageName[2]  + "/" + testFileName + ".xlsx", m.getName());
        int rowCount = excel.getRowCount();
        int colCount = excel.getColCount();
        ArrayList<ArrayList<String>> data = new ArrayList<>();
        for (int i = 1; i < rowCount; i++) {
            if (excel.getCellDataString(i, 0).equals("yes")) {
                ArrayList<String> row = new ArrayList<>();
                for (int j = 1; j < colCount; j++) {
                    String cellData = excel.getCellDataString(i, j);
                    row.add(cellData);
                }
                data.add(row);
            }
        }
        String[][] data2d = data.stream().map(u -> u.toArray(new String[0])).toArray(String[][]::new);
        return data2d;
    }
}
