import jdk.jfr.Timespan;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.lang.Thread;
import java.util.concurrent.TimeUnit;


public class TC {


        @Test
        public void tc1_1_1() {
                //Displaying RETURN DATE box when ROUND TRIP option is selected

                System.setProperty("webdriver.chrome.driver", "c:/Chromedriver/chromedriver.exe");
                WebDriver site = new ChromeDriver();
                site.manage().window().maximize();
                site.manage().deleteAllCookies();
                site.navigate().to("https://www.phptravels.net/flights");

                site.findElement(By.id("round-trip")).click();

               WebElement returnDate = site.findElement(By.id("return"));
               Assertions.assertTrue(returnDate.isDisplayed());

             site.close();


        }

        @Test
        public void tc2_1_1 (){
                //Searching for airport with name
                System.setProperty("webdriver.chrome.driver", "c:/Chromedriver/chromedriver.exe");
                WebDriver site = new ChromeDriver();
                site.manage().window().maximize();

                site.navigate().to("https://www.phptravels.net/flights");

                site.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);

                site.findElement(By.cssSelector("#autocomplete")).click();
                site.findElement(By.cssSelector("#autocomplete")).sendKeys("skvasta");

                Assertions.assertEquals("NYO", site.findElement(By.cssSelector("#onereturn > div.col-md-6 > div > div:nth-child(1) > div > div > div > div > div:nth-child(1) > div:nth-child(1) > b")).getText());
        }


        @Test
        public void tc2_1_2() {
                //Searching for airport with 3 letter code

        System.setProperty("webdriver.chrome.driver", "c:/Chromedriver/chromedriver.exe");
        WebDriver site = new ChromeDriver();
        site.manage().window().maximize();

        site.navigate().to("https://www.phptravels.net/flights");

                site.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);

        site.findElement(By.xpath("/html/body/section[1]/section/div/div/form/div[2]/div[1]/div/div[1]/div/div/div/input")).sendKeys("JFK");

        Assertions.assertEquals("JFK\n" +
                "John F Kennedy Intl", site.findElement(By.xpath("/html/body/section[1]/section/div/div/form/div[2]/div[1]/div/div[1]/div/div/div/div/div[1]/div[1]")).getText());
        site.quit();

        }

        @Test
        public void tc3_1_1 (){
                //Selecting the same airports


                System.setProperty("webdriver.gecko.driver", "c:/Chromedriver/geckodriver.exe");
                WebDriver site = new FirefoxDriver();
                site.manage().window().maximize();

                site.navigate().to("https://www.phptravels.net/flights");

                site.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

                site.findElement(By.id("autocomplete")).sendKeys("JFK");
                site.findElement(By.xpath("/html/body/section[1]/section/div/div/form/div[2]/div[1]/div/div[1]/div/div/div/div/div[1]/div[1]")).click();

                site.findElement(By.id("autocomplete2")).sendKeys("JFK");
                site.findElement(By.xpath("/html/body/section[1]/section/div/div/form/div[2]/div[1]/div/div[2]/div/div[2]/div/div/div[1]/div[1]/strong")).click();


                site.findElement(By.id("flights-search")).click();


                Assertions.assertEquals("https://www.phptravels.net/flights", site.getCurrentUrl());

                site.quit();

        }

        @Test
        public void tc4_1_1 (){
                //Selecting the same ‘return date’ as ‘departure date’

                System.setProperty("webdriver.gecko.driver", "c:/Chromedriver/geckodriver.exe");
                WebDriver site = new FirefoxDriver();
                site.manage().window().maximize();

                site.navigate().to("https://www.phptravels.net/flights");

                site.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

                site.findElement(By.id("round-trip")).click();

                site.findElement(By.id("departure")).click();
                site.findElement(By.cssSelector("div.datepicker:nth-child(21) > div:nth-child(1) > table:nth-child(1) > thead:nth-child(1) > tr:nth-child(1) > th:nth-child(3) > i:nth-child(1)")).click();
                site.findElement(By.cssSelector("div.datepicker:nth-child(21) > div:nth-child(1) > table:nth-child(1) > tbody:nth-child(2) > tr:nth-child(4) > td:nth-child(1)")).click();
                site.findElement(By.cssSelector("div.datepicker:nth-child(21) > div:nth-child(1) > table:nth-child(1) > tbody:nth-child(2) > tr:nth-child(4) > td:nth-child(1)")).click();


                WebElement returnDate = site.findElement(By.id("return"));
                WebElement departureDate = site.findElement(By.id("departure"));
                Assertions.assertEquals(true, returnDate.equals("departureDate"));
                site.quit();

        }

        @Test
        public void tc5_1_1 (){
                //Adding 5 adults and 5 children passangers (total 10) - FIREFOX

                System.setProperty("webdriver.gecko.driver", "c:/Chromedriver/geckodriver.exe");
                WebDriver site = new FirefoxDriver();
                site.manage().window().maximize();
                site.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                site.navigate().to("https://www.phptravels.net/flights");

                // Click passengers list
                site.findElement(By.cssSelector("#onereturn > div.col-lg-1.pr-0 > div > div > div > a > span > span")).click();

                // Wait for passengers list to appear
                var passengersList = By.cssSelector("#onereturn > div.col-lg-1.pr-0 > div > div > div > div");

                var wait = new WebDriverWait(site, java.time.Duration.ofSeconds(10));
                wait.until(ExpectedConditions.visibilityOfElementLocated(passengersList));

                var awaitedPlusButton = wait.until(ExpectedConditions.elementToBeClickable(By.className("qtyInc")));
                awaitedPlusButton.click();
                awaitedPlusButton.click();
                awaitedPlusButton.click();
                awaitedPlusButton.click();


                site.findElement(By.cssSelector("#onereturn > div.col-lg-1.pr-0 > div > div > div > div > div.dropdown-item.child_qty > div > div > div.qtyInc > i")).click();
                site.findElement(By.cssSelector("#onereturn > div.col-lg-1.pr-0 > div > div > div > div > div.dropdown-item.child_qty > div > div > div.qtyInc > i")).click();
                site.findElement(By.cssSelector("#onereturn > div.col-lg-1.pr-0 > div > div > div > div > div.dropdown-item.child_qty > div > div > div.qtyInc > i")).click();
                site.findElement(By.cssSelector("#onereturn > div.col-lg-1.pr-0 > div > div > div > div > div.dropdown-item.child_qty > div > div > div.qtyInc > i")).click();
                site.findElement(By.cssSelector("#onereturn > div.col-lg-1.pr-0 > div > div > div > div > div.dropdown-item.child_qty > div > div > div.qtyInc > i")).click();


                Assertions.assertEquals("10", site.findElement(By.xpath("/html/body/section[1]/section/div/div/form/div[2]/div[3]/div/div/div/a/span/span")).getText());
                site.quit();


        }

        @Test
        public void tc5_1_2 () {
                // Adding 4 adults, 3 children, 2 infants and removing all but 1 adult - CHROME

                System.setProperty("webdriver.chrome.driver", "c:/Chromedriver/chromedriver.exe");
                WebDriver site = new ChromeDriver();
                site.manage().window().maximize();

                site.navigate().to("https://www.phptravels.net/flights");

                site.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);

                site.findElement(By.cssSelector("#onereturn > div.col-lg-1.pr-0 > div > div > div > a > span > span")).click();


                site.findElement(By.cssSelector("#onereturn > div.col-lg-1.pr-0 > div > div > div > div > div.dropdown-item.adult_qty > div > div > div.qtyInc")).click();
                site.findElement(By.cssSelector("#onereturn > div.col-lg-1.pr-0 > div > div > div > div > div.dropdown-item.adult_qty > div > div > div.qtyInc")).click();
                site.findElement(By.cssSelector("#onereturn > div.col-lg-1.pr-0 > div > div > div > div > div.dropdown-item.adult_qty > div > div > div.qtyInc")).click();
                site.findElement(By.cssSelector("#onereturn > div.col-lg-1.pr-0 > div > div > div > div > div.dropdown-item.adult_qty > div > div > div.qtyInc")).click();

                site.findElement(By.cssSelector("#onereturn > div.col-lg-1.pr-0 > div > div > div > div > div.dropdown-item.child_qty > div > div > div.qtyInc > i")).click();
                site.findElement(By.cssSelector("#onereturn > div.col-lg-1.pr-0 > div > div > div > div > div.dropdown-item.child_qty > div > div > div.qtyInc > i")).click();
                site.findElement(By.cssSelector("#onereturn > div.col-lg-1.pr-0 > div > div > div > div > div.dropdown-item.child_qty > div > div > div.qtyInc > i")).click();
                site.findElement(By.cssSelector("#onereturn > div.col-lg-1.pr-0 > div > div > div > div > div.dropdown-item.child_qty > div > div > div.qtyInc > i")).click();

                //removing passangers

                site.findElement(By.xpath("/html/body/section[1]/section/div/div/form/div[2]/div[3]/div/div/div/div/div[2]/div/div/div[1]/i")).click();
                site.findElement(By.xpath("/html/body/section[1]/section/div/div/form/div[2]/div[3]/div/div/div/div/div[2]/div/div/div[1]/i")).click();
                site.findElement(By.xpath("/html/body/section[1]/section/div/div/form/div[2]/div[3]/div/div/div/div/div[2]/div/div/div[1]/i")).click();

                site.findElement(By.xpath("/html/body/section[1]/section/div/div/form/div[2]/div[3]/div/div/div/div/div[1]/div/div/div[1]/i")).click();
                site.findElement(By.xpath("/html/body/section[1]/section/div/div/form/div[2]/div[3]/div/div/div/div/div[1]/div/div/div[1]/i")).click();
                site.findElement(By.xpath("/html/body/section[1]/section/div/div/form/div[2]/div[3]/div/div/div/div/div[1]/div/div/div[1]/i")).click();
                site.findElement(By.xpath("/html/body/section[1]/section/div/div/form/div[2]/div[3]/div/div/div/div/div[1]/div/div/div[1]/i")).click();

                Assertions.assertEquals("2", site.findElement(By.cssSelector("#onereturn > div.col-lg-1.pr-0 > div > div > div > a > span > span")).getText());

                site.quit();
        }

        @Test
        public void tc6_1_1 () {
                //Initializing search with 0 passangers (for one way flights)

                System.setProperty("webdriver.gecko.driver", "c:/Chromedriver/geckodriver.exe");
                WebDriver site = new FirefoxDriver();
                site.manage().window().maximize();
                site.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
                site.navigate().to("https://www.phptravels.net/flights");

                site.findElement(By.id("autocomplete")).sendKeys("DEN");
                site.findElement(By.xpath("/html/body/section[1]/section/div/div/form/div[2]/div[1]/div/div[1]/div/div/div/div/div[1]/div[1]")).click();

                site.findElement(By.id("autocomplete2")).sendKeys("lax");
                site.findElement(By.xpath("/html/body/section[1]/section/div/div/form/div[2]/div[1]/div/div[2]/div/div[2]/div/div/div[1]/div[1]/strong")).click();

                site.findElement(By.cssSelector("#onereturn > div.col-lg-1.pr-0 > div > div > div > a > span > span")).click();
                site.findElement(By.cssSelector("div.dropdown-item:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > i:nth-child(1)")).click();

                site.findElement(By.id("flights-search")).click();


                Assertions.assertEquals("https://www.phptravels.net/flights", site.getCurrentUrl());

                site.quit();



        }


        }
