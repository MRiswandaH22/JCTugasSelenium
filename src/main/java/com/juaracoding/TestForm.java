package com.juaracoding;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.TimeUnit;

public class TestForm {
    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver","C:\\juaracoding\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        JavascriptExecutor js = (JavascriptExecutor) driver;

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://formy-project.herokuapp.com/form");
        System.out.println("Open Web Herokuapp");

        driver.manage().window().maximize();
        System.out.println("Windows Maximaze");

        String judul = driver.findElement(By.cssSelector("h1")).getText();
        System.out.println("Judul Halaman "+judul);

        WebElement element1 = driver.findElement(By.id("first-name")); element1.sendKeys("CwanCwan");
        System.out.println("Input First Name : "+element1.getAttribute("value"));

        WebElement element2 = driver.findElement(By.id("last-name")); element2.sendKeys("22");
        System.out.println("Input Last Name : "+element2.getAttribute("value"));

        WebElement element3 = driver.findElement(By.id("job-title")); element3.sendKeys("System Quality Asurance");
        System.out.println("Input Job Title : "+element3.getAttribute("value"));

        WebElement element4 = driver.findElement(By.xpath("//*[@id='radio-button-1']")); element4.click();
        System.out.println("Data klicked : "+element4.getAttribute("value")+" (High School)");

        WebElement element5 = driver.findElement(By.id("checkbox-1")); element5.click();
        String isian = element5.getAttribute("value");
        System.out.println("Data klicked : "+isian +" (Male)");

        //delay 10 detik
        try {
            Thread.sleep(2000);
        }catch (InterruptedException e){
            throw new RuntimeException(e);
        }

        //untuk scroll kalau data terakhir sudah diisi

        if (isian.isEmpty()){
            System.out.println("Data belum diisi");
            driver.close();
        }else {
            js.executeScript("window.scrollBy(0,500)");
        }

        WebElement selectExperience = driver.findElement(By.id("select-menu"));
        Select select = new Select(selectExperience);
        select.selectByIndex(1);
        System.out.println("Data Selected : "+selectExperience.getAttribute("value")+" (0-1)");

        WebElement dateBox = driver.findElement(By.id("datepicker"));
        dateBox.sendKeys("02242023");
        System.out.println("Date Picked : "+dateBox.getAttribute("value"));

        driver.findElement(By.linkText("Submit")).click();
        System.out.println("Button Clicked move to next page");

        //delay 3 detik
        try {
            Thread.sleep(5000);
        }catch (InterruptedException e){
            throw new RuntimeException(e);
        }
        driver.quit();
    }
}