import dev.failsafe.internal.util.Durations;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class BaseTest {

  private String BASE_URL = "https://www.n11.com/";
  WebDriver driver;

  @BeforeEach
  void setupTest() {
    testWithDifferentBrowsers("chrome");
    driver.manage().window().maximize();
    driver.get(BASE_URL);

  }

  @AfterEach
  void teardown() {
    if (driver != null) {
      driver.quit();
    }
  }

  @ParameterizedTest
  @ValueSource(strings = {"chrome", "firefox", "edge", "opera"})
  void testWithDifferentBrowsers(String browser) {

      switch (browser.toLowerCase()) {
        case "chrome":
          WebDriverManager.chromedriver().setup();
          driver = new ChromeDriver();
          break;
        case "edge":
          WebDriverManager.edgedriver().setup();
          driver = new EdgeDriver();
          break;
        default:
          WebDriverManager.chromedriver().setup();
          driver = new ChromeDriver();
          break;

    }
  }
}
