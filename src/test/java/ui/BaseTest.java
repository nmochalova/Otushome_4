package ui;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import stubs.CourseStub;
import stubs.ScoreStub;
import stubs.UserStub;

import java.net.MalformedURLException;
import java.net.URI;

import static com.github.tomakehurst.wiremock.client.WireMock.configureFor;

public class BaseTest  {
  public WebDriver driver;
  private String browser = System.getProperty("browser");
  private String browserVersion = System.getProperty("browser.version");
  private String remoteUrl = System.getProperty("webdriver.remote.url");

  {
    new ScoreStub();
    new UserStub();
    new CourseStub();
  }

  @BeforeClass
  public static void startWireMock() {
    int port = Integer.parseInt(System.getProperty("port.wiremock"));
    configureFor(port);
  }

  @Before
  public void init() {
    DesiredCapabilities capabilities = new DesiredCapabilities();
    capabilities.setCapability("browserName",browser);
    capabilities.setCapability("browserVersion",browserVersion);
    capabilities.setCapability("enableVNC",true);
    try {
      driver = new RemoteWebDriver(
              URI.create(remoteUrl).toURL(),
              capabilities
      );
    } catch (MalformedURLException e) {
      throw new RuntimeException(e);
    }
  }

  @After
  public void tearDown() {
    if(driver != null) {
      driver.close();
      driver.quit();
    }
  }
}
