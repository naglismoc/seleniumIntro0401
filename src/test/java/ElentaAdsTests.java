import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.sql.Driver;
import java.time.Duration;

public class ElentaAdsTests {

    public static WebDriver driver;

    public void acceptCookies(){
        driver.get("https://elenta.lt");
        WebElement acceptBtn = driver.findElement(By.className("fc-cta-consent"));//
        acceptBtn.click();
    }
    @BeforeClass
    public void setUp(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        acceptCookies();
    }

    @BeforeMethod
    public void beforeMethod(){
        driver.get("https://elenta.lt/patalpinti/ivesti-informacija?categoryId=BuitisLaisvalaikis_FloraFauna&actionId=Siulo&returnurl=%2F");
    }

    public void fillFormPage1(String title, String text, String price, String location, String phoNo, String email){
        driver.findElement(By.id("title")).sendKeys(title);
        driver.findElement(By.id("text")).sendKeys(text);
        driver.findElement(By.id("price")).sendKeys(price);
        driver.findElement(By.id("location-search-box")).sendKeys(location);
        driver.findElement(By.id("phone")).sendKeys(phoNo);
        driver.findElement(By.id("email")).sendKeys(email);
        driver.findElement(By.id("submit-button")).click();
    }
    @Test
    public void positiveAdTest(){
        fillFormPage1("Drasunas","panasiai kaip bijunas, tik drasesnis","40","Kaunas","+37063598745","drasus@medis.lt");
        String expected = "įkelkite nuotraukas";
        String actual = "";
        try{
            actual = driver.findElement(By.xpath("//*[@id=\"fileinput-label\"]/span")).getText();
        }catch (Exception e){}
        Assert.assertEquals(actual,expected);
    }
    @Test
    public void adNoTitleTest(){
        fillFormPage1("","","40","Kaunas","+37063598745","drasus@medis.lt");
    }
    @Test
    public void adNoDescriptionTest(){
        fillFormPage1("drasunas","","40","Kaunas","+37063598745","drasus@medis.lt");
        String notExpected = "display:none";
        String actual =  driver.findElement(By.id("txte")).getDomAttribute("style");
        Assert.assertNotEquals(actual,notExpected);
    }
    @Test
    public void adNoPriceTest(){
        fillFormPage1("Drasunas","panasiai kaip bijunas, tik drasesnis","","Kaunas","+37063598745","drasus@medis.lt");
    }
    @Test
    public void adNoCityTest(){
        fillFormPage1("Drasunas","panasiai kaip bijunas, tik drasesnis","40","","+37063598745","drasus@medis.lt");
    }
    @Test
    public void adNoPhoNoTest(){
        fillFormPage1("Drasunas","panasiai kaip bijunas, tik drasesnis","40","Kaunas","","drasus@medis.lt");
    }
    @Test
    public void adNoEmailTest(){
        fillFormPage1("Drasunas","panasiai kaip bijunas, tik drasesnis","40","Kaunas","+37063598745","");
    }
    @Test
    public void adPhotoPositiveTest(){
        fillFormPage1("Drasunas","panasiai kaip bijunas, tik drasesnis","40","Kaunas","+37063598745","drasus@medis.lt");
        driver.findElement(By.id("inputfile")).sendKeys("C:\\Users\\dvdfi\\Downloads\\java primitive types.jpg");
    }
}
