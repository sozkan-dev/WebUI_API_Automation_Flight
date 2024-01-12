package driver;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.io.FileInputStream;
import java.util.Properties;

public class DriverFactory {

    private static ThreadLocal<WebDriver> webDriver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        if(webDriver.get() == null){
            webDriver.set(createDriver());
        }
        return webDriver.get();
    }

    private static String getBrowserType() {
        String browserType = null;
        try {
            Properties prop = new Properties();
            FileInputStream file = new FileInputStream(System.getProperty("user.dir") + "/src/main/java/properties/config.properties");
            prop.load(file);
            browserType = prop.getProperty("browser")
                    .toLowerCase()
                    .trim();
        } catch (Exception e) {
            System.out.println(e);
        }
        return browserType;
    }
    private static WebDriver createDriver() {
        WebDriver driver = null;



        switch (getBrowserType()) {
            case "chrome" -> {

                ChromeOptions options = new ChromeOptions();
                options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
                options.addArguments("--remote-allow-origins=*");
                driver = new ChromeDriver(options);
                driver.manage().window().maximize();
                break;
            }
            case "firefox" -> {

                FirefoxOptions options = new FirefoxOptions();
                options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
                driver = new FirefoxDriver(options);
                break;
            }
            case "edge" -> {
                EdgeOptions options = new EdgeOptions();
                options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
                driver = new EdgeDriver(options);
                break;
            }
        }
        return driver;
    }
    public static void cleanDriver() {
        webDriver.get().quit();
        webDriver.remove();


    }

}
