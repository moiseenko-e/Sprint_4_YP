package ru.yandex.scooter.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class MainScooterPage {
    private WebDriver driver;

    // Кнопка куки
    private final By cookieButton = By.id("rcc-confirm-button");
    // Заголовок FAQ
    private final By faqHeader = By.className("Home_SubHeader__zwi_E");
    // Аккордеон с вопросами
    private final By questions = By.xpath(".//div[@class='accordion']");
    // Список вопросов
    private final By listOfQuestions = By.xpath(".//div[@class='accordion__item']/div/div");
    // Список ответов
    private final By listOfAnswers = By.xpath(".//div[@class='accordion__panel']");
    // Кнопки Заказать
    private final By orderButton = By.xpath(".//button[text()='Заказать']");


    public MainScooterPage(WebDriver driver) {
        this.driver = driver;
    }

    public void acceptOfCookie() {
        driver.findElement(cookieButton).isDisplayed();
        driver.findElement(cookieButton).click();
    }

    // Скролл до FAQ блока на главной
    public void scrollToFaq() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(questions));
        driver.findElement(faqHeader).isDisplayed();
        driver.findElement(questions).isDisplayed();
    }

    // Получение ответа на выбранный вопрос
    public String getTextAnswer(int numOfQuestion) {
        driver.findElements(listOfQuestions).get(numOfQuestion).click();
        driver.findElements(listOfAnswers).get(numOfQuestion).isDisplayed(); // ждем, что бы ответ отобразился
        return driver.findElements(listOfAnswers).get(numOfQuestion).getText(); // получаем текст
    }

    //Открытие формы оформления заказа кнопкой из хедера
    public void buttonToOrderPage(int numOfOrderButton) {
        if (!driver.findElements(orderButton).get(numOfOrderButton).isDisplayed()) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", driver.findElements(orderButton).get(numOfOrderButton));
        }
        driver.findElements(orderButton).get(numOfOrderButton).click();
    }
}
