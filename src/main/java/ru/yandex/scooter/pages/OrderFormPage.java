package ru.yandex.scooter.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class OrderFormPage {
    private WebDriver driver;

    // ФОРМА ЮЗЕРА
    private final By formOfUser = By.className("Order_Form__17u6u");
    // Поле ИМЯ
    private final By inputName = By.cssSelector("[placeholder='* Имя']");
    // Поле ФАМИЛИЯ
    private final By inputSurname = By.cssSelector("[placeholder='* Фамилия']");
    // Поле АДРЕС
    private final By inputAddress = By.cssSelector("[placeholder='* Адрес: куда привезти заказ']");
    // Поле МЕТРО
    private final By inputMetroStation = By.cssSelector("[placeholder='* Станция метро']");
    // Список станций метро
    private final By selectMetroStation = By.xpath(".//li[@class='select-search__row']/button");
    // Поле ТЕЛЕФОН
    private final By inputPhone = By.cssSelector("[placeholder='* Телефон: на него позвонит курьер']");
    // Кнопка ДАЛЕЕ
    private final By nextButton = By.xpath(".//button[text()='Далее']");

    // ФОРМА САМОКАТА
    // выпадающий календарь
    private final By inputCalendar = By.cssSelector("[placeholder='* Когда привезти самокат']");
    // поле с выпадашкой СРОК АРЕНДЫ
    private final By inputRentalPeriod = By.xpath(".//div[@class='Dropdown-placeholder']");
    // Список доступных сроков аренды
    private final By selectRentalPeriod = By.xpath(".//div[@class='Dropdown-option']");
    // Поле ЦВЕТ САМОКАТА
    private final By inputСolor = By.className("Checkbox_Label__3wxSf");
    // Поле КОММЕНТАРИЙ
    private final By inputComment = By.cssSelector("[placeholder='Комментарий для курьера']");
    // Кнопка Заказать после заполнения форм
    private final By buttonConfirmOrder = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Заказать']");
    // Кнопка в модальном окне "ДА"
    private final By buttonYes = By.xpath(".//button[text()='Да']");
    // Модальное окно "Заказ оформлен"
    private final By cloudOrderNumber = By.xpath(".//div[text()='Заказ оформлен']");
    // Кнопка "Посмотреть статус"
    // private final By statusButton = By.xpath(".//button[text()='Посмотреть статус']");

    public OrderFormPage(WebDriver driver) {
        this.driver = driver;
    }


    // ожидание появления формы заполнения на странице
    public void waitForLoadingOrderPage() {
        driver.findElement(formOfUser).isDisplayed();
    }

    // Заполнение форм заказа
    public void fillInputFields(String userName, String userSurname, String userAddress, int selectOfMetroStation, String userPhone, String userDate, int selectOfRentalPeriod, int checkBoxOfColor, String userComment) {
        driver.findElement(inputName).sendKeys(userName);
        driver.findElement(inputSurname).sendKeys(userSurname);
        driver.findElement(inputAddress).sendKeys(userAddress);
        driver.findElement(inputMetroStation).click();
        driver.findElements(selectMetroStation).get(selectOfMetroStation).click();
        driver.findElement(inputPhone).sendKeys(userPhone);
        driver.findElement(nextButton).click();
        driver.findElement(inputCalendar).sendKeys(userDate);
        driver.findElement(inputCalendar).sendKeys(Keys.ENTER);
        driver.findElement(inputRentalPeriod).click();
        driver.findElements(selectRentalPeriod).get(selectOfRentalPeriod).click();
        driver.findElements(inputСolor).get(checkBoxOfColor).click();
        driver.findElement(inputComment).sendKeys(userComment);
    }
    // подтверждение заказа
    public void orderButtons() {
        driver.findElement(buttonConfirmOrder).isEnabled();
        driver.findElement(buttonConfirmOrder).click();
        driver.findElement(buttonYes).isDisplayed();
        driver.findElement(buttonYes).click();
    }
    // проверка появления модалки, что заказ оформлен
    public void checkСloudOrderNumber() {
        driver.findElement(cloudOrderNumber).isDisplayed();
    }
}

