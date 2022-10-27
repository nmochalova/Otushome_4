package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CoursePage extends Page{

    public CoursePage(WebDriver driver) {
        super(driver);
    }

    public String getTitleByCourse(String titleBeforeClick) {
        By locator;

        //Локаторы для разных курсов различаются
        if (!titleBeforeClick.toUpperCase().contains("СПЕЦИАЛИЗАЦИЯ"))
            locator = By.cssSelector(".course-header2__title");
         else
             locator = By.tagName("title");

        return driver.findElement(locator).getAttribute("innerText"); //Если эл-т не видим на странице, то getText() возвращает пустую строку, поэтому берем текст напрямую из свойств эл-та.
    }

}
