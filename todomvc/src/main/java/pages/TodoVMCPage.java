package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class TodoVMCPage extends BasePage {

    public TodoVMCPage(WebDriver webDriver) {
        super(webDriver);
    }

    @FindBy(xpath = "//input[contains(@class, 'new-todo')]")
    private WebElement enterField;

    @FindBy(xpath = "//a[contains(text(), 'All')]")
    private WebElement allTasksLink;

    @FindBy(xpath = "//a[contains(text(), 'Active')]")
    private WebElement activeTasksLink;

    @FindBy(xpath = "//a[contains(text(), 'Completed')]")
    private WebElement completedTasksLink;

    @FindBy(xpath = "//button[contains(@class, 'clear-completed')]")
    private WebElement clearCompletedTasks;

    @FindBy(xpath = "//span/strong")
    private WebElement taskAmount;


    @FindBy(xpath = "//li//label")
    private List<WebElement> allTasks;

    public void selectActiveTasks() {
        activeTasksLink.click();
    }

    public void selectAllTasks() {
        allTasksLink.click();
    }

    public void selectCompletedTasks() {
        completedTasksLink.click();
    }

    public void addNewTask(String task) {
        enterField.sendKeys(task);
        enterField.sendKeys(Keys.RETURN);
    }

    public void deleteTask(String task) {
        WebElement deleteIcon = webDriver.findElement(By.xpath("//label[text()='" + task + "']/following-sibling::button"));
        Actions actions = new Actions(webDriver);
        actions.moveToElement(webDriver.findElement(By.xpath("//label[text()='" + task + "']"))).build().perform();
        deleteIcon.click();
    }

    public void makeTaskCompleted(String task) {
        WebElement deleteIcon = webDriver.findElement(By.xpath("//label[text()='" + task + "']/preceding::input[@class='toggle']"));
        Actions actions = new Actions(webDriver);
        actions.moveToElement(webDriver.findElement(By.xpath("//label[text()='" + task + "']"))).build().perform();
        deleteIcon.click();
    }

    public List<String> getListOfTasks() {
        return allTasks.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public boolean isTaskCompleted(String task) {
        WebElement deleteIcon = webDriver.findElement(By.xpath("//label[text()='" + task + "']/ancestor::li"));
        return deleteIcon.getAttribute("class").contains("completed");
    }

    public void clearCompletedTasks() {
        clearCompletedTasks.click();
    }

    public int getTasksAmount() {
        return Integer.parseInt(taskAmount.getText());
    }

    public String getHeaderText() {
        return webDriver.findElement(By.xpath("//header//h1")).getText();
    }

    public String getInputFieldText() {
        return enterField.getAttribute("placeholder");
    }

    public String getItemsText() {
        return webDriver.findElement(By.xpath("//span[@class = 'todo-count']")).getText();
    }

}
