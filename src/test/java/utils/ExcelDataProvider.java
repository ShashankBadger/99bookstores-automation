package utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.DataProvider;

public class ExcelDataProvider
{
	private static String path = System.getProperty("user.dir")+File.separator+ConfigReader.getProperty("excelFilePath")+"testsData.xlsx";
	
	@DataProvider(name="validSearchData")
	public static Object[][] validSearchData()
    {
        return getSearchData("books found");
    }
	
	@DataProvider(name="invalidSearchData")
	public static Object[][] invalidSearchData()
    {
        return getSearchData("invalid");
    }
	
	public static Object[][] getSearchData(String key)
	{
		
		ExcelUtils excel = new ExcelUtils(path, "Search_Input");
		
		int rows = excel.getRowCount();
		
		List<Object[]> data = new ArrayList<Object[]>();
		
		for(int i=1; i<= rows; i++)
		{
			String expected = excel.readCellData(i, 3);
			
			if(expected.toLowerCase().contains(key))
			{
				data.add(new Object[] {i,                       // rowIndex
	                        excel.readCellData(i, 0),           // testCase
	                        excel.readCellData(i, 2),           // input
	                        expected,
	                        path
	                     });
			}
		}
		return data.toArray(new Object[0][]);
	}
}
