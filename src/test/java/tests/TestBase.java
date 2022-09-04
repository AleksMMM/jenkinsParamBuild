package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import config.WebConfig;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class TestBase {

    private String remoteUrl;

    @BeforeAll
    public void setup() {

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());

        remoteUrl = System.getProperty("remote_url");
        String browser = System.getProperty("browser", "chrome");
        String browserSize = System.getProperty("browser_size", "1920x1080" );
        String browserVersion = System.getProperty("browser_version");

        if (remoteUrl != null){
            WebConfig config = ConfigFactory.create(WebConfig.class);
            String remoteLogin = config.webRemoteDriverUser();
            String remotePassword = config.webRemoteDriverPassword();

            Configuration.remote = String.format("https://%s:%s@%s/wd/hub", remoteLogin, remotePassword, remoteUrl);
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("enableVNC", true);
            capabilities.setCapability("enableVideo", true);
            Configuration.browserCapabilities = capabilities;
        }

        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = browserSize;
        Configuration.browser = browser;
        Configuration.browserVersion = browserVersion;
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
        closeWebDriver();
    }
}