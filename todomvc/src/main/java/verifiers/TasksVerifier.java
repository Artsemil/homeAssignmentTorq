package verifiers;


import org.testng.Assert;
import pages.TodoVMCPage;

import java.util.List;
import java.util.Optional;

public class TasksVerifier {

    private TasksVerifier() {
    }

    public static void verifyTaskExists(List<String> allTasks, String task) {
        Optional<String> result = allTasks.stream().filter(str -> str.equals(task)).findAny();
        Assert.assertTrue(result.isPresent(), "Task was not added to the list");
    }

    public static void verifyTaskDoesntExist(List<String> allTasks, String task) {
        Optional<String> result = allTasks.stream().filter(str -> str.equals(task)).findAny();
        Assert.assertTrue(result.isEmpty(), "Task is exist in the list, but shouldn't");
    }

    public static void verifyTaskIsComplete(TodoVMCPage page, String task) {
        Assert.assertTrue(page.isTaskCompleted(task), "Task is not marked as completed");
    }
}
