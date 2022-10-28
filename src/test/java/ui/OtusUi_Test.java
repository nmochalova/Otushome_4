package ui;

import data.Titles;
import datatable.DataTableCourse;
import io.restassured.response.Response;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import pages.CoursePage;
import pages.MainPage;
import services.UserApi;
import stubs.CourseStub;
import stubs.ScoreStub;
import stubs.UserStub;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

//mvn clean test -Dtest=OtusUi_Test -Dbrowser="chrome" -Dfilter="QA"
public class OtusUi_Test extends BaseTest {
    @Test
    public void find_course_by_name_test() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openSite();

        List<String> names = mainPage.getNamesAllCourse();
        String filter = System.getProperty("filter");
        
        if (filter == null)
            names.forEach(System.out::println);
        else{
            List<String> namesAfterFilter = mainPage.filterCourseByName(names, filter);
            namesAfterFilter.forEach(System.out::println);
        }
    }

    @Test
    public void get_early_course_test() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openSite();

        //Набираем в Map плитки всех курсов на странице, отдельно в таблицу парсим заголовок и дату курса.
        HashMap<WebElement, DataTableCourse> nameAndDate = mainPage.getNamesAndDates();

        //Выбираем самый ранний курс
        WebElement course = mainPage.getMinMaxDateOfCourse(nameAndDate, false); //false = ищем min
        String titleBeforeClick = mainPage.getNameOfCourse(course);

        //наводим курсор на выбранный курс
        mainPage.moveToElement(course);
        mainPage.clickToElement(course);

        //Переходим на страницу курса
        CoursePage coursePage = new CoursePage(driver);
        String titleAfterClick = coursePage.getTitleByCourse(titleBeforeClick);

        //Проверяем, что открылась страница в соответствии с выбранным курсом
        String expectedTitle=Titles.getExpectedTitleCoursePage(titleBeforeClick);

        assertThat(titleAfterClick).isEqualTo(expectedTitle);
    }

    @Test
    public void get_latest_course_test() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openSite();

        HashMap<WebElement, DataTableCourse> nameAndDate = mainPage.getNamesAndDates();

        WebElement course = mainPage.getMinMaxDateOfCourse(nameAndDate, true); //true - ищем max
        String titleBeforeClick = mainPage.getNameOfCourse(course);

        //наводим курсор на выбранный курс
        mainPage.moveToElement(course);
        mainPage.clickToElement(course);

        CoursePage coursePage = new CoursePage(driver);
        String titleAfterClick = coursePage.getTitleByCourse(titleBeforeClick);

        //Проверяем, что открылась страница в соответствии с выбранным курсом
        String expectedTitle=Titles.getExpectedTitleCoursePage(titleBeforeClick);

        assertThat(titleAfterClick).isEqualTo(expectedTitle);
    }

}
