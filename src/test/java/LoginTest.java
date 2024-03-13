import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginTest extends BaseTest{

  public static final long WAIT = 10;
  private By signInButton = By.className("btnSignIn");
  private By usernameLocator = By.id("email");
  private By passwordLocator = By.id("password");
  private By loginButtonLocator = By.id("loginButton");
  private By notificationIcon = By.className("myNotHolder");
  private By loginError = By.className("error-message");
  private By emptyUsernameError = By.xpath(
      "//form[@id='loginForm']/div[@class='form-inputs']/div[1]/div[@class='errorMessage']/div[@class='errorText']");
  private By emptyPasswordError = By.xpath(
      "//form[@id='loginForm']/div[@class='form-inputs']/div[2]/div[@class='errorMessage']/div[@class='errorText']");

  @Test
  public void testValidLogin() {
    clickByElement(signInButton);
    enterText(usernameLocator, "695striped@yogirt.com");
    enterText(passwordLocator, "Test1234");
    clickByElement(loginButtonLocator);
    assertElementDisplayed(notificationIcon, "Notification icon bulunamadı");
  }

  @Test
  public void testValidLogintoUpperCase() {
    clickByElement(signInButton);
    String email = "695striped@yogirt.com";
    String upperEmail = email.toUpperCase();
    enterText(usernameLocator, upperEmail);
    enterText(passwordLocator, "Test1234");
    clickByElement(loginButtonLocator);
    assertElementDisplayed(notificationIcon, "Notification icon bulunamadı");
  }

  @Test
  public void testValidLogintoLowerCase() {
    clickByElement(signInButton);
    String email = "695STRIPED@YOGIRT.COM";
    String lowerEmail = email.toLowerCase();
    enterText(usernameLocator, lowerEmail);
    enterText(passwordLocator, "Test1234");
    clickByElement(loginButtonLocator);
    assertElementDisplayed(notificationIcon, "Notification icon bulunamadı");
  }

  @Test
  public void invalidUserNameLogin() {
    clickByElement(signInButton);
    enterText(usernameLocator, "autostriped@yogirt.com");
    enterText(passwordLocator, "Test1234");
    clickByElement(loginButtonLocator);
    assertElementTextEquals(loginError,
        "E-posta adresi veya şifre hatalı, kontrol edebilir misin?");
  }

  @Test
  public void invalidPasswordLogin() {
    clickByElement(signInButton);
    enterText(usernameLocator, "695striped@yogirt.com");
    enterText(passwordLocator, "Test5678");
    clickByElement(loginButtonLocator);
    assertElementTextEquals(loginError,
        "E-posta adresi veya şifre hatalı, kontrol edebilir misin?");
  }

  @Test
  public void emptyUserNameAndPasswordLogin() {
    clickByElement(signInButton);
    clickByElement(loginButtonLocator);
    assertElementTextEquals(emptyUsernameError, "Geçerli bir e-posta adresi girmelisin.");
    assertElementTextEquals(emptyPasswordError, "Şifreni girebilir misin?");
  }

  @Test
  public void emptyUserNameAndPasswordLogin1() {
    clickByElement(signInButton);
    clickByElement(loginButtonLocator);
    assertElementTextEquals(emptyUsernameError, "Geçerli bir e-posta adresi girmelisin.");
    assertElementTextEquals(emptyPasswordError, "Şifreni girebilir misin?");
  }

  @Test
  public void checkSecurity() {
    String currentUrl = driver.getCurrentUrl();
    String expectedUrl = "https://www.n11.com/";
    Assertions.assertEquals(currentUrl, expectedUrl);
    if (currentUrl.startsWith("https://")) {
      System.out.println("Bağlantı güvenli.");
    } else {
      System.out.println("Bağlantı güvenli değil.");
    }
  }

  public void enterText(By locator, String text) {
    WebElement element = driver.findElement(locator);
    element.clear();
    element.sendKeys(text);
  }

  public void clickByElement(By locator) {
    WebElement element = driver.findElement(locator);
    waitForVisibility(locator);
    element.click();
  }

  public void assertElementTextEquals(By locator, String expectedText) {
    WebElement element = driver.findElement(locator);
    String actualText = element.getText();
    waitForVisibility(locator);
    Assertions.assertEquals(expectedText, actualText);
  }

  public void assertElementDisplayed(By locator, String errorMessage) {
    WebElement element = driver.findElement(locator);
    waitForVisibility(locator);
    assertTrue(element.isDisplayed(), errorMessage);
  }

  public void waitForVisibility(By locator) {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT));
    wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

  }
}
