package oks09.support.pageobjects;

import oks09.support.tools.Drivers;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class PageObject {
  protected WebDriver driver;

  /**
   * Initialization of each web element annotated by @FindBy()
   */
  public PageObject() {
    driver = Drivers.getDriver();
    PageFactory.initElements(driver, this);
  }

  /**
   * When a web element was initialized by PageFactory.initElements()
   * (e.g. web element was found on the web page = displayed)
   * the reference at it is an only one element of the list
   *
   * if a web element has not been initialized, the list is empty
   *
   * @param list list with one reference to a web element, or empty list
   * @return true, when list is not empty, false otherwise
   */
  public boolean isDisplayed(List<WebElement> list) {
    return list.size() == 1;
  }

  /**
   * Getting an initialized reference to a web element
   * or failing tests by Fail.failTestDueToMissingElement(id);
   *
   * @param list list with one reference to a web element, or empty list
   * @param id ID of the web element - it is necessary for logging of a possible failed tests
   * @return reference to a web element
   */
  public WebElement getElement(List<WebElement> list, String id) {
    if (isDisplayed(list) == true) {
      return list.get(0);
    }
    else {
      failTestDueToMissingElement(id);
      return null;
    }
  }

  /**
   * Fails tests in the case of missing web element
   * mostly used from PageObject.getElement()
   *
   * failMessage will be logged in TestEvaluation.testFailed()
   *
   * @param id ID of missing web element
   */
  public void failTestDueToMissingElement(String id) {
    String failMessage = "WebElement ID='"
            + id + "' has not been found";
//    Assertions.fail(failMessage);
    System.err.println(failMessage);
    System.exit(1);
  }

}