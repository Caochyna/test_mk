package com.cql;

import com.beust.jcommander.Parameter;
import com.cql.dataread.ReadDatabyPOI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;


public class BrowerTest {
    WebDriver driver;
    @DataProvider(name = "chyna_data")
    public Object[][] getData() throws IOException {
        String filepath = "F:\\springcloud_sell\\test_mk_01\\src\\test\\java\\com\\cql\\data\\search.xls";
        System.out.println(filepath);
        return ReadDatabyPOI.poiRangeData(filepath);
    }

    @BeforeTest
    @Parameters({"url","browser"})
    public void setUpClass(String url,String browser) {
        if (browser.equals("chrome")){
            System.setProperty("webdriver.chrome.driver", "F:\\AppData\\Local\\Programs\\Python\\Python36\\chromedriver.exe");
        driver = new ChromeDriver();
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(url);

    }

    @AfterTest
    public void tearDownClass() {
        System.out.println("PASSED Browser");
        driver.quit();
    }

    @BeforeMethod
    public void setUp() {
    }

    @AfterMethod
    public void tearDown() {
    }

    @Test(dataProvider = "chyna_data")
    public void browerTest(String s1) {

        driver.findElement(By.id("kw")).sendKeys(s1);
        try {
            Thread.sleep(2 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(By.id("su")).click();
    }
   /* @Test()
    @Parameters({"value"})
    public void cTest(String searchValue) {

        driver.findElement(By.id("kw")).sendKeys(searchValue);
        try {
            Thread.sleep(2 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(By.id("su")).click();
    }*/
}