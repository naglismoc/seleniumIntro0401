import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import java.time.Duration;

public class skelbiultTests {
    public static WebDriver driver;

    public void acceptCookies(){
        driver.get("https://skelbiu.lt");
        WebElement acceptBtn = driver.findElement(By.id("onetrust-accept-btn-handler"));
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
        driver.get("https://skelbiu.lt");
        driver.findElement(By.id("searchKeyword")).clear();
    }
    @Test
    public void iesmoke1(){
        driver.findElement(By.id("searchKeyword")).sendKeys("nardymo iranga");
        driver.findElement(By.id("searchButton")).click();
    }

    @Test
    public void iesmoke2(){
        driver.findElement(By.id("searchKeyword")).sendKeys("palapine");
        driver.findElement(By.id("searchButton")).click();
    }

    @Test
    public void butinojiIranga(){
        driver.findElement(By.id("searchKeyword")).sendKeys("turistavimo iranga");
        driver.findElement(By.id("searchButton")).click();
    }

    @AfterClass
    public void tearDown(){
//        driver.close();
    }
}
