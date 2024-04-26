package com.example;

import static org.junit.Assert.assertTrue;

import java.io.FileInputStream;
import java.time.Duration;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.checkerframework.checker.units.qual.t;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    WebDriver driver;
    @BeforeTest
    public void BeforeTest(){
    WebDriverManager.chromedriver().setup();
    driver=new ChromeDriver();
    driver.get("https://www.barnesandnoble.com/");
    }
    @Test()
    public void shouldAnswerWithTrue() throws Exception
    {
        WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\'rhf_header_element\']/nav/div/div[3]/form/div/div[1]/a"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\'rhf_header_element\']/nav/div/div[3]/form/div/div[1]/div/a[2]"))).click();
        FileInputStream fs=new FileInputStream("C:\\Users\\viswa\\Desktop\\demobankxl.xlsx");
        XSSFWorkbook wb=new XSSFWorkbook(fs);
        XSSFSheet chetan=wb.getSheet("Sheet1");
        XSSFRow row=chetan.getRow(0);
        String bookname=row.getCell(0).getStringCellValue();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/header/nav/div/div[3]/form/div/div[2]/div/input[1]"))).sendKeys(bookname);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='rhf_header_element']/nav/div/div[3]/form/div/span/button"))).click();
        String str=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='searchGrid']/div/section[1]/section[1]/div/div[1]/div[1]/h1/span"))).getText();
        if(str.equals("Chetan Bhagat"))
        System.out.print("Search Element found");
        else
        System.out.print("Search element not found");
    }
    @Test
    public void test2() throws Exception{
        WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(20));
        JavascriptExecutor js1 = (JavascriptExecutor) driver;
        driver.navigate().to("https://www.barnesandnoble.com/membership/");
        js1.executeScript("window.scrollBy(0,2000)");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\'rewards-modal-link\']"))).click();
        String str=driver.findElement(By.xpath("//*[@id=\'dialog-title\']")).getText();
        if(str.equals("Sign in or Create an Account"))
        System.out.print("signin page popped up");
        else
        System.out.print("signin page not popped up");
        
    }
}
