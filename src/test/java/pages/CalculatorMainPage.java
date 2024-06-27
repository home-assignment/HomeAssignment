package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CalculatorMainPage {
    WebDriver driver;

    @FindBy(css = "#BtnPlus")
    private WebElement plusButton;

    @FindBy(css = "#BtnMinus")
    private WebElement minusButton;

    @FindBy(css = "#BtnMult")
    private WebElement multiplyButton;

    @FindBy(css = "#BtnCalc")
    private WebElement calculateResultButton;

    @FindBy(css = "button[title = 'Sine, sin(x)']")
    private WebElement sinusButton;

    @FindBy(css = "#hist")
    private WebElement historyButton;

    @FindBy(xpath = "//*[@id = 'histframe']//p[@class = 'r']")
    private WebElement firstResult;

    public void pressNumber(int number) {
        String temp = Integer.toString(number);
        int[] newNumber = new int[temp.length()];
        for (int i = 0; i < temp.length(); i++) {
            newNumber[i] = temp.charAt(i) - '0';
            By numberLocator = By.cssSelector("#Btn" + newNumber[i]);
            driver.findElement(numberLocator).click();
        }
    }

    public void pressParenthesis(String side) {
        By openOrCloseParenthesis = By.xpath("//button[@id='BtnParan" + side + "']");
        driver.findElement(openOrCloseParenthesis).click();
    }

    public void summ() {
        plusButton.click();
    }

    public void sub() {
        minusButton.click();
    }

    public void mult() {
        multiplyButton.click();
    }

    public void sin() {
        sinusButton.click();
    }

    public void calculate() {
        calculateResultButton.click();
    }

    public void openHistory() {
        historyButton.click();
    }

    public Double getResult() {
        String resultText = firstResult.getAttribute("title");
        System.out.println(resultText);
        return Double.parseDouble(resultText);
    }


    public CalculatorMainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}
