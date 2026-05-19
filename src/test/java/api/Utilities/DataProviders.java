package api.Utilities;

import org.testng.annotations.DataProvider;
import java.io.IOException;

    public class DataProviders {

    @DataProvider(name = "Data")
    public String[][] getAllData() throws IOException {
        String path = System.getProperty("user.dir")+"/src/testData/Userdata.xls";
        XLUtility xlutil = new XLUtility(path);                        // creating object for XLUtilities

        int totallrows = xlutil.getRowCount("sheet1");
        int totallcols = xlutil.getCellCount("sheet1", 1);

        String apiData[][] = new String[totallrows][totallcols];      //creating 2dimensional array witch can store data
          for (int i = 1; i <= totallrows; i++){                      //reading data from xl and storing in 2dim array{
              for (int j = 0; j < totallcols; j++){
                  apiData[i - 1][j] = xlutil.getCellData("sheet1", i, j);
              }
          }
            return apiData;
    }

    @DataProvider(name = "userNames")
    public String[] getUserName() throws IOException {
        String path = System.getProperty("user.dir")+"/src/testData/Userdata.xls";
        XLUtility xlutil = new XLUtility(path);                      // creating object for XLUtilities

        int totallrows = xlutil.getRowCount("sheet1");

        String apiData[] = new String[totallrows];                   //creating 1 dimensional array witch can store data
          for (int i = 1; i <= totallrows; i++) {                      //reading data from xl and storing in 2dim array

              apiData[i - 1] = xlutil.getCellData("sheet1", i, 1);
          }
          return apiData;
        }
    }

