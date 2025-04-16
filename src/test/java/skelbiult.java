import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class skelbiult {
    public void acceptCookies(WebDriver driver){
        driver.get("https://skelbiu.lt");
        WebElement acceptBtn = driver.findElement(By.id("onetrust-accept-btn-handler"));
        acceptBtn.click();
    }
    @Test
    public void dataGatherer(){
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        acceptCookies(driver);

        String searchKeyword = "verpimo ratelis";
        String baseUrl = "https://www.skelbiu.lt/skelbimai/";
        List<String> urls = new ArrayList<>();
        for (int i = 1; i <= 200; i++) {
            String url = baseUrl + i + "?keywords=" + searchKeyword.replace(" ","%20");
            driver.get(url);
            String currentUrl = driver.getCurrentUrl();
            if(!url.equals(currentUrl)){
                break;
            }
         urls.addAll(getUrls(driver));
        }
        System.out.println(urls.size());
        getUserData(driver, urls);
    }

    private void getUserData(WebDriver driver, List<String> urls) {
        for (String url : urls) {
            driver.get(url);
            try {
            System.out.println(driver.findElement(By.cssSelector(".profile-data > .name")).getText());
            }catch (Exception e){}
        }
    }

    private List<String> getUrls(WebDriver driver) {
        List<WebElement> urlsElements = driver.findElements(By.cssSelector(".standard-list-container > div > a"));
        List<String> urls = new ArrayList<>();
        for (WebElement urlElem:urlsElements   ) {
            String url = "https://www.skelbiu.lt" + urlElem.getDomAttribute("href");
            urls.add(url);
        }
        return urls;
    }


    //    @Test
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
