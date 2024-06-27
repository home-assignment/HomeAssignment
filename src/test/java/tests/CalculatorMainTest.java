package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.CalculatorMainPage;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CalculatorMainTest {
    private WebDriver driver;
    private CalculatorMainPage calculatorMainPage;


    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://web2.0calc.com/");
        calculatorMainPage = new CalculatorMainPage(driver);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }


    @ParameterizedTest(name = "Summ:  {0} + {1}")
    @CsvSource({"2, 3", "5, 41"})
    public void summ(int firstNumber, int secondNumber) {
        calculatorMainPage.pressNumber(firstNumber);
        calculatorMainPage.summ();
        calculatorMainPage.pressNumber(secondNumber);
        calculatorMainPage.calculate();
        calculatorMainPage.openHistory();
        assertEquals(firstNumber + secondNumber, calculatorMainPage.getResult(), "unexpected result");
    }

    @ParameterizedTest(name = "Subtraction:  {0} - {1}")
    @CsvSource({"10, 2", "5, 14"})
    public void subtract(int firstNumber, int secondNumber) {
        calculatorMainPage.pressNumber(firstNumber);
        calculatorMainPage.sub();
        calculatorMainPage.pressNumber(secondNumber);
        calculatorMainPage.calculate();
        calculatorMainPage.openHistory();
        assertEquals(firstNumber - secondNumber, calculatorMainPage.getResult(), "unexpected result");
    }

    @ParameterizedTest(name = "Sinus: {0}")
    @ValueSource(ints = {30, 777})
    public void sin(int number) {
        calculatorMainPage.sin();
        calculatorMainPage.pressNumber(number);
        calculatorMainPage.calculate();
        calculatorMainPage.openHistory();
        double rads = Math.toRadians(number);
        double sine = Math.sin(rads);
        assertTrue(Math.abs(sine - calculatorMainPage.getResult()) < 0.0001, "unexpected result");
    }

    @ParameterizedTest(name = "using formula: ({0}-{1})*{2}  result should not be: {3}")
    @CsvSource({"10, 2, 2, 20", "10, 3, 2, 16"})
    public void formula(int firstNumber, int secondNumber, int thirdNumber, int result) {
        calculatorMainPage.pressParenthesis("L");
        calculatorMainPage.pressNumber(firstNumber);
        calculatorMainPage.sub();
        calculatorMainPage.pressNumber(secondNumber);
        calculatorMainPage.pressParenthesis("R");
        calculatorMainPage.mult();
        calculatorMainPage.pressNumber(thirdNumber);
        calculatorMainPage.calculate();
        calculatorMainPage.openHistory();

        assertNotEquals(result, calculatorMainPage.getResult(), "unexpected result");
    }

}
