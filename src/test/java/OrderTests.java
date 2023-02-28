import config.AppConfig;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import PageObjects.MainScooterPage;
import PageObjects.OrderFormPage;
import org.openqa.selenium.firefox.FirefoxDriver;

@RunWith(Parameterized.class)
public class OrderTests {

    private WebDriver driver;

    private final int numOfOrderButton;
    private final String userName;
    private final String userSurname;
    private final String userAddress;
    private final int selectOfMetroStation;
    private final String userPhone;
    private final String userDate;
    private final int selectOfRentalPeriod;
    private final int checkBoxOfColor;
    private final String userComment;

    public OrderTests(int numOfOrderButton, String userName, String userSurname, String userAddress, int selectOfMetroStation, String userPhone, String userDate, int selectOfRentalPeriod, int checkBoxOfColor, String userComment) {
        this.numOfOrderButton = numOfOrderButton;
        this.userName = userName;
        this.userSurname = userSurname;
        this.userAddress = userAddress;
        this.selectOfMetroStation = selectOfMetroStation;
        this.userPhone = userPhone;
        this.userDate = userDate;
        this.selectOfRentalPeriod = selectOfRentalPeriod;
        this.checkBoxOfColor = checkBoxOfColor;
        this.userComment = userComment;
    }


    @Before
    public void setUp() {
        // подготовка браузера
        WebDriverManager.chromedriver().setup();
    }

    @Before
    public void preconditionTests() {
        // драйвер для браузера Firefox
        // driver = new FirefoxDriver();
        // драйвер для браузера Chrome
        driver = new ChromeDriver();
        // выставлено ожидание
        // driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(WebDriverConfig.WAIT_SECONDS_TIMEOUT));
        // переход на страницу сервиса
        driver.navigate().to(AppConfig.URL);
        // удаление всех куки, что бы появилась кнопка акцепта куки
        driver.manage().deleteAllCookies();

        MainScooterPage mainPage = new MainScooterPage(driver);
        mainPage.acceptOfCookie();
    }

    @Parameterized.Parameters
    public static Object[][] getTestData() {
        return new Object[][]{
                {0, "Елена", "Ленина", "Москва, Бродников, 4", 0, "89054464555", "27.02.2023", 0, 0, "Тестовый"},
                {1, "Петр", "Петров", "Москва", 224, "+79054464555", "31.02.2023", 6, 1, "Позвонить курьеру 89999999999"},
        };
    }

    @Test
    public void orderTest() {
        MainScooterPage mainPage = new MainScooterPage(driver);
        mainPage.ButtonToOrderPage(numOfOrderButton);

        OrderFormPage orderPage = new OrderFormPage(driver);
        orderPage.waitForLoadingOrderPage();

        orderPage.fillInputFields(userName, userSurname, userAddress, selectOfMetroStation, userPhone, userDate, selectOfRentalPeriod, checkBoxOfColor, userComment);
        orderPage.orderButtons();
        orderPage.checkСloudOrderNumber();
    }

    @After
    public void teardown() {
        driver.quit();
    }
}
