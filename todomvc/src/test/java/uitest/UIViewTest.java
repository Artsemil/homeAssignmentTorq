package uitest;

import org.testng.Assert;
import org.testng.annotations.Test;
import verifiers.TasksVerifier;

import java.util.List;

public class UIViewTest extends BaseTest {

    private static final String HEADER = "todos";
    private static final String INPUT_TEXT = "What needs to be done?";
    private static final String INITIAL_TASK = "Use Redux";
    private static final String INITIAL_ITEMS = "1 item left";

    @Test
    public void verifyHeader() {
        Assert.assertEquals(todoVMCPage.getHeaderText(), HEADER);
    }

    @Test
    public void verifyInputFieldText() {
        Assert.assertEquals(todoVMCPage.getInputFieldText(), INPUT_TEXT);
    }

    @Test
    public void verifyInitialTask() {
        List<String> listOfTasks = todoVMCPage.getListOfTasks();
        TasksVerifier.verifyTaskExists(listOfTasks, INITIAL_TASK);
        todoVMCPage.selectActiveTasks();
        TasksVerifier.verifyTaskExists(todoVMCPage.getListOfTasks(), INITIAL_TASK);
    }

    @Test
    public void verifyInitialItemsAmount() {
        String items = todoVMCPage.getItemsText();
        Assert.assertEquals(items, INITIAL_ITEMS);
    }

}
