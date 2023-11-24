package example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Optional;
import org.testng.annotations.*;

import java.util.*;

public class TablePractice {

    WebDriver driver;

    @BeforeMethod
    @Parameters("browser")
    public void bm(@Optional("chrome") String browser) {
        ChromeOptions chromeOptions = new ChromeOptions();
//        RelativeLocator.with().toLeftOf()
        if (browser.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        } else {
            driver = new FirefoxDriver();
        }
        driver.get("https://www.nseindia.com/");
    }

    @Test
    public void simple() throws InterruptedException {
        Thread.sleep(5000);

        driver.switchTo().newWindow(WindowType.TAB);
        driver.navigate().to("https://www.browserstack.com/");
        Thread.sleep(5000);
        driver.switchTo().newWindow(WindowType.WINDOW);
        driver.navigate().to("https://www.browserstack.com/guide/selenium-4-features");
        Thread.sleep(5000);


        Set<String> windowHandles = driver.getWindowHandles();
        for (String w:windowHandles){
            String title = driver.switchTo().window(w).getTitle();

        }

    }

    @Test
    public void test() {
        int rowsSize = driver.findElements(By.xpath("//table[@id='tab1Ganier']/tbody/tr")).size();
        System.out.println(rowsSize);
        int columnSize = driver.findElements(By.xpath("//table[@id='tab1Ganier']/thead/tr/th")).size();
        System.out.println(columnSize);

        List<Map<String, String>> list = new ArrayList<>();
        Map<String, String> map = null;

        for (int i = 1; i <= rowsSize; i++) {
            map = new HashMap<>();
            for (int j = 1; j <= columnSize; j++) {
                String key = driver.findElement(By.xpath("//table[@id='tab1Ganier']/thead/tr/th[" + j + "]")).getText();
                String value = driver.findElement(By.xpath("//table[@id='tab1Ganier']/tbody/tr[" + i + "]/td[" + j + "]")).getText();

                map.put(key, value);
            }
            list.add(map);
        }

        System.out.println(list);

        for (int i = 0; i < list.size(); i++) {
            Map<String, String> stringStringMap = list.get(i);
            for (Map.Entry<String, String> entry : stringStringMap.entrySet()) {
                if (entry.getKey().equalsIgnoreCase("%CHNG")) {
                    double value = Double.parseDouble(entry.getValue());
                    if (value < 2) {
                        System.out.println(entry.getValue());
                    }
                }
            }
        }

    }

    @Test
    public void listApproach() {
        int rowsSize = driver.findElements(By.xpath("//table[@id='tab1Ganier']/tbody/tr")).size();

        List<StockData> data = new ArrayList<>();
        for (int i = 1; i <= rowsSize; i++) {
            String symbol = driver.findElement(By.xpath("//table[@id='tab1Ganier']/tbody/tr[" + i + "]/td[1]")).getText();
            String LTP = driver.findElement(By.xpath("//table[@id='tab1Ganier']/tbody/tr[" + i + "]/td[2]")).getText();
            String CHNG = driver.findElement(By.xpath("//table[@id='tab1Ganier']/tbody/tr[" + i + "]/td[3]")).getText();
            String volume = driver.findElement(By.xpath("//table[@id='tab1Ganier']/tbody/tr[" + i + "]/td[4]")).getText();
            StockData stockData = new StockData(symbol, Double.parseDouble(LTP), Double.parseDouble(CHNG), volume);
            data.add(stockData);
        }

        Double maxLTP = 0.0;
        String company = null;
        for (StockData d : data) {
            System.out.println(d);
            if (d.getLTP() > maxLTP) {
                maxLTP = d.getLTP();
                company = d.getSymbol();
            }
        }
        System.out.println(company + " " + maxLTP);

        //less
        double minValue = Double.MAX_VALUE;
        String company2 = null;
        for (StockData d : data) {
            System.out.println(d);
            Double ltp = d.getLTP();
            if (ltp < minValue) {
                minValue = ltp;
                company2 = d.getSymbol();
            }
        }
        System.out.println(company2+" "+minValue);
    }

    @AfterMethod
    public void am() {
        driver.close();
    }
}
