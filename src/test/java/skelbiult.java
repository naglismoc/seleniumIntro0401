import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.util.List;

public class skelbiult {

    @Test
    public void openWebsite() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://skelbiu.lt");
        Thread.sleep(500);
        WebElement acceptBtn = driver.findElement(By.id("onetrust-accept-btn-handler"));
        acceptBtn.click();
        driver.findElement(By.id("searchKeyword")).sendKeys("kengura");
        driver.findElement(By.id("searchButton")).click();
        Thread.sleep(500);
        String adsQuatity = driver.findElement(By.xpath("//*[@id=\"body-container\"]/div[2]/div[1]/ul/li/span")).getText();
        System.out.println("radome " + adsQuatity + " skelbimu.");
        WebElement container = driver.findElement(By.className("standard-list-container"));
        List<WebElement> contentBlocks = container.findElements(By.className("content-block"));
        Thread.sleep(1000);
        for (int i = 0; i < contentBlocks.size(); i++) {
           String price = "0";
           try {
               price = contentBlocks.get(i).findElement(By.className("price")).getText();
           }catch (Exception e){
               System.out.println("nebuvo kainos");
           }
            System.out.println(price);
        }
//        driver.quit();
    }




}
